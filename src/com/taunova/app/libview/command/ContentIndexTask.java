/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 * 
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.taunova.app.libview.command;

import com.taunova.app.libview.CommandRegistry;
import com.taunova.app.libview.Constants;
import com.taunova.app.libview.Item;
import com.taunova.app.libview.ItemCommand;
import com.taunova.app.libview.ItemProcessingException;
import com.taunova.app.libview.Node;
import com.taunova.app.libview.TemplateEngine;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Artem
 */
public class ContentIndexTask implements Runnable {
    protected Node root      = null;
    protected Map properties = null;
    protected CommandRegistry registry = null;
    protected TemplateEngine templateEngine = null;

    public ContentIndexTask(Node root,
                            CommandRegistry registry,
                            TemplateEngine templateEngine,
                            Map properties) {
        this.root = root;
        this.registry = registry;
        this.templateEngine = templateEngine;
        this.properties = properties;        
    }

    public void run() {
        try{
            createContentIndex(root, properties);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }


    protected void createContentIndex(Node root, Map properties) throws ItemProcessingException {
        String path = root.getPath();
        File indexFolder = new File((String)properties.get(Constants.PROP_INDEX_DIR));
        File indexFile = getEntityFile(path, indexFolder, "index.png");

        List<File> files = root.getFiles();

        Map context = new HashMap();
        context.put(Constants.PROP_RELATIVE_PATH, path);

        for (File file : files) {
            if (registry.containsCommand(file)) {
                ItemCommand command = registry.getCommand(file);
                Item item = command.process(file, context, properties);

                root.addItem(item);
            }
        }

        List<Item> items = root.getItems();

        if (!items.isEmpty()) {
            for (Item item : items) {
                System.out.println("Item: " + item.getThumbnailLink());
            }

            //  ShelfRenderer.ContentDescriptor descriptor = indexShelfRenderer.drawContent(items);
            //  Image image = descriptor.image;
            //  ImageHelpers.storeImage(image, indexFile);
            //  templateEngine.createHtmlIndex(descriptor.pointList, indexFile);
            templateEngine.createIndex(root, indexFile);
        }
    }

    protected File getEntityFile(String path, File indexDir, String fileName) {
        String parentDir = indexDir.getAbsoluteFile() + File.separator + path;
        (new File(parentDir)).mkdirs();
        return new File(parentDir + fileName);
    }
}
