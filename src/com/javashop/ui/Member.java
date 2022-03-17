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
				System.out.println("登录失败，请重新登录");
				MenuFactory.createMenu().start();
			}
		} else {
			giftList();
		}
	}

	public void giftList() {
		GiftBiz biz = new GiftBiz();
		List<MyGifts> myGifts = biz.getCalGifts(DataUtil.users.getId());
		System.out.println("----------------------------礼物信息如下：----------------------------");
		System.out.println(
				"礼物编号                      礼物名称                                                           礼物价值                礼物数量  ");
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
				System.out.println("登录失败，请重新登录");
				MenuFactory.createMenu().start();
			}
		} else {
			editPwd();
		}

	}

	public void editPwd() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入新密码:");
		String newPwd = scanner.next();
		int ok = biz.editPwd(newPwd, DataUtil.users.getId());
		if (ok > 0) {
			System.out.println("密码修改成功");
		} else {
			System.out.println("密码修改失败");
		}
		MenuFactory.createMenu().start();
	}

	public void showOrders() throws SQLException {
		if (DataUtil.users == null) {
			boolean ok = MenuFactory.createMenu().Login();
			if (!ok) {
				System.out.println("登录失败，请重新登录");
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
			System.out.println("----------------------------订单信息如下：----------------------------");
			System.out.println("订单号              订单金额                  下单时间                              支付状态");
			System.out.printf("%-12s%-10s%-30s%-4s\n", order.getId(), order.getTotal(), order.getAddDate(),
					("0".equals(order.getIsPay()) ? "否" : "是"));
			System.out.println("--------------------------------------------------------------------");

			System.out.println();
			System.out.println();

			System.out.println("-------------------------商品明细信息如下：----------------------------");
			System.out.println(
					"商品名称                                                    单价                   数量                 小计");
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
