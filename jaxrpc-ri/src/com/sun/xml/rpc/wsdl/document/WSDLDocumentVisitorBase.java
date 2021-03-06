/*
 * $Id: WSDLDocumentVisitorBase.java,v 1.3 2007-07-13 23:36:41 ofung Exp $
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

package com.sun.xml.rpc.wsdl.document;

import com.sun.xml.rpc.wsdl.framework.ExtensionVisitorBase;


/**
 *
 * @author JAX-RPC Development Team
 */
public class WSDLDocumentVisitorBase extends ExtensionVisitorBase {
    public WSDLDocumentVisitorBase() {
    }

    public void preVisit(Definitions definitions) throws Exception {
    }
    public void postVisit(Definitions definitions) throws Exception {
    }
    public void visit(Import i) throws Exception {
    }
    public void preVisit(Types types) throws Exception {
    }
    public void postVisit(Types types) throws Exception {
    }
    public void preVisit(Message message) throws Exception {
    }
    public void postVisit(Message message) throws Exception {
    }
    public void visit(MessagePart part) throws Exception {
    }
    public void preVisit(PortType portType) throws Exception {
    }
    public void postVisit(PortType portType) throws Exception {
    }
    public void preVisit(Operation operation) throws Exception {
    }
    public void postVisit(Operation operation) throws Exception {
    }
    public void preVisit(Input input) throws Exception {
    }
    public void postVisit(Input input) throws Exception {
    }
    public void preVisit(Output output) throws Exception {
    }
    public void postVisit(Output output) throws Exception {
    }
    public void preVisit(Fault fault) throws Exception {
    }
    public void postVisit(Fault fault) throws Exception {
    }
    public void preVisit(Binding binding) throws Exception {
    }
    public void postVisit(Binding binding) throws Exception {
    }
    public void preVisit(BindingOperation operation) throws Exception {
    }
    public void postVisit(BindingOperation operation) throws Exception {
    }
    public void preVisit(BindingInput input) throws Exception {
    }
    public void postVisit(BindingInput input) throws Exception {
    }
    public void preVisit(BindingOutput output) throws Exception {
    }
    public void postVisit(BindingOutput output) throws Exception {
    }
    public void preVisit(BindingFault fault) throws Exception {
    }
    public void postVisit(BindingFault fault) throws Exception {
    }
    public void preVisit(Service service) throws Exception {
    }
    public void postVisit(Service service) throws Exception {
    }
    public void preVisit(Port port) throws Exception {
    }
    public void postVisit(Port port) throws Exception {
    }
    public void visit(Documentation documentation) throws Exception {
    }
}
