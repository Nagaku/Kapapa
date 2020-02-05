/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kapapa.kapapa.controller;

import edu.kapapa.kapapa.model.MemoModel;
import edu.kapapa.kapapa.view.MainFrame;
import java.sql.Date;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class MemoController {
	private MemoModel model;

	public void setModel(MemoModel model) {
		this.model = model;
	}
	public void resetMemo(MainFrame view){
		model.resetMemo();
	}
	
	
	//mengatur nilai dari view dan menjalankan insertmemo
	public void insertMemo(MainFrame view){
		
		String matapelajaran = view.getTxtMapel().getText();
		String tugas = view.getTxtTugas().getText();
		long tanggal =  view.getTanggalChooser().getDate().getTime();
		Date date = new java.sql.Date(tanggal);
		
		if (matapelajaran.trim().equals("")) {
		 JOptionPane.showMessageDialog(view, "Mata Pelajaran Tidak Boleh Kosong!");
		}
		 else if (matapelajaran.length()>255){
		JOptionPane.showMessageDialog(view, "Mata Pelajaran Tidak Boleh Lebih Dari 255 Karakter!");	 
		} else {
		 model.setMatapelajaran(matapelajaran);
		 model.setTugas(tugas);
		 model.setTanggal(date);
		 
		 try {
			model.insertMemo();
			JOptionPane.showMessageDialog(view, "Berhasil Ditambahkan ");
			} catch (Throwable throwable) {
			JOptionPane.showMessageDialog(view, new Object[]{"Terjadi Error, ",throwable.getMessage()});	
			}
		 }
	
		
	}
	//mengatur nilai dari view dan menjalankan updatememo
	public void updateMemo(MainFrame view){
		if (view.getTableMemoView().getSelectedRowCount()==0){
			JOptionPane.showMessageDialog(view, "Silahkan Seleksi Baris Data Yang Akan Diubah");
			return;
		}
		
		
		Integer id = Integer.parseInt(view.getTxtId().getText());
		String matapelajaran = view.getTxtMapel().getText();
		String tugas = view.getTxtTugas().getText();
		long tanggal =  view.getTanggalChooser().getDate().getTime();
		Date date = new java.sql.Date(tanggal);
		
		if (matapelajaran.trim().equals("")) {
			JOptionPane.showMessageDialog(view, "Mata Pelajaran Tidak Boleh Kosong!");
		}
		else if (matapelajaran.length()>255){
			JOptionPane.showMessageDialog(view, "Mata Pelajaran Tidak Boleh Lebih Dari 255 Karakter!");	 
		} else {
			model.setId(id);
			model.setMatapelajaran(matapelajaran);
			model.setTugas(tugas);
			model.setTanggal(date);
			
		 
			try {
				model.updateMemo();
				JOptionPane.showMessageDialog(view, "Berhasil Diubah ");
				model.resetMemo();
			} catch (Throwable throwable) {
				JOptionPane.showMessageDialog(view, new Object[]{"Terjadi Error, ",throwable.getMessage()});	
			}
		}
		
	}
	//mengatur nilai dari view dan menjalankan deletememo
	public void deleteMemo(MainFrame view){
		if (view.getTableMemoView().getSelectedRowCount()==0){
			JOptionPane.showMessageDialog(view, "Silahkan Seleksi Baris Data Yang Akan Diubah");
			return;
		}
		
		if (JOptionPane.showConfirmDialog(view, "Anda Yakin Akan Menghapus?")==JOptionPane.OK_OPTION) {
			Integer id = Integer.parseInt(view.getTxtId().getText());
			model.setId(id);
			
			try {
			model.deleteMemo();
			JOptionPane.showMessageDialog(view, "Berhasil Dihapus ");
			model.resetMemo();
			} catch (Throwable throwable) {
			JOptionPane.showMessageDialog(view, new Object[]{"Terjadi Error, ",throwable.getMessage()});	
			}
		}
	}
	
	
	//mengatur nilai dari view dan menjalankan deleteallmemo
	public void deleteAllMemo(MainFrame view){
		
		if (JOptionPane.showConfirmDialog(view, "Anda Yakin Akan Mengosongkan Tabel?")==JOptionPane.OK_OPTION) {
			Integer id = Integer.parseInt(view.getTxtId().getText());
			model.setId(0);
			
			try {
			model.deleteAll();
			JOptionPane.showMessageDialog(view, "Berhasil Dihapus ");
			model.resetMemo();
			} catch (Throwable throwable) {
			JOptionPane.showMessageDialog(view, new Object[]{"Terjadi Error, ",throwable.getMessage()});	
			}
		}
	}
}
