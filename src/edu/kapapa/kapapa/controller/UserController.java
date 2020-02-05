/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kapapa.kapapa.controller;

import edu.kapapa.kapapa.model.UserModel;
import edu.kapapa.kapapa.view.MainFrame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class UserController {
	
	private UserModel model;
	public void setModel(UserModel model) {
		this.model = model;
	}
	
	//mengkonversi image menjadi bufferedimage
	public static BufferedImage convertToBufferedImage(Image image)
	{
		BufferedImage newImage = new BufferedImage(
		image.getWidth(null), image.getHeight(null),
		BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = newImage.createGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		return newImage;
	}
	
	//mengatur nilai dari view dan menjalankan inseruser
	public void insertUser(MainFrame view) throws IOException{
		
		Integer nisn = Integer.parseInt(view.getNisnF().getText());
		String username = view.getUsernameF().getText();
		String password = view.getPasswordF().getText();
		String nama = view.getNamaF().getText();
		String noAbsen = view.getNoabsenF().getText();
		String noTelp = view.getNotelpF().getText();
		String jabatan = view.getJabatanF().getText();
		ByteArrayOutputStream inputBtye = new ByteArrayOutputStream();
		ImageIO.write(convertToBufferedImage(view.getImage()),"png", inputBtye); 
		InputStream foto = new ByteArrayInputStream(inputBtye.toByteArray());
		
		if (nisn == 0)
			JOptionPane.showMessageDialog(view, "NISN Tidak Boleh Kosong");
		else if (username.trim().equals("")) 
			JOptionPane.showMessageDialog(view, "Mata Pelajaran Tidak Boleh Kosong!");
		else if (username.length()>255)
			JOptionPane.showMessageDialog(view, "Mata Pelajaran Tidak Boleh Lebih Dari 255 Karakter!");	 
		else {
			model.setNisn(nisn);
			model.setUsername(username);
			model.setPassword(password);
			model.setNama(nama);
			model.setNoAbsen(noAbsen);
			model.setNoTelp(noTelp);
			model.setJabatan(jabatan);
			model.setFoto(foto);
			
		 
		 try {
			model.insertUser();
			JOptionPane.showMessageDialog(view, "Berhasil Ditambahkan ");
			} catch (Throwable throwable) {
			JOptionPane.showMessageDialog(view, new Object[]{"Terjadi Error, ",throwable.getMessage()});	
			}
		 }
		
	
		
	}
	//mengatur nilai dari view dan menjalankan updateuser
	public void updateUser(MainFrame view) throws IOException{
		
		Integer nisn = Integer.parseInt(view.getNisnF().getText());
		String username = view.getUsernameF().getText();
		String password = view.getPasswordF().getText();
		String nama = view.getNamaF().getText();
		String noAbsen = view.getNoabsenF().getText();
		String noTelp = view.getNotelpF().getText();
		String jabatan = view.getJabatanF().getText();
		//konversi image menjadi inputstream
		ByteArrayOutputStream inputBtye = new ByteArrayOutputStream();
		ImageIO.write(convertToBufferedImage(view.getImage()),"png", inputBtye); 
		InputStream foto = new ByteArrayInputStream(inputBtye.toByteArray());
		
		if (view.getUserTable().getSelectedRowCount()==0){
			JOptionPane.showMessageDialog(view, "Silahkan Seleksi Baris Data Yang Akan Diubah");
			return;
		}
		
		
		
		if (username.trim().equals("")) 
			JOptionPane.showMessageDialog(view, "Mata Pelajaran Tidak Boleh Kosong!");
		else if (username.length()>255)
			JOptionPane.showMessageDialog(view, "Mata Pelajaran Tidak Boleh Lebih Dari 255 Karakter!");		 
		else {
			model.setNisn(nisn);
			model.setUsername(username);
			model.setPassword(password);
			model.setNama(nama);
			model.setNoAbsen(noAbsen);
			model.setNoTelp(noTelp);
			model.setJabatan(jabatan);
			model.setFoto(foto);
		 
			try {
				model.updateUser();
				JOptionPane.showMessageDialog(view, "Berhasil Diubah ");
			} catch (Throwable throwable) {
				JOptionPane.showMessageDialog(view, new Object[]{"Terjadi Error, ",throwable.getMessage()});	
			}
		}
		
	}
	//
	//mengatur nilai dari view dan menjalankan deleteuser
	public void deleteUser(MainFrame view){
		
		
		
		if (view.getUserTable().getSelectedRowCount()==0){
			JOptionPane.showMessageDialog(view, "Silahkan Seleksi Baris Data Yang Akan Diubah");
			return;
		}
		
		if (JOptionPane.showConfirmDialog(view, "Anda Yakin Akan Menghapus?")==JOptionPane.OK_OPTION) {
			Integer nisn = Integer.parseInt(view.getNisnF().getText());
			model.setNisn(nisn);
			
			try {
				model.deleteUser();
				JOptionPane.showMessageDialog(view, "Berhasil Dihapus ");
			} catch (Throwable throwable) {
				JOptionPane.showMessageDialog(view, new Object[]{"Terjadi Error, ",throwable.getMessage()});	
			}
		}
	}
}
