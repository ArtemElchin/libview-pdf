/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.taunova.app.libview.components;

import com.taunova.app.libview.AbstractFileRenderer;
import java.io.File;

/**
 *
 * @author Artem
 */
public class DjvuBookRenderer extends AbstractFileRenderer {

    public void processFile(File file) {
            // djvu book renderer
    }
    /*
    public Image addDJVUBook(File file) {

        int tryNumber = 0;
        Image image = null;

        DjVuBean djvuBean = new DjVuBean();

        try {
            djvuBean.setURL(new URL("file:" + file.getAbsolutePath()));

            JFrame frame = new JFrame();
            frame.add(djvuBean);
            frame.pack();

            DjVuImage djvuImage = null;

            //System.out.println("Setting page: " + page);
            //djvuBean.setPage(page);
            djvuImage = djvuBean.getImage();

            while (djvuImage.isDecoding()) {
                System.out.println("is decoding");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }

                if (tryNumber++ > DECODING_TRIES) {
                    System.out.println("Can not decode file: " + file.getAbsolutePath());
                    return null;
                }
            }

            Dimension size = djvuImage.getSize();
            int width = size.width;
            int height = size.height;

            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedImage.createGraphics();
            g2d.clipRect(0, 0, width, height);

            JPanel panel = new JPanel();
            panel.setSize(width, height);
            djvuImage.draw(panel, g2d, panel);

            image = bufferedImage;
        } catch (Exception e) {
            System.out.println("Can not open file:" + file.getAbsolutePath());
        }

        return image;
    }
*/

}
