/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.taunova.app.libview.command;

import com.taunova.app.libview.CommandRegistry;
import com.taunova.app.libview.Item;
import com.taunova.app.libview.ItemCommand;
import com.taunova.app.libview.ItemProcessingException;
import com.taunova.app.libview.Node;
import com.taunova.app.libview.components.DefaultTemplateEngine;
import com.taunova.app.libview.TemplateEngine;
import com.taunova.app.libview.components.IndexShelfRenderer;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 *
 * @author Artem
 */
public class FolderItemCommand implements ItemCommand {
    public static final String TYPE = "folder";

    private TemplateEngine templateEngine = null;
    private IndexShelfRenderer indexShelfRenderer = null;
    private ExecutorService executor = null;

    private CommandRegistry registry = null;

    public FolderItemCommand(CommandRegistry registry, ExecutorService executor) {
        this.registry = registry;
        this.executor = executor;
        this.indexShelfRenderer = new IndexShelfRenderer();
        this.templateEngine = new DefaultTemplateEngine();
    }

    public String getType() {
        return TYPE;
    }

    public Item process(File folderItem, Map context, Map properties) throws ItemProcessingException {
        Node rootNode = processTree(new Node(folderItem));
        return process(rootNode, context, properties);
    }

    public Node processTree(Node root) {
        File[] entries = root.listFiles();

        for (File entry : entries) {
            if (entry.isFile()) {
                root.addFile(entry);
            } else {
                Node node = new Node(entry, root);
                processTree(node);
                root.addNode(node);
            }
        }

        return root;
    }

    public Item process(Node node, Map context, Map properties) throws ItemProcessingException {
        List<Node> nodeList = node.getNodes();

        // process all folders
        for (Node folder : nodeList) {
            Item item = process(folder, context, properties);
            node.addFolderItem(item);
        }

        //createContentIndex(node, properties); // separate task
        Runnable command = new ContentIndexTask(node, registry, templateEngine, properties);
        executor.execute(command);
        return null;
    }
}
