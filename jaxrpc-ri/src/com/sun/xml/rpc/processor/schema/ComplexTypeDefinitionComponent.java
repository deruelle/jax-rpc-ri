/*
 * $Id: ComplexTypeDefinitionComponent.java,v 1.2 2006-04-13 01:31:37 ofung Exp $
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

package com.sun.xml.rpc.processor.schema;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author JAX-RPC Development Team
 */
public class ComplexTypeDefinitionComponent extends TypeDefinitionComponent {
    
    public static final int CONTENT_EMPTY = 1;
    public static final int CONTENT_SIMPLE = 2;
    public static final int CONTENT_MIXED = 3;
    public static final int CONTENT_ELEMENT_ONLY = 4;
    
    public ComplexTypeDefinitionComponent() {
        _attributeUses = new ArrayList();
    }
    
    public boolean isComplex() {
        return true;
    }
    
    public TypeDefinitionComponent getBaseTypeDefinition() {
        return _baseTypeDefinition;
    }
    
    public void setBaseTypeDefinition(TypeDefinitionComponent c) {
        _baseTypeDefinition = c;
    }
    
    public Symbol getDerivationMethod() {
        return _derivationMethod;
    }
    
    public void setDerivationMethod(Symbol s) {
        _derivationMethod = s;
    }
    
    public void setProhibitedSubstitutions(Set s) {
        _prohibitedSubstitutions = s;
    }
    
    public void setFinal(Set s) {
        _final = s;
    }
    
    public boolean isAbstract() {
        return _abstract;
    }
    
    public void setAbstract(boolean b) {
        _abstract = b;
    }
    
    public Iterator attributeUses() {
        return _attributeUses.iterator();
    }
    
    public boolean hasNoAttributeUses() {
        return _attributeUses.size() == 0;
    }
    
    public void addAttributeUse(AttributeUseComponent c) {
        _attributeUses.add(c);
    }
    
    public void addAttributeGroup(AttributeGroupDefinitionComponent c) {
        for (Iterator iter = c.attributeUses(); iter.hasNext();) {
            AttributeUseComponent a = (AttributeUseComponent) iter.next();
            addAttributeUse(a);
        }
    }
    
    public int getContentTag() {
        return _contentTag;
    }
    
    public void setContentTag(int i) {
        _contentTag = i;
    }
    
    public SimpleTypeDefinitionComponent getSimpleTypeContent() {
        return _simpleTypeContent;
    }
    
    public void setSimpleTypeContent(SimpleTypeDefinitionComponent c) {
        _simpleTypeContent = c;
    }
    
    public ParticleComponent getParticleContent() {
        return _particleContent;
    }
    
    public void setParticleContent(ParticleComponent c) {
        _particleContent = c;
    }
    
    public void accept(ComponentVisitor visitor) throws Exception {
        visitor.visit(this);
    }
    
    private TypeDefinitionComponent _baseTypeDefinition;
    private Symbol _derivationMethod;
    private Set _final;
    private boolean _abstract;
    private List _attributeUses;
    private WildcardComponent _attributeWildcard;
    private int _contentTag;
    private SimpleTypeDefinitionComponent _simpleTypeContent;
    private ParticleComponent _particleContent;
    private Set _prohibitedSubstitutions;
    private List _annotations;
}
