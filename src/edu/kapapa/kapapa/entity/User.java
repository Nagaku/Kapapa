/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kapapa.kapapa.entity;

import java.io.InputStream;
import javax.imageio.stream.ImageInputStream;

/**
 *
 * @author ASUS
 */
public class User {
	private int nisn;
	private String username;
	private String password;
	private String nama;
	private String noAbsen;
	private String noTelp;
	private String jabatan;
	private InputStream foto;

	public int getNisn() {
		return nisn;
	}

	public void setNisn(int nisn) {
		this.nisn = nisn;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getNoAbsen() {
		return noAbsen;
	}

	public void setNoAbsen(String noAbsen) {
		this.noAbsen = noAbsen;
	}

	public String getNoTelp() {
		return noTelp;
	}

	public void setNoTelp(String noTelp) {
		this.noTelp = noTelp;
	}

	public String getJabatan() {
		return jabatan;
	}

	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}

	public InputStream getFoto() {
		return foto;
	}

	public void setFoto(InputStream foto) {
		this.foto = foto;
	}

	
	
	
	
	
	
}
