/*
 * $Id: XSDDoubleEncoder.java,v 1.2 2006-04-13 01:27:53 ofung Exp $
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
public class XSDDoubleEncoder extends SimpleTypeEncoderBase {
    private static final SimpleTypeEncoder encoder = new XSDDoubleEncoder();

    private XSDDoubleEncoder() {
    }

    public static SimpleTypeEncoder getInstance() {
        return encoder;
    }

    public String objectToString(Object obj, XMLWriter writer)
        throws Exception {
            
        if (obj == null) {
            return null;
        }
        Double d = (Double) obj;
        double dVal = d.doubleValue();
        if (d.isInfinite()) {
            if (dVal == Double.NEGATIVE_INFINITY) {
                return "-INF";
            } else {
                return "INF";
            }
        } else if (d.isNaN()) {
            return "NaN";
        }
        return d.toString();
    }

    public Object stringToObject(String str, XMLReader reader)
        throws Exception {
            
        if (str == null) {
            return null;
        }
        str = EncoderUtils.collapseWhitespace(str);
        if (str.equals("-INF")) {
            return new Double(Double.NEGATIVE_INFINITY);
        } else if (str.equals("INF")) {
            return new Double(Double.POSITIVE_INFINITY);
        } else if (str.equals("NaN")) {
            return new Double(Double.NaN);
        }
        return new Double(str);
    }

    public void writeValue(Object obj, XMLWriter writer) throws Exception {
        writer.writeCharsUnquoted(objectToString(obj, writer));
    }
}
