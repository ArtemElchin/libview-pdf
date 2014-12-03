/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.taunova.app.libview;

import com.taunova.app.libview.components.LibraryAnalyzer;

import java.io.File;

public class LibraryViewer {

    public static int PAGE_WIDTH = 1024;
    public static int ICON_WIDTH = 200;
    public static int ICON_HEIGHT = 300;
    public static int WIDTH_SPACE = 10;
    public static int HEIGTH_SPACE = 10;

    public static void main(String[] args) {
        try {
            Log.init();
            System.out.println("Library viewer");
            org.apache.log4j.BasicConfigurator.configure();
            File input = new File("/1/libary/");
            File output = new File("/1/libary/");
            Log.logger.info("Starting Library Viewer");

            new LibraryViewer().scan(input, output);
        } catch (ItemProcessingException ex) {
            Log.logger.error("Error starting LibView", ex);
            ex.printStackTrace();
        }
    }

    private void scan(File libraryDir, File indexDir) throws ItemProcessingException {
        if (libraryDir.isDirectory()) {
            LibraryAnalyzer visitor = new LibraryAnalyzer();
            visitor.analyze(libraryDir, indexDir);
        } else {
            Log.logger.error("Wrong library path: " + libraryDir.getAbsolutePath());
            throw new IllegalArgumentException("Wrong library path: " + libraryDir.getAbsolutePath());
        }
    }
}
