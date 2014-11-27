/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.taunova.app.libview.components;


import com.taunova.app.libview.ShelfRenderer;
import com.taunova.app.libview.AbstractFileRenderer;
import java.awt.Image;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author Artem
 */
class IcePdfBookRenderer extends AbstractFileRenderer {
    public static int PREVIEW_IMAGE_COUNT = 12;

    protected ShelfRenderer shelfRenderer = null;

    public IcePdfBookRenderer(ShelfRenderer shelfRenderer) {
        super();
        this.shelfRenderer = shelfRenderer;
    }

    public void processFile(File file) {
        List<Image> images = new LinkedList<Image>();
        List<String> names = new LinkedList<String>();

                // open the url
        Document document = new Document();
        try {
            document.setFile(file.getAbsolutePath());
        } catch (Exception ex) {
            System.out.println("Error handling PDF document " + ex);
        }

                // save page caputres to file.
        float scale = 1.0f;
        float rotation = 0f;

        // Paint each pages content to an image and write the image to file
        for (int i = 0; i < document.getNumberOfPages(); i++) {
            BufferedImage image = (BufferedImage)
                    document.getPageImage(i,
                                          GraphicsRenderingHints.SCREEN,
                                          Page.BOUNDARY_CROPBOX, rotation, scale);
            RenderedImage rendImage = image;

            images.add(image);
            names.add("page " + i);
            
            if (titleImage == null) {
                  titleImage = image;
            }
            //image.flush();
        }
        // clean up resources
        document.dispose();
        indexImage = shelfRenderer.drawContent(images, names).image;

    }
}