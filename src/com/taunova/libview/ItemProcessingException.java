/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taunova.app.libview;

/**
 *
 * @author Artem
 */
public class ItemProcessingException extends Exception {

    public ItemProcessingException(String description) {
        super(description);
    }

    public ItemProcessingException(Throwable cause) {
        super(cause);
    }

    public ItemProcessingException(String description, Throwable cause) {
        super(description, cause);
    }
}
