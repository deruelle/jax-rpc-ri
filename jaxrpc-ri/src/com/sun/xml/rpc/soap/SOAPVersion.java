/*
 *$Id: SOAPVersion.java,v 1.2 2006-04-13 01:32:19 ofung Exp $
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

package com.sun.xml.rpc.soap;

/**
 * @author JAX-RPC Development Team
 */

public class SOAPVersion {

	private final String version;

	private SOAPVersion(String ver) {
		this.version = ver;
	}

	public String toString() {
		return this.version;
	}

	public boolean equals(String strVersion) {
		return version.equals(strVersion);
	}

	public static final SOAPVersion SOAP_11 = new SOAPVersion("soap1.1");
	public static final SOAPVersion SOAP_12 = new SOAPVersion("soap1.2");
}
