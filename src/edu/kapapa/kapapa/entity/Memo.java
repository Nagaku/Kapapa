/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kapapa.kapapa.entity;

import java.sql.Date;
import java.util.Objects;
import javax.imageio.stream.ImageInputStream;

/**
 *
 * @author ASUS
 */
public class Memo {
	private int idMemo;
	private String mataPelajaran;
	private String tugas;
	private Date tanggal;
	

	public Memo() {
	}

	public Memo(String mataPelajaran, String tugas) {
		this.mataPelajaran = mataPelajaran;
		this.tugas = tugas;
	}
	
	public int getIdMemo() {
		return idMemo;
	}

	public void setIdMemo(int idMemo) {
		this.idMemo = idMemo;
	}

	public String getMataPelajaran() {
		return mataPelajaran;
	}

	public void setMataPelajaran(String mataPelajaran) {
		this.mataPelajaran = mataPelajaran;
	}

	public String getTugas() {
		return tugas;
	}

	public void setTugas(String tugas) {
		this.tugas = tugas;
	}

	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

	
	
	
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 73 * hash + this.idMemo;
		hash = 73 * hash + Objects.hashCode(this.mataPelajaran);
		hash = 73 * hash + Objects.hashCode(this.tugas);
		hash = 73 * hash + Objects.hashCode(this.tanggal);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Memo other = (Memo) obj;
		if (this.idMemo != other.idMemo) {
			return false;
		}
		if (!Objects.equals(this.mataPelajaran, other.mataPelajaran)) {
			return false;
		}
		if (!Objects.equals(this.tugas, other.tugas)) {
			return false;
		}
		if (!Objects.equals(this.tanggal, other.tanggal)) {
			return false;
		}
		return true;
	}

	
	
}
