/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.taunova.app.libview;

import com.taunova.app.libview.components.*;
import java.awt.Image;
import java.util.List;

/**
 *
 * @author Artem
 */
public interface ShelfRenderer {

    class ContentDescriptor {
        public Image image = null;
        public List<Coordinates> pointList = null;
    }

    public ContentDescriptor drawContent(List<Item> items);
}
