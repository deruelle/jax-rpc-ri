/*
 * $Id: SOAPHeaderFault.java,v 1.2 2006-04-13 01:34:27 ofung Exp $
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

package com.sun.xml.rpc.wsdl.document.soap;

import javax.xml.namespace.QName;

import com.sun.xml.rpc.wsdl.framework.Extension;
import com.sun.xml.rpc.wsdl.framework.QNameAction;

/**
 * A SOAP header fault extension.
 *
 * @author JAX-RPC Development Team
 */
public class SOAPHeaderFault extends Extension {

    public SOAPHeaderFault() {
    }

    public QName getElementName() {
        return SOAPConstants.QNAME_HEADERFAULT;
    }

    public String getNamespace() {
        return _namespace;
    }

    public void setNamespace(String s) {
        _namespace = s;
    }

    public SOAPUse getUse() {
        return _use;
    }

    public void setUse(SOAPUse u) {
        _use = u;
    }

    public boolean isEncoded() {
        return _use == SOAPUse.ENCODED;
    }

    public boolean isLiteral() {
        return _use == SOAPUse.LITERAL;
    }

    public String getEncodingStyle() {
        return _encodingStyle;
    }

    public void setEncodingStyle(String s) {
        _encodingStyle = s;
    }

    public String getPart() {
        return _part;
    }

    public void setMessage(QName message) {
        _message = message;
    }

    public QName getMessage() {
        return _message;
    }

    public void setPart(String s) {
        _part = s;
    }

    public void withAllQNamesDo(QNameAction action) {
        super.withAllQNamesDo(action);

        if (_message != null) {
            action.perform(_message);
        }
    }

    public void validateThis() {
        if (_message == null) {
            failValidation("validation.missingRequiredAttribute", "message");
        }
        if (_part == null) {
            failValidation("validation.missingRequiredAttribute", "part");
        }
        if (_use == null) {
            failValidation("validation.missingRequiredAttribute", "use");
        }
    }

    private String _encodingStyle;
    private String _namespace;
    private String _part;
    private QName _message;
    private SOAPUse _use;
}
