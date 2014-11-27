/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.taunova.app.libview.components;

import com.taunova.app.libview.CommandRegistry;
import com.taunova.app.libview.DefaultCommandRegistry;
import com.taunova.app.libview.ItemCommand;
import com.taunova.app.libview.ItemProcessingException;
import com.taunova.app.libview.Constants;
import com.taunova.app.libview.command.FolderItemCommand;
import com.taunova.app.libview.command.JpegItemCommand;
import com.taunova.app.libview.command.PngItemCommand;
import java.io.File;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LibraryAnalyzer {
    protected final int THREAD_COUNT = 10;

    public static int PAGE_WIDTH = 1024;
    public static int ICON_WIDTH = 200;
    public static int ICON_HEIGHT = 300;
    public static int WIDTH_SPACE = 10;
    public static int HEIGTH_SPACE = 10;
    public static int PREVIEW_IMAGE_COUNT = 12;
    public static int MAIN_ICON_WIDTH = 400;
    public static int MAIN_ICON_HEIGHT = 600;
    protected CommandRegistry registry = new DefaultCommandRegistry();

    private ExecutorService executor = null;

    public LibraryAnalyzer() {
        executor = Executors.newFixedThreadPool(THREAD_COUNT);

        registry.addCommand(new FolderItemCommand(registry, executor));
        registry.addCommand(new PngItemCommand());
        registry.addCommand(new JpegItemCommand());
    }

    public void analyze(File file, File indexDir) throws ItemProcessingException {
        ItemCommand command = registry.getCommand(file);
        Properties properties = new Properties();
        properties.setProperty(Constants.PROP_INDEX_DIR, indexDir.getAbsolutePath());
        command.process(file, new HashMap(), properties);

        executor.shutdown();
        try {
            executor.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
