/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.taunova.app.libview.command;

import com.taunova.app.libview.Constants;
import com.taunova.app.libview.Item;
import com.taunova.app.libview.ItemCommand;
import com.taunova.app.libview.DefaultItem;

import com.taunova.app.libview.components.ImageHelpers;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 *
 * @author Artem
 */
public abstract class AbstractImageCommand implements ItemCommand {

    public abstract String getType();

    public Item process(File file, Map context, Map properties) {
        BufferedImage main = null;

        String indexDir = (String) properties.get(Constants.PROP_INDEX_DIR);
        String relativeDir = (String) context.get(Constants.PROP_RELATIVE_PATH);

        StringBuilder imagePath = new StringBuilder(indexDir);
        imagePath.append(File.separator);
        imagePath.append(relativeDir);
        imagePath.append(File.separator);
        imagePath.append(file.getName());

        File imageFile = new File(imagePath.toString());
        File previewFile = ImageHelpers.getPreviewFile(imageFile);
        File thumbnailFile = ImageHelpers.getThumbnailFile(imageFile);


        if (!previewFile.isFile() || !thumbnailFile.isFile()) {
            try {
                main = ImageIO.read(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            Image thumbnail = ImageHelpers.getImageThubnail(main);
            Image preview = ImageHelpers.getImagePreview(main);

            ImageHelpers.storePreview(preview, previewFile);
            ImageHelpers.storeThumbnail(thumbnail, thumbnailFile);
        }

        Item result = new DefaultItem(imageFile.getName(),
                thumbnailFile.getName(),
                previewFile.getName());

        return result;
    }
}
