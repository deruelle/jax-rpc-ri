/*
 * $Id: LoggingXMLReader.java,v 1.3 2007-07-13 23:36:33 ofung Exp $
 */

/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 * 
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License. You can obtain
 * a copy of the License at https://glassfish.dev.java.net/public/CDDL+GPL.html
 * or glassfish/bootstrap/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at glassfish/bootstrap/legal/LICENSE.txt.
 * Sun designates this particular file as subject to the "Classpath" exception
 * as provided by Sun in the GPL Version 2 section of the License file that
 * accompanied this code.  If applicable, add the following below the License
 * Header, with the fields enclosed by brackets [] replaced by your own
 * identifying information: "Portions Copyrighted [year]
 * [name of copyright owner]"
 * 
 * Contributor(s):
 * 
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package com.sun.xml.rpc.streaming;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.xml.namespace.QName;

/**
 *
 * @author JAX-RPC Development Team
 */
public class LoggingXMLReader implements XMLReader {
    PrintWriter log;
    XMLReader reader;

    public LoggingXMLReader(OutputStream log, XMLReader reader) {
        this(new PrintWriter(log), reader);
    }

    public LoggingXMLReader(PrintWriter log, XMLReader reader) {
        this.log = log;
        this.reader = reader;
    }

    public int next() {
        int nextState = reader.next();
        log.println("Next state: " + XMLReaderUtil.getStateName(reader));
        return nextState;
    }
    public int nextContent() {
        int nextState = reader.nextContent();
        log.println(
            "Next content state: " + XMLReaderUtil.getStateName(reader));
        return nextState;
    }
    public int nextElementContent() {
        int nextState = reader.nextElementContent();
        log.println(
            "Next element content state: "
                + XMLReaderUtil.getStateName(reader));
        return nextState;
    }
    public int getState() {
        int currentState = reader.getState();
        log.println("Current state: " + XMLReaderUtil.getStateName(reader));
        return currentState;
    }
    public QName getName() {
        QName name = reader.getName();
        log.println("name: " + name);
        return name;
    }
    public String getURI() {
        String uri = reader.getURI();
        log.println("uri: " + uri);
        return uri;
    }
    public String getLocalName() {
        String localName = reader.getLocalName();
        log.println("localName: " + localName);
        return localName;
    }
    public Attributes getAttributes() {
        Attributes attributes = reader.getAttributes();
        log.println("attributes: " + attributes);
        return attributes;
    }
    public String getValue() {
        String value = reader.getValue();
        log.println("value: " + value);
        return value;
    }
    public int getElementId() {
        int id = reader.getElementId();
        log.println("id: " + id);
        return id;
    }
    public int getLineNumber() {
        int lineNumber = reader.getLineNumber();
        log.println("lineNumber: " + lineNumber);
        return lineNumber;
    }
    public String getURI(String prefix) {
        String uri = reader.getURI(prefix);
        log.println("uri for: " + prefix + ": " + uri);
        return uri;
    }
    public Iterator getPrefixes() {
        return reader.getPrefixes();
    }
    public XMLReader recordElement() {
        return reader.recordElement();
    }
    public void skipElement() {
        reader.skipElement();
        log.println("Skipped to: " + XMLReaderUtil.getStateName(reader));
    }
    public void skipElement(int elementId) {
        reader.skipElement(elementId);
        log.println("Skipped to: " + XMLReaderUtil.getStateName(reader));
    }
    public void close() {
        reader.close();
        log.println("reader closed");
    }
}