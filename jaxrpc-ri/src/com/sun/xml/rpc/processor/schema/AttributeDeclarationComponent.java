/*
 * $Id: AttributeDeclarationComponent.java,v 1.3 2007-07-13 23:36:19 ofung Exp $
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

package com.sun.xml.rpc.processor.schema;

import javax.xml.namespace.QName;

/**
 *
 * @author JAX-RPC Development Team
 */
public class AttributeDeclarationComponent extends Component {
    
    public AttributeDeclarationComponent() {}
    
    public QName getName() {
        return _name;
    }
    
    public void setName(QName name) {
        _name = name;
    }
    
    public SimpleTypeDefinitionComponent getTypeDefinition() {
        return _typeDefinition;
    }
    
    public void setTypeDefinition(SimpleTypeDefinitionComponent c) {
        _typeDefinition = c;
    }
    
    public ComplexTypeDefinitionComponent getScope() {
        return _scope;
    }
    
    public void setScope(ComplexTypeDefinitionComponent c) {
        _scope = c;
    }
    
    public void setValue(String s) {
        _value = s;
    }
    
    // bug fix: 4968046
    public String getValue() {
        return _value;
    }

    public void setValueKind(Symbol s) {
        _valueKind = s;
    }
    
    // bug fix: 4968046
    public Symbol getValueKind() {
        return _valueKind;
    }

    
    public AnnotationComponent getAnnotation() {
        return _annotation;
    }
    
    public void setAnnotation(AnnotationComponent c) {
        _annotation = c;
    }
    
    public void accept(ComponentVisitor visitor) throws Exception {
        visitor.visit(this);
    }
    
    private QName _name;
    private SimpleTypeDefinitionComponent _typeDefinition;
    private ComplexTypeDefinitionComponent _scope;
    private String _value;
    private Symbol _valueKind;
    private AnnotationComponent _annotation;
    
}
