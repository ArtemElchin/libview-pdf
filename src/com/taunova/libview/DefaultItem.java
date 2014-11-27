/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.taunova.app.libview;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

public class DefaultItem implements Item {

    protected String name;
    protected String description;

    protected String subLink;
    protected String downloadLink;
    protected String thubnailImage;
    protected String previewImage;

    protected Image thubnail;
    protected Map attributes = new HashMap();

    public DefaultItem(String name, String tubnailImage) {
        this.name = name;
        this.thubnailImage = tubnailImage;
    }

    public DefaultItem(String name, Image thubnail, String thubnailImage, String previewImage) {
        this.name = name;
        this.thubnail = thubnail;
        this.thubnailImage = thubnailImage;
        this.previewImage = previewImage;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isComposit() {
        return (null != subLink);
    }

    public void setSubLink(String link) {
        this.subLink = link;
    }

    public String getInternalLink() {
        return subLink;
    }

    public boolean isFile() {
        return (null != downloadLink);
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public String getThumbnailLink() {
        return thubnailImage;
    }

    public String getPreviewLink() {
        return previewImage;
    }

    public Map getAttributes() {
        return attributes;
    }

    public Image getThumbnailImage() {
        return thubnail;
    }

}