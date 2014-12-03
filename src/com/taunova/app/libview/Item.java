/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.taunova.app.libview;

import java.awt.Image;
import java.util.Map;

/**
 *
 * @author Artem
 */
public interface Item {

    boolean isFile();

    boolean isComposit();

    String getName();

    String getDescription();

    String getInternalLink();

    String getDownloadLink();

    String getThumbnailLink();

    String getPreviewLink();

    Map getAttributes();

//    Image getThumbnailImage();
}
