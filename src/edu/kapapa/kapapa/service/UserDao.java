/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kapapa.kapapa.service;


import edu.kapapa.kapapa.entity.User;
import edu.kapapa.kapapa.error.UserException;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface UserDao {
	public void insertUser(User user) throws UserException;
	public void updateUser(User user) throws UserException;
	public void deleteUser(int nisn) throws UserException;
	public User getUser(String username, String password) throws UserException;
	public List<User> selectAllUser() throws UserException;
	public List<User> selectSOK() throws UserException;
	
}
