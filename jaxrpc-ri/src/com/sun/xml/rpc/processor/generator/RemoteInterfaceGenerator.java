/*
 * $Id: RemoteInterfaceGenerator.java,v 1.2 2006-04-13 01:28:51 ofung Exp $
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
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import com.sun.xml.rpc.processor.ProcessorAction;
import com.sun.xml.rpc.processor.ProcessorConstants;
import com.sun.xml.rpc.processor.ProcessorOptions;
import com.sun.xml.rpc.processor.config.Configuration;
import com.sun.xml.rpc.processor.model.Model;
import com.sun.xml.rpc.processor.model.ModelProperties;
import com.sun.xml.rpc.processor.model.Port;
import com.sun.xml.rpc.processor.model.Service;
import com.sun.xml.rpc.processor.model.java.JavaInterface;
import com.sun.xml.rpc.processor.model.java.JavaMethod;
import com.sun.xml.rpc.processor.model.java.JavaParameter;
import com.sun.xml.rpc.processor.util.GeneratedFileInfo;
import com.sun.xml.rpc.processor.util.IndentingWriter;
import com.sun.xml.rpc.processor.util.ProcessorEnvironment;
import com.sun.xml.rpc.util.exception.LocalizableExceptionAdapter;

/**
 *
 * @author JAX-RPC Development Team
 */
public class RemoteInterfaceGenerator implements ProcessorAction {
    private boolean donotOverride;

    public RemoteInterfaceGenerator() {
    }

    public void perform(
        Model model,
        Configuration config,
        Properties options) {

        try {
            env = (ProcessorEnvironment) config.getEnvironment();
            env.getNames().resetPrefixFactory();
            String key = ProcessorOptions.SOURCE_DIRECTORY_PROPERTY;
            String dirPath = options.getProperty(key);
            key = ProcessorOptions.DONOT_OVERRIDE_CLASSES;
            donotOverride =
                Boolean.valueOf(options.getProperty(key)).booleanValue();
            sourceDir = new File(dirPath);
            Set interfaceNames = new HashSet();
            JAXRPCVersion =
                options.getProperty(ProcessorConstants.JAXRPC_VERSION);
            sourceVersion =
                options.getProperty(ProcessorOptions.JAXRPC_SOURCE_VERSION);

            String modelerName =
                (String) model.getProperty(
                    ModelProperties.PROPERTY_MODELER_NAME);
            if (modelerName != null
                && modelerName.equals(
                    "com.sun.xml.rpc.processor.modeler.rmi.RmiModeler")) {
                // do not generate a remote interface if the model was produced by the RMI modeler
                return;
            }

            for (Iterator iter = model.getServices(); iter.hasNext();) {
                Service service = (Service) iter.next();

                for (Iterator iter2 = service.getPorts(); iter2.hasNext();) {
                    Port port = (Port) iter2.next();
                    JavaInterface intf = port.getJavaInterface();
                    if (!interfaceNames.contains(intf.getName())) {
                        generateClassFor(port);
                        interfaceNames.add(intf.getName());
                    }
                }
            }
        } finally {
            sourceDir = null;
            env = null;
        }
    }

    private void generateClassFor(Port port) {
        JavaInterface intf = port.getJavaInterface();
        try {
            String className = env.getNames().customJavaTypeClassName(intf);
            if ((donotOverride && GeneratorUtil.classExists(env, className))) {
                log("Class " + className + " exists. Not overriding.");
                return;
            }
            File classFile =
                env.getNames().sourceFileForClass(
                    className,
                    className,
                    sourceDir,
                    env);

            /* adding the file name and its type */
            GeneratedFileInfo fi = new GeneratedFileInfo();
            fi.setFile(classFile);
            fi.setType(GeneratorConstants.FILE_TYPE_REMOTE_INTERFACE);
            env.addGeneratedFile(fi);

            IndentingWriter out =
                new IndentingWriter(
                    new OutputStreamWriter(new FileOutputStream(classFile)));
            GeneratorBase.writePackage(out, className, JAXRPCVersion, sourceVersion);
            out.plnI(
                "public interface "
                    + Names.stripQualifier(className)
                    + " extends java.rmi.Remote {");

            for (Iterator iter = intf.getMethods(); iter.hasNext();) {
                JavaMethod method = (JavaMethod) iter.next();
                out.p("public ");
                if (method.getReturnType() == null) {
                    out.p("void");
                } else {
                    out.p(method.getReturnType().getName());
                }
                out.p(" ");
                out.p(method.getName());
                out.p("(");
                boolean first = true;

                for (Iterator iter2 = method.getParameters();
                    iter2.hasNext();
                    ) {
                    JavaParameter parameter = (JavaParameter) iter2.next();
                    if (!first) {
                        out.p(", ");
                    }
                    if (parameter.isHolder()) {
                        out.p(
                            env.getNames().holderClassName(
                                port,
                                parameter.getType()));
                    } else {
                        out.p(
                            env.getNames().typeClassName(parameter.getType()));
                    }
                    out.p(" ");
                    out.p(parameter.getName());
                    first = false;
                }
                out.plnI(") throws ");
                Iterator exceptions = method.getExceptions();
                String exception;
                while (exceptions.hasNext()) {
                    exception = (String) exceptions.next();
                    out.p(exception + ", ");
                }
                out.pln(" java.rmi.RemoteException;");
                out.pO();
            }

            out.pOln("}");
            out.close();

        } catch (Exception e) {
            throw new GeneratorException(
                "generator.nestedGeneratorError",
                new LocalizableExceptionAdapter(e));
        }
    }

    private void log(String msg) {
        if (env.verbose()) {
            System.out.println(
                "["
                    + Names.stripQualifier(this.getClass().getName())
                    + ": "
                    + msg
                    + "]");
        }
    }

    private File sourceDir;
    private ProcessorEnvironment env;
    private String JAXRPCVersion;
    private String sourceVersion;
}
