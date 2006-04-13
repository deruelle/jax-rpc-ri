/*
 * $Id: ServiceExceptionImpl.java,v 1.2 2006-04-13 01:26:35 ofung Exp $
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

package com.sun.xml.rpc.client;

import java.io.PrintStream;
import java.io.PrintWriter;

import javax.xml.rpc.ServiceException;

import com.sun.xml.rpc.util.exception.NestableExceptionSupport;
import com.sun.xml.rpc.util.localization.Localizable;
import com.sun.xml.rpc.util.localization.LocalizableSupport;
import com.sun.xml.rpc.util.localization.Localizer;

/**
 * @author JAX-RPC Development Team
 */
public class ServiceExceptionImpl
    extends ServiceException
    implements Localizable {
    protected LocalizableSupport localizablePart;
    protected NestableExceptionSupport nestablePart;

    public ServiceExceptionImpl() {
        nestablePart = new NestableExceptionSupport();
    }

    public ServiceExceptionImpl(String key) {
        this();
        localizablePart = new LocalizableSupport(key);
    }

    public ServiceExceptionImpl(String key, String arg) {
        this();
        localizablePart = new LocalizableSupport(key, arg);
    }

    public ServiceExceptionImpl(String key, Localizable localizable) {
        this(key, new Object[] { localizable });
    }

    public ServiceExceptionImpl(String key, Object[] args) {
        this();
        localizablePart = new LocalizableSupport(key, args);
        if (args != null && nestablePart.getCause() == null) {
            for (int i = 0; i < args.length; ++i) {
                if (args[i] instanceof Throwable) {
                    nestablePart.setCause((Throwable) args[i]);
                    break;
                }
            }
        }
    }

    public ServiceExceptionImpl(Localizable arg) {
        this("service.exception.nested", arg);
    }

    public String getResourceBundleName() {
        return "com.sun.xml.rpc.resources.dii";
    }

    public String getKey() {
        return localizablePart.getKey();
    }

    public Object[] getArguments() {
        return localizablePart.getArguments();
    }

    public String toString() {
        // for debug purposes only
        //return getClass().getName() + " (" + getKey() + ")";
        return getMessage();
    }

    public String getMessage() {
        Localizer localizer = new Localizer();
        return localizer.localize(this);
    }

    public Throwable getLinkedException() {
        return nestablePart.getCause();
    }

    public void printStackTrace() {
        super.printStackTrace();
        nestablePart.printStackTrace();
    }

    public void printStackTrace(PrintStream s) {
        super.printStackTrace(s);
        nestablePart.printStackTrace(s);
    }

    public void printStackTrace(PrintWriter s) {
        super.printStackTrace(s);
        nestablePart.printStackTrace(s);
    }
}
