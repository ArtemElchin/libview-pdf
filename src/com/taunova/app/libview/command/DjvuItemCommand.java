/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.taunova.app.libview.command;

import com.taunova.app.libview.Item;
import com.taunova.app.libview.ItemCommand;
import com.taunova.app.libview.ItemProcessingException;
import java.io.File;
import java.util.Map;

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

    @Override
    public Item process(File item, Map context, Map properties) throws ItemProcessingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
