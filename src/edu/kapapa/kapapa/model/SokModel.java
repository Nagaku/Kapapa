/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kapapa.kapapa.model;

import edu.kapapa.kapapa.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ASUS
 */
public class SokModel{
	
	private List<User> list = new ArrayList<User>();

	public void setList(List<User> list) {
		this.list = list;
	}
	

	//mengambil row nilai pada index
	public User get(int index) {
		return list.get(index);
	}

}
