/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.taunova.app.libview;

import com.taunova.app.libview.components.*;
import java.io.File;
import java.util.List;

/**
 *
 * @author Artem
 */
public interface TemplateEngine {
    void createHtmlPreview(File indexFile);
    void createHtmlIndex(List<Coordinates> pointlist, File indexFile);
    void createIndex(Node root, File indexFile);
}