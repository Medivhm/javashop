package com.javashop.ui;

import java.sql.SQLException;

/**
 * @author Medivhm
 *
 */
public class Start {
	public static void main(String[] args) throws SQLException {
		
		if(MenuFactory.createMenu().Login()){
			MenuFactory.createMenu().start();
		}
		
	}

}
