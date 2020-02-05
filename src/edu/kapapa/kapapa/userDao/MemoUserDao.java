/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kapapa.kapapa.userDao;

import edu.kapapa.kapapa.entity.Memo;
import edu.kapapa.kapapa.error.MemoException;
import edu.kapapa.kapapa.service.MemoDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class MemoUserDao implements MemoDao{
	
	private Connection connection;
	//query query yang akan digunakan
	private final String insertMemoQuery = "insert into memo (mataPelajaran, tugas, tanggal) values(?,?,?);";
	private final String updateMemoQuery = "update memo set mataPelajaran=?, tugas=?, tanggal=? where idMemo=?;";
	private final String deleteMemoQuery = "delete from memo where idMemo=?;";
	private final String selectMemoQuery = "select * from memo where mataPelajaran=?;";
	private final String selectAllMemoQuery = "select * from memo";
	private final String deleteAllMemoQuery = "delete from memo where 1=1";
	//koneksi database
	public MemoUserDao(Connection connection) {
		this.connection = connection;
	}

	public MemoUserDao() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	//fungsi untuk menambahkan memo
	@Override
	public void insertMemo(Memo memo) throws MemoException {
		
		PreparedStatement statement = null;
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(insertMemoQuery,  Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, memo.getMataPelajaran());
			statement.setString(2, memo.getTugas());
			statement.setDate(3, memo.getTanggal());
			statement.executeUpdate();
			
			 ResultSet result = statement.getGeneratedKeys();
			 if (result.next()) {
				 memo.setIdMemo(result.getInt(1));
			 }
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new MemoException(ex.getMessage());
			}
			throw new MemoException(e.getMessage());
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (Exception e) {
			}
			try {
				statement.close();
			} catch (SQLException ex) {
				throw new MemoException(ex.getMessage());
			}
		}
	}
	//fungsi untuk memperbaharui memo
	@Override
	public void updateMemo(Memo memo) throws MemoException {
		
		PreparedStatement statement = null;
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(updateMemoQuery);
			statement.setString(1, memo.getMataPelajaran());
			statement.setString(2, memo.getTugas());
			statement.setDate(3, memo.getTanggal());
			statement.setInt(4, memo.getIdMemo());
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new MemoException(ex.getMessage());
			}
			throw new MemoException(e.getMessage());
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (Exception e) {
			}
			try {
				statement.close();
			} catch (SQLException ex) {
				throw new MemoException(ex.getMessage());
			}
		}
	}
	//fungsi menghapus memo
	@Override
	public void deleteMemo(int idMemo) throws MemoException {
		
		PreparedStatement statement = null;
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(deleteMemoQuery);
			statement.setInt(1, idMemo);
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new MemoException(ex.getMessage());
			}
			throw new MemoException(e.getMessage());
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (Exception e) {
			}
			try {
				statement.close();
			} catch (SQLException ex) {
				throw new MemoException(ex.getMessage());
			}
		}
	}
	//fungsi mengambil memo berdasarkan mata pelajaran
	@Override
	public Memo getMemo(String mataPelajaran) throws MemoException {
		
		PreparedStatement statement = null;
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(selectMemoQuery);
			statement.setString(1, mataPelajaran);
			ResultSet result= statement.executeQuery();
			Memo memo = new Memo();
			
			if ( result.next() ){
				memo.setIdMemo(result.getInt("idMemo"));
				memo.setMataPelajaran(result.getString("mataPelajaran"));
				memo.setTugas(result.getString("tugas"));
			} else {
				throw new MemoException("Mata Pelajaran " + mataPelajaran + " Tidak Memiliki Tugas");
			}
			connection.commit();
			return memo;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new MemoException(ex.getMessage());
			}
			throw new MemoException(e.getMessage());
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (Exception e) {
			}
			try {
				statement.close();
			} catch (SQLException ex) {
				throw new MemoException(ex.getMessage());
			}
		}
	}
	//fungsi untuk mengambil semua memo 
	@Override
	public List<Memo> selectAllMemo() throws MemoException {
		
		Statement statement = null;
		List<Memo> listMemo = new ArrayList<Memo>();
		
		try {
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			
			ResultSet result= statement.executeQuery(selectAllMemoQuery);
			Memo memo = null;
			
			while( result.next() ){
				memo = new Memo();
				memo.setIdMemo(result.getInt("idMemo"));
				memo.setMataPelajaran(result.getString("mataPelajaran"));
				memo.setTugas(result.getString("tugas"));
				memo.setTanggal(result.getDate("tanggal"));
				listMemo.add(memo);
			}
			
			return listMemo;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new MemoException(ex.getMessage());
			}
			throw new MemoException(e.getMessage());
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (Exception e) {
			}
			try {
				statement.close();
			} catch (SQLException ex) {
				throw new MemoException(ex.getMessage());
			}
		}
	}
	//fungsi menghapus semua memo
	@Override
	public void deleteAll() throws MemoException {
		
		PreparedStatement statement = null;
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(deleteAllMemoQuery);
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new MemoException(ex.getMessage());
			}
			throw new MemoException(e.getMessage());
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (Exception e) {
			}
			try {
				statement.close();
			} catch (SQLException ex) {
				throw new MemoException(ex.getMessage());
			}
		}
	}
	
}
