/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.taunova.app.libview.components;

import com.taunova.app.libview.Item;
import java.awt.Image;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Artem
 */
class BookShelfRenderer extends AbstractShelfRenderer {
    public static int MAIN_ICON_WIDTH     = 400;
    public static int MAIN_ICON_HEIGHT    = 600;

    public BookShelfRenderer() {
        super();
    }

    @Override
    public ContentDescriptor drawContent(List<Item> itemList) {
        List<Item> items = new LinkedList();
        items.addAll(itemList);

        Item item = items.remove(0);
        String name = item.getName();
        //Image image = item.getThumbnailImage();
        Image image = null;

        ContentDescriptor descriptor = super.drawContent(items);
        Image result = descriptor.image;

        double k = ((double)image.getHeight(null))/image.getWidth(null);

        int width = MAIN_ICON_WIDTH;
        int height = (int)(MAIN_ICON_WIDTH * k);

        int x = WIDTH_SPACE;
        int y = HEIGTH_SPACE;

        paintImage(result.getGraphics(), image, name, x, y, width, height);

        return descriptor;
    }

    @Override
    protected int getPadding() {
        return MAIN_ICON_HEIGHT + HEIGTH_SPACE;
    }
}
