/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.taunova.app.libview.components;

import com.mortennobel.imagescaling.AdvancedResizeOp;
import com.mortennobel.imagescaling.ResampleOp;
import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Artem
 */
public class ImageHelpers {

    public static void storeImage(Image image, File file) {

        try {
            ImageIO.write((RenderedImage) image, "png", file);
        } catch (IOException e) {
            System.out.println("Can not save file: " + file.getAbsolutePath());
        }
    }

    public static Dimension getScaledDimension(Image image, int width) {
        double k = ((double) image.getHeight(null)) / image.getWidth(null);
        int height = (int) (width * k);

        return new Dimension(width, height);
    }


    public static Image getScaledImage(BufferedImage image, int width) {
        Dimension d = getScaledDimension(image, width);
        Image scaledImage = image.getScaledInstance(d.width, d.height, Image.SCALE_SMOOTH);

        BufferedImage resizedImage = null;

        final boolean OPTIMIZE_SCALE = true;

        if (OPTIMIZE_SCALE) {
            ResampleOp resampleOp = new ResampleOp(100, 200);
            resampleOp.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.Normal);
            resizedImage = resampleOp.filter(image, null);
        } else {
            resizedImage = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = resizedImage.createGraphics();
            g.setComposite(AlphaComposite.Src);
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.drawImage(scaledImage, 0, 0, d.width, d.height, null);
            g.dispose();
        }

        return resizedImage;
    }

    public static Image getImageThubnail(BufferedImage image) {
        return getScaledImage(image, AbstractShelfRenderer.ICON_WIDTH);
    }

    public static Image getImagePreview(BufferedImage image) {
        return getScaledImage(image, AbstractShelfRenderer.PREVIEW_WIDTH);
    }

    public static File addPrefixToFile(File file, String prefix) {
        String name = FilenameUtils.getBaseName(file.getName());
        String extension = FilenameUtils.getExtension(file.getName());

        StringBuilder builder = new StringBuilder(file.getParent());
        builder.append(File.separator);
        builder.append(name);
        builder.append(prefix);
        builder.append('.');
        builder.append(extension);
        return new File(builder.toString());
    }

    public static File getThumbnailFile(File file) {
        return addPrefixToFile(file, "_thumb");
    }

    public static File getPreviewFile(File file) {
        return addPrefixToFile(file, "_preview");
    }

    public static void storeThumbnail(Image image, File file) {
        //File file = getThumbnailFile(parent);
        storeImage(image, file);
        //return file;
    }

    public static void storePreview(Image image, File file) {
        //File file = getPreviewFile(parent);
        storeImage(image, file);
        //return file;
    }
}
