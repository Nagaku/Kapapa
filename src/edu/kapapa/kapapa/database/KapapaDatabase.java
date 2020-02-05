/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kapapa.kapapa.database;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import edu.kapapa.kapapa.service.MemoDao;
import edu.kapapa.kapapa.service.UserDao;
import edu.kapapa.kapapa.userDao.MemoUserDao;
import edu.kapapa.kapapa.userDao.UserUserDao;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class KapapaDatabase {
	
	//koneksi database
	private static Connection connection;
	private static MemoDao memoDao;
	private static UserDao userDao;
	
	public static Connection getConnection() throws SQLException{
		if ( connection == null) {
			
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setURL("jdbc:mysql://localhost:3306/kapapa");
			dataSource.setUser("root");
			dataSource.setPassword("");
			connection = dataSource.getConnection();
		}
		
		return connection;
	}
	
	//memo dao
	public static MemoDao getMemoDao() throws SQLException{
		if (memoDao==null) 
			memoDao = new MemoUserDao(getConnection());
		
		return memoDao;
	}
	
	//user dao
	public static UserDao getUserDao() throws SQLException{
		if (userDao==null) 
			userDao = new UserUserDao(getConnection());
		
		return userDao;
	}
}
