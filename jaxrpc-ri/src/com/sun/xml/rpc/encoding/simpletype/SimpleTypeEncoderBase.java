/*
 * $Id: SimpleTypeEncoderBase.java,v 1.2 2006-04-13 01:27:44 ofung Exp $
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

package com.sun.xml.rpc.encoding.simpletype;

import com.sun.xml.rpc.streaming.XMLReader;
import com.sun.xml.rpc.streaming.XMLWriter;

/**
 *
 * @author JAX-RPC Development Team
 */
public abstract class SimpleTypeEncoderBase implements SimpleTypeEncoder {

    public abstract String objectToString(Object obj, XMLWriter writer)
        throws Exception;
        
    public abstract Object stringToObject(String str, XMLReader reader)
        throws Exception;

    public void writeValue(Object obj, XMLWriter writer) throws Exception {
        // NOTE - this method is only called when the value (obj, that is) is non-null
        writer.writeChars(objectToString(obj, writer));
    }

    public void writeAdditionalNamespaceDeclarations(
        Object obj,
        XMLWriter writer)
        throws Exception {
        // no-op
    }
}
