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
	 * ��ʾϵͳ���˵�
	 */
	public void showMainMenu() {
		System.out.println("*********��ѡ����Ҫ�Ĳ���*********");
		System.out.println("*         1.��Ա����                        *");
		System.out.println("*         2.���빺��                        *");
		System.out.println("*         3.����֮��                        *");
		System.out.println("********************************");
	}

	/**
	 * ��ʾ�����Ա���ĺ�Ĳ˵�
	 */
	public void showMemberCenterMenu() {
		System.out.println("*********��ѡ����Ҫ�Ĳ���*********");
		System.out.println("*         1.�޸�����                        *");
		System.out.println("*         2.�ҵĶ���                        *");
		System.out.println("*         3.�ҵ�����                        *");
		System.out.println("*         4.�����ϲ�                        *");
		System.out.println("********************************");
	}

	public void showLuckyCenterMenu() {
		System.out.println("*********��ѡ����Ҫ�Ĳ���*********");
		System.out.println("*         1.��ʼ����֮��                 *");
		System.out.println("*         2.�����ϲ�                        *");
		System.out.println("********************************");
	}

	/**
	 * �ж������Ƿ�Ϸ�
	 * 
	 * @param value
	 *            �Ϸ��ַ�����
	 * @return �����ѷ��ϱ�׼����
	 */
	public String acceptInput(String[] value) {
		Scanner scanner = new Scanner(System.in);
		String str = "";
		boolean exist = false;
		System.out.print("������:");
		outer: while (!exist) {
			str = scanner.next();
			for (String item : value) {
				if (item.equals(str)) {
					exist = true;
					break outer;
				}
			}
			System.out.println("��������Ƿ��������룬������y�������˳�");
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
			System.out.print("�������û���:");
			String name = scanner.next();
			System.out.print("����������:");
			String pwd = scanner.next();
			users.setLoginId(name);
			users.setLoginPwd(pwd);

			UsersBiz biz = new UsersBiz();
			try {
				users = biz.login(users);

				if (users == null) {
					System.out.println("��¼ʧ�ܣ��Ƿ���Ҫ������¼������������y�����������ȡ����¼");
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
