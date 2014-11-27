/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.taunova.app.libview.components;

import com.taunova.app.libview.Item;
import com.taunova.app.libview.ShelfRenderer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Artem
 */
public abstract class AbstractShelfRenderer implements ShelfRenderer {

    public static int PREVIEW_WIDTH = 800;
    public static int PAGE_WIDTH = 1024;
    public static int ICON_WIDTH = 200;
    public static int ICON_HEIGHT = 300;
    public static int WIDTH_SPACE = 10;
    public static int HEIGTH_SPACE = 10;
    public static int PREVIEW_IMAGE_COUNT = 12;

    public AbstractShelfRenderer() {
    }

    public void paintImage(Graphics g, Image image, String name, int x, int y, int width, int height) {
        g.drawImage(image, x, y, width, height - 20, null);
        g.drawRect(x, y, width - 1, height - 20);

        paintImageTitle(g, name, x, y, width, height);
    }

    private void paintImageTitle(Graphics g, String name, int x, int y, int width, int height) {
        int fileNameLength = name.length();
        int widthDrawedString = 0;

        String drawedFileName = name;
        widthDrawedString = g.getFontMetrics().getStringBounds(drawedFileName, g).getBounds().width;

        while (widthDrawedString > width && fileNameLength != 0) {
            drawedFileName = name.substring(0, --fileNameLength);
            drawedFileName = drawedFileName.concat("...");
            widthDrawedString = g.getFontMetrics().getStringBounds(drawedFileName, g).getBounds().width;
        }

        int _width = (width - widthDrawedString) / 2;

        g.drawString(drawedFileName, x + _width, y + height - 5);
    }

    protected int getPadding() {
        return 0;
    }

    public ContentDescriptor drawContent(List<Item> items) {
        ContentDescriptor result = new ContentDescriptor();

        List<Coordinates> pointList = new ArrayList<Coordinates>();
        result.pointList = pointList;

        int space = getPadding();

        int imageCount = items.size();
        int columns = (PAGE_WIDTH - WIDTH_SPACE) / (ICON_WIDTH + WIDTH_SPACE);
        int rows = (int) Math.ceil((double) imageCount / columns);

        //        int _height = rows * (ICON_HEIGHT + HEIGTH_SPACE) + HEIGTH_SPACE;
        int _height = rows * (ICON_HEIGHT + HEIGTH_SPACE) + HEIGTH_SPACE + space;

        BufferedImage imageCanvas = new BufferedImage(PAGE_WIDTH, _height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = imageCanvas.createGraphics();
        g.clipRect(0, 0, PAGE_WIDTH, _height);

        g.setColor(Color.white);
        g.fillRect(0, 0, PAGE_WIDTH, _height);
        g.setColor(Color.black);

        int x = WIDTH_SPACE;
        int y = HEIGTH_SPACE;

        int index = 0;
        for (Item item : items) {

            //Image image = item.getThumbnailImage();
            Image image = null;
            double k = ((double) image.getHeight(null)) / image.getWidth(null);

            int width = ICON_WIDTH;
            int height = (int) (width * k);

            int c = index % columns;
            int r = index / columns;

            x = c * (width + WIDTH_SPACE) + WIDTH_SPACE;
            y = r * (height + HEIGTH_SPACE) + HEIGTH_SPACE + space;

            paintImage(g, image, item.getName(), x, y, width, height);
            pointList.add(new Coordinates(item.getName(), x, y));
            index++;
        }

        result.image = imageCanvas;
        return result;
    }
}

