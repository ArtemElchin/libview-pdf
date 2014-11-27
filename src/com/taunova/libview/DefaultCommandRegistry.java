/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.taunova.app.libview;

import com.taunova.app.libview.command.FolderItemCommand;
import com.taunova.app.libview.command.PngItemCommand;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Artem
 */
public class DefaultCommandRegistry implements CommandRegistry {

    protected Map<String, ItemCommand> registry = new HashMap();

    public DefaultCommandRegistry() {
    }

    public void addCommand(ItemCommand command) {
        registry.put(command.getType(), command);
    }

    public boolean containsCommand(File file) {
        return registry.containsKey(getExtension(file));
    }

    public ItemCommand getCommand(File file) {
        return registry.get(getExtension(file));
    }

    protected String getExtension(File file) {
        return (file.isFile()) ? FilenameUtils.getExtension(file.getName()) : "folder";
    }
}