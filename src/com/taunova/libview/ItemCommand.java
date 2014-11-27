/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.taunova.app.libview;

import java.io.File;
import java.util.Map;

/**
 *
 * @author Artem
 */
public interface ItemCommand {
    String getType();
    Item process(File item, Map context, Map properties) throws ItemProcessingException;
}
