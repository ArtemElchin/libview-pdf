/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.taunova.app.libview;

import java.io.File;

/**
 *
 * @author Artem
 */
public interface CommandRegistry {
    boolean containsCommand(File file);
    ItemCommand getCommand(File file);
    void addCommand(ItemCommand command);
}
