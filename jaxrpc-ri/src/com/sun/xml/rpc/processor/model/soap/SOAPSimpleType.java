/*
 * $Id: SOAPSimpleType.java,v 1.2 2006-04-13 01:30:05 ofung Exp $
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

package com.sun.xml.rpc.processor.model.soap;

import javax.xml.namespace.QName;

import com.sun.xml.rpc.processor.model.java.JavaSimpleType;
import com.sun.xml.rpc.soap.SOAPVersion;

/**
 *
 * @author JAX-RPC Development Team
 */
public class SOAPSimpleType extends SOAPType {
    
    public SOAPSimpleType() {}
    
    public SOAPSimpleType(QName name) {
        this(name, null);
    }
    
    public SOAPSimpleType(QName name, JavaSimpleType javaType) {
        this(name, javaType, true);
    }
    
    public SOAPSimpleType(QName name, JavaSimpleType javaType,
        SOAPVersion version) {
        
        this(name, javaType, true, version);
    }
    
    public SOAPSimpleType(QName name, JavaSimpleType javaType,
        boolean referenceable) {
        
        this(name, javaType, referenceable, SOAPVersion.SOAP_11);
    }
    
    public SOAPSimpleType(QName name, JavaSimpleType javaType,
        boolean referenceable, SOAPVersion version) {
        
        super(name, javaType, version);
        this.referenceable = referenceable;
    }
    
    public QName getSchemaTypeRef() {
        return schemaTypeRef;
    }
    
    public void setSchemaTypeRef(QName n) {
        schemaTypeRef = n;
    }
    
    public boolean isReferenceable() {
        return referenceable;
    }
    
    public void setReferenceable(boolean b) {
        referenceable = b;
    }
    
    public void accept(SOAPTypeVisitor visitor) throws Exception {
        visitor.visit(this);
    }
    
    private QName schemaTypeRef;
    private boolean referenceable;
}
