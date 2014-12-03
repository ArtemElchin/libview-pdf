/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.taunova.app.libview.command;

import com.taunova.app.libview.Item;
import com.taunova.app.libview.ItemCommand;
import java.io.File;

/**
 *
 * @author Artem
 */
public class DjvuItemCommand implements ItemCommand {

    public String getType() {
        return "djvu";
    }

    public Item process(File item) {
        //image = addDJVUBook(book);
        return null;
    }

}
