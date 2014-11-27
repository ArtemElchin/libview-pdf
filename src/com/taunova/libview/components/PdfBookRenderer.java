/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.taunova.app.libview.components;

import com.taunova.app.libview.ShelfRenderer;
import com.taunova.app.libview.AbstractFileRenderer;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Artem
 */
class PdfBookRenderer extends AbstractFileRenderer {
    public static int PREVIEW_IMAGE_COUNT = 12;

    protected ShelfRenderer shelfRenderer = null;

    public PdfBookRenderer(ShelfRenderer shelfRenderer) {
        super();
        this.shelfRenderer = shelfRenderer;
    }

    public void processFile(File file) {
        Image image = null;
        Image mainImage = null;
        RandomAccessFile raf;
        List<Image> images = new LinkedList<Image>();
        List<String> names = new LinkedList<String>();

        try {
            raf = new RandomAccessFile(file, "r");

            FileChannel channel = raf.getChannel();
            ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
            PDFFile pdffile = new PDFFile(buf);

            int count = pdffile.getNumPages();
            count = Math.min(count, PREVIEW_IMAGE_COUNT);

            for (int i = 0; i <= count; i++) {
                PDFPage page = pdffile.getPage(i);

                if (null != page) {
                    int width = (int) page.getWidth();
                    int height = (int) page.getHeight();

                    PagePanel panel = new PagePanel();
                    panel.showPage(page);
                    image = page.getImage(width, height, new Rectangle(0, 0, width, height), panel, true, true);
                    images.add(image);

                    if (mainImage == null) {
                        mainImage = image;
                    }
                }

                names.add("page " + i);
            }

            titleImage = images.get(0);
            indexImage = shelfRenderer.drawContent(images, names).image;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}