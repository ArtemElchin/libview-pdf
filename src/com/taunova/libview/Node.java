/*
 * Copyright 2009 TauNova (http://taunova.com). All rights reserved.
 * 
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.taunova.app.libview;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Artem
 */
public class Node {
    protected Node parent = null;
    protected File nodeRoot;

    protected List<File> files = new LinkedList();
    protected List<Node> nodes = new LinkedList();
    protected List<Item> fileItems = new LinkedList();
    protected List<Item> folderItems = new LinkedList();

    public Node(File root) {
        this(root, null);
    }

    public Node(File root, Node parent) {
        this.nodeRoot = root;
        this.parent = parent;
    }

    public File[] listFiles() {
        return nodeRoot.listFiles();
    }

    public String getName() {
        return nodeRoot.getName();
    }

    public File getRoot() {
        return nodeRoot;
    }

    public void addFile(File file) {
        files.add(file);
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addItem(Item item) {
        fileItems.add(item);
    }

    public void addFolderItem(Item item) {
        folderItems.add(item);
    }

    public void addFolderItem(int index, Item item) {
        folderItems.add(index, item);
    }

    public List<File> getFiles() {
        return files;
    }

    public List<Item> getItems() {
        return fileItems;
    }

    public boolean hasChildren() {
        return !nodes.isEmpty();
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public Node getRootNode() {
        return (null != parent) ? parent.getRootNode() : this;
    }

    public String getPath() {
       return (null != parent) ? parent.getPath() + getName() + File.separator : "" ;
    }

    public String getRelativePath() {
        return (null != parent) ? parent.getRelativePath() + File.separator  + "..": "." ;
    }
}
