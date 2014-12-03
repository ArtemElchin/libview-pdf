/*
 * Copyright 2009 TauNova (http://taunova.com). All rights reserved.
 * 
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.taunova.app.libview.components;

import com.taunova.app.libview.Item;
import com.taunova.app.libview.ShelfRenderer;
import java.util.List;

/**
 *
 * @author Artem
 */
public class IndexShelfRenderer implements ShelfRenderer {

    public IndexShelfRenderer() {

    }

    public ContentDescriptor drawContent(List<Item> items) {
        ContentDescriptor result = new ContentDescriptor();
        return result;
    }
}
