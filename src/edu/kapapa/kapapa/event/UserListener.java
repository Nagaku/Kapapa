/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kapapa.kapapa.event;


import edu.kapapa.kapapa.entity.User;
import edu.kapapa.kapapa.model.UserModel;

/**
 *
 * @author ASUS
 */
public interface UserListener {
	public void OnUserChange(UserModel model);
	public void OnUserInsert(User user);
	public void OnUserDelete();
	public void OnUserUpdate(User user);
	
}
