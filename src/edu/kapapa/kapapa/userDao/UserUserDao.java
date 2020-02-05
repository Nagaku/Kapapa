/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kapapa.kapapa.userDao;

import edu.kapapa.kapapa.entity.User;
import edu.kapapa.kapapa.error.MemoException;
import edu.kapapa.kapapa.error.UserException;
import edu.kapapa.kapapa.service.UserDao;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class UserUserDao implements UserDao{
	
	private Connection connection;
	
	//koneksi database
	public UserUserDao(Connection connection) {
		this.connection = connection;
	}

	public UserUserDao() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	//query query yang akan dijalankan
	String insertUserQuery = "insert into user (nisn, username, password, nama, noAbsen, noTelp, jabatan, foto) values (?,?,?,?,?,?,?,?);";
	String updateUserQuery = "update user set nisn=?, username=?, password=?, nama=?, noAbsen=?, noTelp=?, jabatan=?, foto=? where nisn=?;";
	String deleteUserQuery = "delete from user where nisn=?";
	String getUserQuery = "select * from user where username=? and password=?";
	String selectAllUserQuery = "select * from user";
	String selectWali = "select * from user where jabatan=\"Wali\"";
	String selectKetuaKelas = "select * from user where jabatan=\"Ketua Kelas\"";
	String selectWakilKetua = "select * from user where jabatan=\"Wakil Ketua Kelas\"";
	String selectSekretaris = "select * from user where jabatan=\"Sekretaris\"";
	String selectBendahara = "select * from user where jabatan=\"Bendahara\"";

	//fungsi memasukkan user
	@Override
	public void insertUser(User user) throws UserException {
		
		PreparedStatement statement = null;
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(insertUserQuery);
			statement.setInt(1, user.getNisn());
			statement.setString(2, user.getUsername());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getNama());
			statement.setString(5, user.getNoAbsen());
			statement.setString(6, user.getNoTelp());
			statement.setString(7, user.getJabatan());
			statement.setBinaryStream(8, user.getFoto());
			statement.executeUpdate();
			
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new UserException(ex.getMessage());
			}
			throw new UserException(e.getMessage());
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (Exception e) {
			}
			try {
				statement.close();
			} catch (SQLException ex) {
				throw new UserException(ex.getMessage());
			}
		}
	}

	//fungsi mengupdate user
	@Override
	public void updateUser(User user) throws UserException {
		
		PreparedStatement statement = null;
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(updateUserQuery);
			statement.setInt(1, user.getNisn());
			statement.setString(2, user.getUsername());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getNama());
			statement.setString(5, user.getNoAbsen());
			statement.setString(6, user.getNoTelp());
			statement.setString(7, user.getJabatan());
			statement.setBinaryStream(8, user.getFoto());
			statement.setInt(9, user.getNisn());
			statement.executeUpdate();
			
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new UserException(ex.getMessage());
			}
			throw new UserException(e.getMessage());
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (Exception e) {
			}
			try {
				statement.close();
			} catch (SQLException ex) {
				throw new UserException(ex.getMessage());
			}
		}
	}
	//fungsi menghapus user
	@Override
	public void deleteUser(int nisn) throws UserException {
		
		PreparedStatement statement = null;
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(deleteUserQuery);
			statement.setInt(1, nisn);
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new UserException(ex.getMessage());
			}
			throw new UserException(e.getMessage());
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (Exception e) {
			}
			try {
				statement.close();
			} catch (SQLException ex) {
				throw new UserException(ex.getMessage());
			}
		}
	}
	//fungsi mengambil user berdasarkan ketepatan username dan password
	@Override
	public User getUser(String username, String password) throws UserException {
		
		PreparedStatement statement = null;
		try {
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(getUserQuery);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet result= statement.executeQuery();
			User user = new User();
			
			if ( result.next() ){
				user.setNisn(result.getInt("nisn"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("password"));
				user.setNama(result.getString("nama"));
				user.setNoAbsen(result.getString("noabsen"));
				user.setNoTelp(result.getString("notelp"));
				user.setJabatan(result.getString("jabatan"));
				user.setFoto((InputStream) result.getBinaryStream(8));
					
			} 
			connection.commit();
			return user;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new UserException(ex.getMessage());
			}
			throw new UserException(e.getMessage());
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (Exception e) {
			}
			try {
				statement.close();
			} catch (SQLException ex) {
				throw new UserException(ex.getMessage());
			}
		}
	}
	//mengambil semua user dari datbase 
	@Override
	public List<User> selectAllUser() throws UserException {
		
		Statement statement = null;
		List<User> listUser = new ArrayList<User>();
		
		try {
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			
			ResultSet result= statement.executeQuery(selectAllUserQuery);
			User user = null;
			while( result.next() ){
				user = new User();
				user.setNisn(result.getInt("nisn"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("password"));
				user.setNama(result.getString("nama"));
				user.setNoAbsen(result.getString("noAbsen"));
				user.setNoTelp(result.getString("noTelp"));
				user.setJabatan(result.getString("jabatan"));
				user.setFoto((InputStream) result.getBinaryStream(8));
				listUser.add(user);
			}
			
			return listUser;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new UserException(ex.getMessage());
			}
			throw new UserException(e.getMessage());
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (Exception e) {
			}
			try {
				statement.close();
			} catch (SQLException ex) {
				throw new UserException(ex.getMessage());
			}
		}
	}
	//mengambil gambar dan username untuk struktur organisasi kelas
	@Override
	public List<User> selectSOK() throws UserException {
		
		
		Statement statement = null;
		List<User> listUser = new ArrayList<User>();
		
		try {
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			ResultSet result= statement.executeQuery(selectWali);
			User user = null;
			result.next();
			user = new User();
			user.setUsername(result.getString("username"));
			user.setFoto((InputStream) result.getBinaryStream(8));
			listUser.add(user);
			statement = connection.createStatement();
			result= statement.executeQuery(selectKetuaKelas);
			user = null;
			result.next();
			user = new User();
			user.setUsername(result.getString("username"));
			user.setFoto((InputStream) result.getBinaryStream(8));
			listUser.add(user);
			statement = connection.createStatement();
			result= statement.executeQuery(selectWakilKetua);
			user = null;
			result.next();
			user = new User();
			user.setUsername(result.getString("username"));
			user.setFoto((InputStream) result.getBinaryStream(8));
			listUser.add(user);
			statement = connection.createStatement();
			result= statement.executeQuery(selectSekretaris);
			user = null;
			result.next();
			user = new User();
			user.setUsername(result.getString("username"));
			user.setFoto((InputStream) result.getBinaryStream(8));
			listUser.add(user);
			statement = connection.createStatement();
			result= statement.executeQuery(selectBendahara);
			user = null;
			result.next();
			user = new User();
			user.setUsername(result.getString("username"));
			user.setFoto((InputStream) result.getBinaryStream(8));
			listUser.add(user);
			return listUser;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new UserException(ex.getMessage());
			}
			throw new UserException(e.getMessage());
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (Exception e) {
			}
			try {
				statement.close();
			} catch (SQLException ex) {
				throw new UserException(ex.getMessage());
			}
		}
	}
	
	
	
	
}
