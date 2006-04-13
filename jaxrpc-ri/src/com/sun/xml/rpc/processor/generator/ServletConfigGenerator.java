/*
 * $Id: ServletConfigGenerator.java,v 1.2 2006-04-13 01:28:57 ofung Exp $
 */

/*
 * The contents of this file are subject to the terms
 * of the Common Development and Distribution License
 * (the License).  You may not use this file except in
 * compliance with the License.
 * 
 * You can obtain a copy of the license at
 * https://glassfish.dev.java.net/public/CDDLv1.0.html.
 * See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 * When distributing Covered Code, include this CDDL
 * Header Notice in each file and include the License file
 * at https://glassfish.dev.java.net/public/CDDLv1.0.html.
 * If applicable, add the following below the CDDL Header,
 * with the fields enclosed by brackets [] replaced by
 * you own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * Copyright 2006 Sun Microsystems Inc. All Rights Reserved
 */

package com.sun.xml.rpc.processor.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.Properties;
import java.util.TreeSet;

import javax.xml.namespace.QName;

import com.sun.xml.rpc.processor.config.Configuration;
import com.sun.xml.rpc.processor.model.Model;
import com.sun.xml.rpc.processor.model.ModelProperties;
import com.sun.xml.rpc.processor.model.Operation;
import com.sun.xml.rpc.processor.model.Port;
import com.sun.xml.rpc.processor.model.Service;
import com.sun.xml.rpc.processor.util.DirectoryUtil;
import com.sun.xml.rpc.processor.util.GeneratedFileInfo;
import com.sun.xml.rpc.processor.util.IndentingWriter;
import com.sun.xml.rpc.processor.util.ProcessorEnvironment;
import com.sun.xml.rpc.soap.SOAPVersion;

/**
 *
 * @author JAX-RPC Development Team
 */
public class ServletConfigGenerator extends GeneratorBase {
    private File configFile;
    private Service currentService;
    private IndentingWriter out;
    private int portCount;
    private TreeSet operations = null;

    public ServletConfigGenerator() {
        configFile = null;
        out = null;
        portCount = 0;
    }

    public GeneratorBase getGenerator(
        Model model,
        Configuration config,
        Properties properties) {
        return new ServletConfigGenerator(model, config, properties);
    }

    public GeneratorBase getGenerator(
        Model model,
        Configuration config,
        Properties properties,
        SOAPVersion ver) {
        return new ServletConfigGenerator(model, config, properties);
    }

    private ServletConfigGenerator(
        Model model,
        Configuration config,
        Properties properties) {
        super(model, config, properties);
        configFile = null;
        out = null;
        portCount = 0;
    }

    protected void preVisitService(Service service) throws Exception {
        try {
            currentService = service;
            String className = service.getName().getLocalPart();
            configFile = configFileForClass(className, nonclassDestDir, env);

            /* Here the filename for the ServletConfig to be geenrated is
               retrieved to be set in the GeneratedFileInfo Object */
            GeneratedFileInfo fi = new GeneratedFileInfo();
            fi.setFile(configFile);
            fi.setType(GeneratorConstants.FILE_TYPE_SERVLET_CONFIG);
            env.addGeneratedFile(fi);

            out =
                new IndentingWriter(
                    new OutputStreamWriter(new FileOutputStream(configFile)));
            portCount = 0;
            out.pln("# This file is generated by wscompile.");
            out.pln();
        } catch (IOException e) {
            fail("cant.write", configFile.toString());
        }
    }

    protected void postVisitService(Service service) throws Exception {
        try {
            out.pln("portcount=" + portCount);
            closeFile();
        } catch (IOException e) {
            fail("cant.write", configFile.toString());
        } finally {
            currentService = null;
        }
    }

    public void visit(Port port) throws Exception {
        int myPortNum = portCount;
        portCount = myPortNum + 1;
        operations = new TreeSet(new StringLenComparator());
        Iterator operations = port.getOperations();
        while (operations.hasNext()) {
            ((Operation) operations.next()).accept(this);
        }
        try {
            String portID = "port" + myPortNum;
            String servant = null;
            servant = port.getJavaInterface().getImpl();
            if (servant == null) {
                servant =
                    "Please specify the servant class for port:"
                        + port.getName().getLocalPart();
            }
            out.pln(portID + ".tie=" + env.getNames().tieFor(port));
            out.pln(portID + ".servant=" + servant);
            out.pln(portID + ".name=" + port.getName().getLocalPart());
            out.pln(
                portID
                    + ".wsdl.targetNamespace="
                    + model.getTargetNamespaceURI());
            out.pln(
                portID
                    + ".wsdl.serviceName="
                    + currentService.getName().getLocalPart());
            QName portName =
                (QName) port.getProperty(
                    ModelProperties.PROPERTY_WSDL_PORT_NAME);
            out.pln(portID + ".wsdl.portName=" + portName.getLocalPart());
        } catch (IOException e) {
            fail("generator.cant.write", configFile.toString());
        }
    }

    protected void visitOperation(Operation operation) throws Exception {
        operations.add(operation);
    }

    private void closeFile() throws IOException {
        if (out != null) {
            out.close();
            out = null;
        }
    }

    private String getBaseName(String s) {
        if (s.endsWith("Port")) {
            return s.substring(0, s.length() - 4);
        } else {
            return s;
        }
    }

    private String getPortName(String s) {
        return getBaseName(s) + "Port";
    }

    /**
     * Return the File object that should be used as the configuration
     * file for the given Java class, using the supplied destination
     * directory for the top of the package hierarchy.
     */
    protected static File configFileForClass(
        String className,
        File destDir,
        ProcessorEnvironment env)
        throws GeneratorException {
        File packageDir =
            DirectoryUtil.getOutputDirectoryFor(className, destDir, env);
        String outputName = Names.stripQualifier(className);

        String outputFileName = outputName + "_Config.properties";
        return new File(packageDir, outputFileName);
    }

    private static class StringLenComparator implements java.util.Comparator {
        public int compare(Object o1, Object o2) {
            int len1, len2;
            len1 = ((Operation) o1).getName().getLocalPart().length();
            len2 = ((Operation) o2).getName().getLocalPart().length();
            return (len1 <= len2) ? -1 : 1;
        }
    }
}
