/*
 * $Id: SOAPBlockInfo.java,v 1.2 2006-04-13 01:32:22 ofung Exp $
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

package com.sun.xml.rpc.soap.message;

import javax.xml.namespace.QName;

import com.sun.xml.rpc.encoding.JAXRPCSerializer;

/**
 * @author JAX-RPC Development Team
 */
public class SOAPBlockInfo {

	public SOAPBlockInfo(QName name) {
		_name = name;
	}

	public QName getName() {
		return _name;
	}

	public Object getValue() {
		return _value;
	}

	public void setValue(Object value) {
		_value = value;
	}

	public JAXRPCSerializer getSerializer() {
		return _serializer;
	}

	public void setSerializer(JAXRPCSerializer s) {
		_serializer = s;
	}

	private QName _name;
	private Object _value;
	private JAXRPCSerializer _serializer;
}
