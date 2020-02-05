/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kapapa.kapapa.main;

import edu.kapapa.kapapa.database.KapapaDatabase;
import edu.kapapa.kapapa.entity.Memo;
import edu.kapapa.kapapa.error.MemoException;
import edu.kapapa.kapapa.error.UserException;
import edu.kapapa.kapapa.service.MemoDao;
import edu.kapapa.kapapa.view.LoginFrame;
import edu.kapapa.kapapa.view.MainFrame;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author ASUS
 */
public class Kapapa {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws SQLException, MemoException, UserException {
		// TODO code application logic here
		
		
		SwingUtilities.invokeLater(new Runnable() {
				
			@Override
			public void run() {
				//menampilkan form login
				LoginFrame loginFrame = new LoginFrame();
				loginFrame.setVisible(true);
			}
		});
	}
}
			

