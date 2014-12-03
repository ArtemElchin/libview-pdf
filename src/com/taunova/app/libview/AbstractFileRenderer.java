/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.taunova.app.libview;

import java.awt.Image;

/**
 *
 * @author Artem
 */
public abstract class AbstractFileRenderer implements FileRenderer {

    protected Image titleImage = null;
    protected Image indexImage = null;

    public AbstractFileRenderer() {
    }

    public Image getFileTitle() {
        return titleImage;
    }

    public Image getFileIndex() {
        return indexImage;
    }
}

