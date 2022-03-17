package com.javashop.ui;

public class MenuFactory {
	private static Menu menu = null;

	private MenuFactory(){
		
	}
	
	public static Menu createMenu() {
		if (menu == null) {
			menu = new Menu();
		}
		return menu;
	}
}
