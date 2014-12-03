/*
 * Copyright 2009 TauNova (http://taunova.com). All rights reserved.
 * 
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.taunova.app.libview;

import org.apache.log4j.Logger;

/**
 *
 * @author Artem
 */
public final class Log {

    public static Logger logger = null;
    public static final boolean INFO = true;
    public static final boolean ERROR = true;
    public static final boolean DEBUG = true;

    public static void init() {
        logger = Logger.getLogger("libary");
    }
}

