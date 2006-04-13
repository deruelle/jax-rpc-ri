/*
 * $Id: EncodingException.java,v 1.2 2006-04-13 01:27:05 ofung Exp $
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

package com.sun.xml.rpc.encoding;

import com.sun.xml.rpc.util.exception.JAXRPCExceptionBase;
import com.sun.xml.rpc.util.localization.Localizable;

/**
 * EncodingException represents an exception that occurred while supporting
 * object serialization or deserialization.
 *
 * @see com.sun.xml.rpc.util.exception.JAXRPCExceptionBase
 *
 * @author JAX-RPC Development Team
 */
public class EncodingException extends JAXRPCExceptionBase {

    public EncodingException(String key) {
        super(key);
    }

    public EncodingException(String key, String arg) {
        super(key, arg);
    }

    public EncodingException(String key, Object[] args) {
        super(key, args);
    }

    public EncodingException(String key, Localizable arg) {
        super(key, arg);
    }

    public EncodingException(Localizable arg) {
        super("nestedEncodingError", arg);
    }

    public String getResourceBundleName() {
        return "com.sun.xml.rpc.resources.encoding";
    }

}
