/*
 * Copyright 2009-2010 TauNova (http://taunova.com). All rights reserved.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.taunova.app.libview.components;

import com.taunova.app.libview.Node;
import com.taunova.app.libview.TemplateEngine;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 *
 * @author Artem
 */
public class DefaultTemplateEngine implements TemplateEngine {

    public static int PAGE_WIDTH = 1024;
    public static int ICON_WIDTH = 200;
    public static int ICON_HEIGHT = 300;

    public DefaultTemplateEngine() {
    }

    public void createIndex(Node root, File indexFile) {
        VelocityContext context = new VelocityContext();

        context.put("root", root.getRootNode());
        context.put("name", root.getName());
        context.put("nodes", root.getNodes());
        context.put("items", root.getItems());
        context.put("relativePath", root.getRelativePath());

        processTemplate(context, indexFile);
    }

    protected void processTemplate(VelocityContext context, File indexFile) {

        try {
            String templateFile = "work/libview/template/index.vm";
            Velocity.init("work/libview/template/velocity.properties");

            Template template = null;

            try {
                template = Velocity.getTemplate(templateFile);
            } catch (ResourceNotFoundException rnfe) {
                System.out.println("Cannot find template " + templateFile);
            } catch (ParseErrorException pee) {
                System.out.println("Syntax error in template " + templateFile + ":" + pee);
            }

            if (template != null) {
                File file = new File(indexFile.getParent() + File.separator + "index.html");
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                template.merge(context, writer);

                writer.flush();
                writer.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createHtmlPreview(File indexFile) {

        try {
            String templateFile = "work/libview/template/previewhtml.vm";

            Velocity.init("work/libview/template/velocity.properties");
            VelocityContext context = new VelocityContext();

            context.put("name", indexFile.getName());

            Template template = null;

            try {
                template = Velocity.getTemplate(templateFile);
            } catch (ResourceNotFoundException rnfe) {
                System.out.println("Cannot find template " + templateFile);
            } catch (ParseErrorException pee) {
                System.out.println("Syntax error in template " + templateFile + ":" + pee);
            }

            if (template != null) {
                String bookName = (indexFile.getName().substring(0, indexFile.getName().lastIndexOf('.')) + ".html");
                File file = new File(indexFile.getParent() + File.separator + bookName);

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                template.merge(context, writer);

                writer.flush();
                writer.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createHtmlIndex(List<Coordinates> pointlist, File indexFile) {
        try {
            String templateFile = "work/libview/template/indexhtml.vm";

            Velocity.init("work/libview/template/velocity.properties");
            VelocityContext context = new VelocityContext();

            context.put("list", pointlist.iterator());
            context.put("name", indexFile.getName());
            context.put("iconWidth", ICON_WIDTH);
            context.put("iconHeigth", ICON_HEIGHT);

            Template template = null;

            try {
                template = Velocity.getTemplate(templateFile);
            } catch (ResourceNotFoundException rnfe) {
                System.out.println("Cannot find template " + templateFile);
            } catch (ParseErrorException pee) {
                System.out.println("Syntax error in template " + templateFile + ":" + pee);
            }

            if (template != null) {
                File file = new File(indexFile.getParent() + File.separator + "index.html");
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                template.merge(context, writer);

                writer.flush();
                writer.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

