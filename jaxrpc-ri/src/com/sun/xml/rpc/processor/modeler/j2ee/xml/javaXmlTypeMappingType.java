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

// @(#) 1.1 jsr109ri/src/java/com/ibm/webservices/ri/xml/jaxrpcmap1_1/javaXmlTypeMappingType.java, jsr109ri, jsr10911, b0240.03 9/30/02 11:33:39 [10/7/02 11:55:19]
/*************************************************************************
   Licensed Materials - Property of IBM
   5639-D57, 5630-A36, 5630-A37, 5724-D18 (C) COPYRIGHT International Business M
achines Corp. 2002
   All Rights Reserved
   US Government Users Restricted Rights - Use, duplication, or
   disclosure restricted by GSA ADP Schedule Contract  with
   IBM Corp.
**************************************************************************/
package com.sun.xml.rpc.processor.modeler.j2ee.xml;

/**
* This class represents the complex type <javaXmlTypeMappingType>
*/
public class javaXmlTypeMappingType extends ComplexType {
    public javaXmlTypeMappingType() {
    }

    public void setJavaType(javaTypeType javaType) {
        setElementValue("java-type", javaType);
    }

    public javaTypeType getJavaType() {
        return (javaTypeType) getElementValue("java-type", "javaTypeType");
    }

    public boolean removeJavaType() {
        return removeElement("java-type");
    }

    public void setRootTypeQname(xsdQNameType rootTypeQname) {
        setElementValue("root-type-qname", rootTypeQname);
    }

    public xsdQNameType getRootTypeQname() {
        return (xsdQNameType) getElementValue(
            "root-type-qname",
            "xsdQNameType");
    }

    public boolean removeRootTypeQname() {
        return removeElement("root-type-qname");
    }

    public void setAnonymousTypeQname(string anonymousTypeQname) {
        setElementValue("anonymous-type-qname", anonymousTypeQname);
    }

    public string getAnonymousTypeQname() {
        return (string) getElementValue("anonymous-type-qname", "string");
    }

    public boolean removeAnonymousTypeQname() {
        return removeElement("anonymous-type-qname");
    }

    public void setQnameScope(qnameScopeType qnameScope) {
        setElementValue("qname-scope", qnameScope);
    }

    public qnameScopeType getQnameScope() {
        return (qnameScopeType) getElementValue(
            "qname-scope",
            "qnameScopeType");
    }

    public boolean removeQnameScope() {
        return removeElement("qname-scope");
    }

    public void setVariableMapping(
        int index,
        variableMappingType variableMapping) {
        setElementValue(index, "variable-mapping", variableMapping);
    }

    public variableMappingType getVariableMapping(int index) {
        return (variableMappingType) getElementValue(
            "variable-mapping",
            "variableMappingType",
            index);
    }

    public int getVariableMappingCount() {
        return sizeOfElement("variable-mapping");
    }

    public boolean removeVariableMapping(int index) {
        return removeElement(index, "variable-mapping");
    }

    public void setId(String id) {
        setAttributeValue("id", id);
    }

    public String getId() {
        return getAttributeValue("id");
    }

    public boolean removeId() {
        return removeAttribute("id");
    }

}
