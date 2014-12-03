/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.taunova.app.libview.components;

public class Coordinates {

    private String name;
    private String topRect;
    private String bottomRect;

    public Coordinates(String name, int x, int y) {
        this.name = name;

        int _x = x + LibraryAnalyzer.ICON_WIDTH;
        int _y = y + LibraryAnalyzer.ICON_HEIGHT / 2;

        topRect = new String(x + ","
                + y + ","
                + _x + ","
                + _y);
        bottomRect = new String(x + ","
                + _y + ","
                + _x + ","
                + (y + LibraryAnalyzer.ICON_HEIGHT));

    }

    public String getBookName() {
        return name;
    }

    public String getPreviewFileName() {
        return name.substring(0, name.lastIndexOf('.')) + ".html";
    }

    public String getTopRect() {
        return topRect;
    }

    public String getBottomRect() {
        return bottomRect;
    }
}
