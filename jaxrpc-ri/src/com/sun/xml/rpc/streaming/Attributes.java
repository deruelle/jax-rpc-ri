/*
 * $Id: Attributes.java,v 1.3 2007-07-13 23:36:32 ofung Exp $
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

import javax.xml.namespace.QName;

/**
 * <p> The Attributes interface is essentially a version of the
 * org.xml.sax.Attributes interface modified to use the JAX-RPC QName class.</p>
 *
 * <p> Although namespace declarations can appear in the attribute list, the
 * actual values of the local name and URI properties are
 * implementation-specific. </p>
 *
 * <p> Applications that need to iterate through all the attributes can use the
 * {@link #isNamespaceDeclaration} method to identify namespace declarations
 * and skip them. </p>
 *
 * <p> Also, the URI property of an attribute will never be null. The value
 * "" (empty string) is used for the URI of non-qualified attributes. </p>
 *
 * @author JAX-RPC Development Team
 */
public interface Attributes {

    /**
     * Return the number of attributes in the list.
     *
     */
    public int getLength();

    /**
     * Return true if the attribute at the given index is a namespace
     * declaration.
     *
     * <p> Implementations are encouraged to optimize this method by taking into
     * account their internal representations of attributes. </p>
     *
     */
    public boolean isNamespaceDeclaration(int index);

    /**
     * Look up an attribute's QName by index.
     *
     */
    public QName getName(int index);

    /**
     * Look up an attribute's URI by index.
     *
     */
    public String getURI(int index);

    /**
     * Look up an attribute's local name by index.
     *
     */
    public String getLocalName(int index);

    /**
     * Look up an attribute's prefix by index.
     *
     */
    public String getPrefix(int index);

    /**
     * Look up an attribute's value by index.
     *
     */
    public String getValue(int index);

    /**
     * Look up the index of an attribute by QName.
     *
     */
    public int getIndex(QName name);

    /**
     * Look up the index of an attribute by URI and local name.
     *
     */
    public int getIndex(String uri, String localName);

    /**
     * Look up the index of an attribute by local name.
     *
     */
    public int getIndex(String localName);

    /**
     * Look up the value of an attribute by QName.
     *
     */
    public String getValue(QName name);

    /**
     * Look up the value of an attribute by URI and local name.
     *
     */
    public String getValue(String uri, String localName);

    /**
     * Look up the value of an attribute by local name.
     *
     */
    public String getValue(String localName);
}
