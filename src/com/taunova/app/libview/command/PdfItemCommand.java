/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */

package com.taunova.app.libview.command;

import com.taunova.app.libview.FileRenderer;
import com.taunova.app.libview.Item;
import com.taunova.app.libview.ItemCommand;
import com.taunova.app.libview.ItemProcessingException;
import java.io.File;
import java.util.Map;

/**
 *
 * @author Artem
 */
public class PdfItemCommand implements ItemCommand {

    private FileRenderer pdfBookRenderer = null;

    public PdfItemCommand() {
      //  this.pdfBookRenderer = new IcePdfBookRenderer(new BookShelfRenderer());
    }

    public String getType() {
        return "pdf";
    }

    public Item process(File item) {
//                pdfBookRenderer.processFile(book);
//                image = pdfBookRenderer.getFileTitle();
//
//                String bookName = (book.getName().substring(0, book.getName().lastIndexOf('.')) + ".png");
//                File bookFile = getEntityFile(path, bookName);
//                ImageHelpers.storeImage(pdfBookRenderer.getFileIndex(), bookFile);
//                templateEngine.createHTMLPreview(bookFile);
        return null;
    }

    @Override
    public Item process(File item, Map context, Map properties) throws ItemProcessingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
