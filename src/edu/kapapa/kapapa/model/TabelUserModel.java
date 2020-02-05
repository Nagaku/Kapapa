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
public class TabelUserModel extends AbstractTableModel{
	
	//list yang menyimpan all user
	private List<User> list = new ArrayList<User>();

	public void setList(List<User> list) {
		this.list = list;
	}
	
	//menemtukan jumlah row tabel
	@Override
	public int getRowCount() {
		return list.size();
	}
	//menemtukan jumlah kolom tabel
	@Override
	public int getColumnCount() {
		return 8;
	}
	//menambahkan row baru ke tabel
	public boolean add(User e) {
		try {
			return list.add(e);
		} finally {
		   fireTableRowsInserted(getColumnCount()-1, getRowCount()-1);
		}
	}
	//mengambil nilai dari row yang dipilih
	public User get(int index) {
		return list.get(index);
	}
	//mengganti nilai dari row yang dipilih
	public User set(int index, User element) {
		try {
		return list.set(index, element);	
		} finally {
		   fireTableRowsUpdated(index, index);
		}
		
	}
	//menghapus row yang dipilih
	public User remove(int index) {
		try {
			
		return list.remove(index);
		} finally {
		    fireTableRowsDeleted(index, index);
		}
		
	}
	
	//membari nama kolom dalam tabel
	@Override
	public String getColumnName(int column) {
		switch(column){
			case 0 : 
				return "NISN";
			case 1 : 
				return "Username";
			case 2 : 
				return "Password";
			case 3 : 
				return "Nama";
			case 4 : 
				return "No Absen";
			case 5 : 
				return "No Telpon";
			case 6 : 
				return "Jabatan";
			case 7 : 
				return "Foto";
			default:
				return null;
		}
		
	}
	
	
	//mengembalikan nilai berdasarkan row dan kolom yang dipilih
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
			case 0 : 
				return list.get(rowIndex).getNisn();
			case 1 :
				return list.get(rowIndex).getUsername();
			case 2 :
				return list.get(rowIndex).getPassword();
			case 3 :
				return list.get(rowIndex).getNama();
			case 4 :
				return list.get(rowIndex).getNoAbsen();
			case 5 :
				return list.get(rowIndex).getNoTelp();
			case 6 :
				return list.get(rowIndex).getJabatan();
			case 7 :
				return list.get(rowIndex).getFoto();
			default:
				return null;
	}
		
	}
}
