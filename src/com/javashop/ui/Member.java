package com.javashop.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.javashop.biz.GiftBiz;
import com.javashop.biz.OrderBiz;
import com.javashop.biz.UsersBiz;
import com.javashop.entity.MyGifts;
import com.javashop.entity.OrderDetail;
import com.javashop.entity.Orders;

import util.DataUtil;

/**
 * @author Medivhm
 *
 */
public class Member implements Operate {
	Menu menu = MenuFactory.createMenu();
	UsersBiz biz = new UsersBiz();

	@Override
	public void start() {
		menu.showMemberCenterMenu();
		String str = menu.acceptInput(new String[] { "1", "2", "3", "4" });
		switch (str) {
		case "1":
			try {
				modifyPwd();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "2":
			try {
				showOrders();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "3":
			showGift();
			break;
		case "4":
			System.out.println();
		default:
			MenuFactory.createMenu().showMainMenu();
		}
	}

	public void showGift() {
		if (DataUtil.users == null) {
			boolean ok = MenuFactory.createMenu().Login();
			if (!ok) {
				System.out.println("��¼ʧ�ܣ������µ�¼");
				MenuFactory.createMenu().start();
			}
		} else {
			giftList();
		}
	}

	public void giftList() {
		GiftBiz biz = new GiftBiz();
		List<MyGifts> myGifts = biz.getCalGifts(DataUtil.users.getId());
		System.out.println("----------------------------������Ϣ���£�----------------------------");
		System.out.println(
				"������                      ��������                                                           �����ֵ                ��������  ");
		for (MyGifts myGift : myGifts) {
			System.out.printf("%-12s\t%-50s\t%-15s%-15s\n", myGift.getGift().getId(), myGift.getGift().getGiftName(),
					myGift.getGift().getPrice(), myGift.getNumber());
		}
		System.out.println("---------------------------------------------------------------------");
		System.out.println();

		MenuFactory.createMenu().start();

	}

	public void modifyPwd() throws SQLException {
		if (DataUtil.users == null) {
			boolean ok = MenuFactory.createMenu().Login();
			if (!ok) {
				System.out.println("��¼ʧ�ܣ������µ�¼");
				MenuFactory.createMenu().start();
			}
		} else {
			editPwd();
		}

	}

	public void editPwd() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("������������:");
		String newPwd = scanner.next();
		int ok = biz.editPwd(newPwd, DataUtil.users.getId());
		if (ok > 0) {
			System.out.println("�����޸ĳɹ�");
		} else {
			System.out.println("�����޸�ʧ��");
		}
		MenuFactory.createMenu().start();
	}

	public void showOrders() throws SQLException {
		if (DataUtil.users == null) {
			boolean ok = MenuFactory.createMenu().Login();
			if (!ok) {
				System.out.println("��¼ʧ�ܣ������µ�¼");
				MenuFactory.createMenu().start();
			}
		} else {
			orderList();
		}
	}

	public void orderList() {
		OrderBiz biz = new OrderBiz();
		List<Orders> orders = biz.getOrders(DataUtil.users.getId());
		for (Orders order : orders) {
			System.out.println("----------------------------������Ϣ���£�----------------------------");
			System.out.println("������              �������                  �µ�ʱ��                              ֧��״̬");
			System.out.printf("%-12s%-10s%-30s%-4s\n", order.getId(), order.getTotal(), order.getAddDate(),
					("0".equals(order.getIsPay()) ? "��" : "��"));
			System.out.println("--------------------------------------------------------------------");

			System.out.println();
			System.out.println();

			System.out.println("-------------------------��Ʒ��ϸ��Ϣ���£�----------------------------");
			System.out.println(
					"��Ʒ����                                                    ����                   ����                 С��");
			if (order.getOrderDetails() != null) {
				for (OrderDetail detail : order.getOrderDetails()) {
					System.out.printf("%-40s\t%-15s%-10s%-12s\n", detail.getGoods().getGoodsName(),
							detail.getGoods().getPrice(), detail.getNumber(),
							detail.getGoods().getPrice() * detail.getNumber());
				}
			}
			System.out.println("--------------------------------------------------------------------");
			System.out.println();
		}

		System.out.println();
		MenuFactory.createMenu().start();
	}

}
