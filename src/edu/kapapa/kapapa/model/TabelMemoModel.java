/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kapapa.kapapa.model;

import edu.kapapa.kapapa.entity.Memo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ASUS
 */
public class TabelMemoModel extends AbstractTableModel{

	//list yang menyimpan semua memo
	private List<Memo> list = new ArrayList<Memo>();

	public void setList(List<Memo> list) {
		this.list = list;
	}
	
	//menentukan jumlah baris tabel
	@Override
	public int getRowCount() {
		return list.size();
	}
	//menentukan jumlah kolom tabel
	@Override
	public int getColumnCount() {
		return 4;
	}
	//menambahkan row baru ke tabel memo
	public boolean add(Memo e) {
		try {
			return list.add(e);
		} finally {
			fireTableRowsInserted(getColumnCount()-1, getRowCount()-1);
		}
	}
	//mengembalikan nilai dari row yang dipilih
	public Memo get(int index) {
		return list.get(index);
	}
	//mengupdate nilai dari row yang dipilih
	public Memo set(int index, Memo element) {
		try {
			return list.set(index, element);	
		} finally {
			fireTableRowsUpdated(index, index);
		}
		
	}
	//menghapus row yang dipiih
	public Memo remove(int index) {
		try {
			
			return list.remove(index);
		} finally {
			fireTableRowsDeleted(index, index);
		}
		
	}
	
	//memberi nama kolom pada tabel memo
	@Override
	public String getColumnName(int column) {
		switch(column){
			case 0 : 
				return "ID";
			case 1 : 
				return "Mata Pelajaran";
			case 2 : 
				return "Tugas";
			case 3 :
				return "Tanggal";
			default:
				return null;
		}
		
	}
	
	
	//mengembalikan nilai berdasarkan row dan kolom yang dipilih
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
			case 0 : 
				return list.get(rowIndex).getIdMemo();
			case 1 :
				return list.get(rowIndex).getMataPelajaran();
			case 2 :
				return list.get(rowIndex).getTugas();
			case 3 :
				return list.get(rowIndex).getTanggal();
			default:
				return null;
	}
		
	}	
	
}



