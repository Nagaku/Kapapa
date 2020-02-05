/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kapapa.kapapa.model;

import edu.kapapa.kapapa.database.KapapaDatabase;
import edu.kapapa.kapapa.entity.Memo;
import edu.kapapa.kapapa.error.MemoException;
import edu.kapapa.kapapa.event.MemoListener;
import edu.kapapa.kapapa.service.MemoDao;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class MemoModel {
	private int idMemo;
	private String matapelajaran;
	private String tugas;
	private Date tanggal;

	public Date getTanggal() {
		return tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}
	
	private MemoListener listener;

	public MemoListener getListener() {
		return listener;
	}

	public void setListener(MemoListener listener) {
		this.listener = listener;
	}

	public int getId() {
		return idMemo;
	}

	public void setId(int id) {
		this.idMemo = id;
		fireOnChange();
	}

	public String getMatapelajaran() {
		return matapelajaran;
	}

	public void setMatapelajaran(String matapelajaran) {
		this.matapelajaran = matapelajaran;
		fireOnChange();
	}

	public String getTugas() {
		return tugas;
	}

	public void setTugas(String tugas) {
		this.tugas = tugas;
		fireOnChange();
	}
	//jalankan ketika memo berubah
	protected void fireOnChange(){
		if (listener!=null){
			listener.OnMemoChange(this);
		}
	}
	//jalankan ketika memo baru disimpan
	protected void fireOnInsert(Memo memo){
		if (listener!=null){
			listener.OnMemoInsert(memo);
		}
		
	}
	//jalankan ketika memo terupdate
	protected void fireOnUpdate(Memo memo){
		if (listener!=null){
			listener.OnMemoUpdate(memo);
		}
		
	}
	//jalankan ketika memo terdelete
	protected void fireOnDelete(){
		if (listener!=null){
			listener.OnMemoDelete();
		}
	}
	
	//fungsi yang akan dipanggil di controller untuk memasukkan memo baru
	public void insertMemo() throws SQLException, MemoException{
		MemoDao dao = KapapaDatabase.getMemoDao();
		Memo memo = new Memo();
		memo.setMataPelajaran(matapelajaran);
		memo.setTugas(tugas);
		memo.setTanggal(tanggal);
		
		dao.insertMemo(memo);
		fireOnInsert(memo);
	}
	
	//fungsi yang akan dipanggil di controller untuk mengupdate memo
	public void updateMemo() throws SQLException, MemoException{
		MemoDao dao = KapapaDatabase.getMemoDao();
		Memo memo = new Memo();
		memo.setMataPelajaran(matapelajaran);
		memo.setTugas(tugas);
		memo.setTanggal(tanggal);
		memo.setIdMemo(idMemo);
		
		dao.updateMemo(memo);
		fireOnUpdate(memo);
	}
	
	//fungsi yang akan dipanggil di controller untuk menghapus memo 
	public void deleteMemo() throws SQLException, MemoException{
		MemoDao dao = KapapaDatabase.getMemoDao();
		dao.deleteMemo(idMemo);
		fireOnDelete();
	}
	
	
	//fungsi yang akan dipanggil di controller untuk menghapus semua
	public void deleteAll() throws SQLException, MemoException{
		MemoDao dao = KapapaDatabase.getMemoDao();
		dao.deleteAll();
		fireOnDelete();
	}
	
	public void resetMemo(){
		setId(0);
		setMatapelajaran("");
		setTugas("");
		setTanggal(null);
	}
}
