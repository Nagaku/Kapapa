/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kapapa.kapapa.error;

/**
 *
 * @author ASUS
 */
public class MemoException extends Exception {

	/**
	 * Creates a new instance of <code>MemoException</code> without detail
	 * message.
	 */
	public MemoException() {
	}

	/**
	 * Constructs an instance of <code>MemoException</code> with the
	 * specified detail message.
	 *
	 * @param msg the detail message.
	 */
	public MemoException(String msg) {
		super(msg);
	}
}
