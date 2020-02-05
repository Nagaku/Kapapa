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
public class UserException extends Exception{
	 
	public UserException() {
	}

	/**
	 * Constructs an instance of <code>MemoException</code> with the
	 * specified detail message.
	 *
	 * @param msg the detail message.
	 */
	public UserException(String msg) {
		super(msg);
	}
	
}
