/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kapapa.kapapa.model;

import edu.kapapa.kapapa.database.KapapaDatabase;
import edu.kapapa.kapapa.entity.User;
import edu.kapapa.kapapa.error.UserException;
import edu.kapapa.kapapa.event.UserListener;
import edu.kapapa.kapapa.service.UserDao;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class UserModel {
	private int nisn;
	private String username;
	private String password;
	private String nama;
	private String noAbsen;
	private String noTelp;
	private String jabatan;
	private InputStream foto;
	
	private UserListener listener;
	
	public UserListener getListener() {
		return listener;
	}
	
	public void setListener(UserListener listener) {
		this.listener = listener;
	}

	public int getNisn() {
		return nisn;
	}

	public void setNisn(int nisn) {
		this.nisn = nisn;
		fireOnChange();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
		fireOnChange();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		fireOnChange();
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
		fireOnChange();
	}

	public String getNoAbsen() {
		return noAbsen;
	}

	public void setNoAbsen(String noAbsen) {
		this.noAbsen = noAbsen;
		fireOnChange();
	}

	public String getNoTelp() {
		return noTelp;
	}

	public void setNoTelp(String noTelp) {
		this.noTelp = noTelp;
		fireOnChange();
	}

	public String getJabatan() {
		return jabatan;
	}

	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
		fireOnChange();
	}

	public InputStream getFoto() {
		return foto;
	}

	public void setFoto(InputStream foto) {
		this.foto = foto;
	}
	
	//ketika user terganti
	protected void fireOnChange(){
		if (listener!=null){
			listener.OnUserChange(this);
		}
	}
	//jalankan ketika user ditambahkan
	protected void fireOnInsert(User user){
		if (listener!=null){
			listener.OnUserInsert(user);
		}
		
	}
	//jalankan ketika user diperbaharui
	protected void fireOnUpdate(User user){
		if (listener!=null){
			listener.OnUserUpdate(user);
		}
		
	}
	//jalankan ketika user terhapus
	protected void fireOnDelete(){
		if (listener!=null){
			listener.OnUserDelete();
		}
	}
	//fungsi yang akan dipanggil di controller untuk memasukkan user baru
	public void insertUser() throws SQLException, UserException {
		UserDao dao = KapapaDatabase.getUserDao();
		User user = new User();
		user.setNisn(nisn);
		user.setUsername(username);
		user.setPassword(password);
		user.setNama(nama);
		user.setNoAbsen(noAbsen);
		user.setNoTelp(noTelp);
		user.setJabatan(jabatan);
		user.setFoto(foto);
		
		dao.insertUser(user);
		fireOnInsert(user);
	}
	//fungsi yang akan dipanggil di controller untuk mengupdate user 
	public void updateUser() throws SQLException, UserException{
		UserDao dao = KapapaDatabase.getUserDao();
		User user = new User();
		user.setNisn(nisn);
		user.setUsername(username);
		user.setPassword(password);
		user.setNama(nama);
		user.setNoAbsen(noAbsen);
		user.setNoTelp(noTelp);
		user.setJabatan(jabatan);
		user.setFoto(foto);
		
		dao.updateUser(user);
		fireOnUpdate(user);
	}
	//fungsi yang akan dipanggil di controller untuk menghapus user 
	public void deleteUser() throws SQLException, UserException{
		UserDao dao = KapapaDatabase.getUserDao();
		dao.deleteUser(nisn);
		fireOnDelete();
	}
	
	
	
}
