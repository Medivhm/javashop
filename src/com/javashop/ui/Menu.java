package com.javashop.ui;

import java.sql.SQLException;
import java.util.Scanner;

import com.javashop.biz.UsersBiz;
import com.javashop.entity.Users;

import util.DataUtil;

/**
 * @author Medivhm
 *
 */
public class Menu {

	/**
	 * 显示系统主菜单
	 */
	public void showMainMenu() {
		System.out.println("*********请选择需要的操作*********");
		System.out.println("*         1.会员中心                        *");
		System.out.println("*         2.进入购物                        *");
		System.out.println("*         3.手气之旅                        *");
		System.out.println("********************************");
	}

	/**
	 * 显示进入会员中心后的菜单
	 */
	public void showMemberCenterMenu() {
		System.out.println("*********请选择需要的操作*********");
		System.out.println("*         1.修改密码                        *");
		System.out.println("*         2.我的订单                        *");
		System.out.println("*         3.我的礼物                        *");
		System.out.println("*         4.返回上层                        *");
		System.out.println("********************************");
	}

	public void showLuckyCenterMenu() {
		System.out.println("*********请选择需要的操作*********");
		System.out.println("*         1.开始手气之旅                 *");
		System.out.println("*         2.返回上层                        *");
		System.out.println("********************************");
	}

	/**
	 * 判断输入是否合法
	 * 
	 * @param value
	 *            合法字符数组
	 * @return 返回已符合标准输入
	 */
	public String acceptInput(String[] value) {
		Scanner scanner = new Scanner(System.in);
		String str = "";
		boolean exist = false;
		System.out.print("请输入:");
		outer: while (!exist) {
			str = scanner.next();
			for (String item : value) {
				if (item.equals(str)) {
					exist = true;
					break outer;
				}
			}
			System.out.println("输入错误，是否重新输入，重输输y，其余退出");
			String reput = scanner.next();
			if(!"y".equals(reput))
				return null;
		}
		return str;
	}

	public boolean Login() {
		String go = "y";
		while ("y".equals(go)) {
			Users users = new Users();
			Scanner scanner = new Scanner(System.in);
			System.out.print("请输入用户名:");
			String name = scanner.next();
			System.out.print("请输入密码:");
			String pwd = scanner.next();
			users.setLoginId(name);
			users.setLoginPwd(pwd);

			UsersBiz biz = new UsersBiz();
			try {
				users = biz.login(users);

				if (users == null) {
					System.out.println("登录失败，是否需要继续登录？继续请输入y，其它任意键取消登录");
					go = scanner.next();
				} else {
					DataUtil.users = users;
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public void start() {
		while (true) {
			showMainMenu();
			String str = acceptInput(new String[] { "1", "2", "3" });
			Operate op = OperateFactory.createOperate(str);
			op.start();
		}
	}
}
