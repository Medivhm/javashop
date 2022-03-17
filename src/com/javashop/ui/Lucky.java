package com.javashop.ui;

import java.util.Scanner;

import com.javashop.biz.GiftBiz;
import com.javashop.biz.LuckyBiz;
import com.javashop.entity.Gift;

import util.DataUtil;

/**
 * @author Medivhm
 *
 */
public class Lucky implements Operate {
	Menu menu = new Menu();

	@Override
	public void start() {
		if (DataUtil.users == null) {
			boolean ok = MenuFactory.createMenu().Login();
			if (ok) {
				fun();
			} else {
				System.out.println("登录失败，请重新登录");
				MenuFactory.createMenu().start();
			}
		} else {
			fun();
		}
	}

	public void fun() {
		String str = "";
		menu.showLuckyCenterMenu();
		str = menu.acceptInput(new String[] { "1", "2" });
		switch (str) {
		case "1":
			play();
			break;
		case "2":
			break;
		default:
		}
	}

	public void play() {
		GiftBiz biz = new GiftBiz();
		LuckyBiz lbiz = new LuckyBiz();
		Scanner sc = new Scanner(System.in);
		int times = 3;
		while (times > 0) {
			times--;
			int correctNum = (int) (Math.random() * 10);
			Gift gift = biz.getGiftByGiftId(correctNum);
			System.out.println("请在0-9之间任意选择一个数，作为你的幸运数字。");
			System.out.print("请输入：");
			String playerNum = sc.next();
			String returnStr = "";
			if (correctNum == Integer.valueOf(playerNum)) {
				System.out.println("恭喜！您获得一下礼物：" + gift.getGiftName() + "，价值：" + gift.getPrice() + "元，可在'我的礼物'中查看");

				lbiz.addGift(gift.getId());

				System.out.println("返回至手气之旅请输入#，输入其它返回主菜单");
			} else {
				System.out.println("很遗憾，您未能获得礼物，本次幸运数字是：" + correctNum + "，您还有" + times + "次机会");
				System.out.println("继续请输入1，返回至手气之旅请输入#，输入其它返回主菜单");
			}
			returnStr = sc.next();
			if ("1".equals(returnStr)) {
				if(times==0){
					System.out.println("游戏结束，返回主菜单");
				}
			} else if ("#".equals(returnStr)) {
				fun();
				break;
			} else {
				times = -1;
			}
		}
	}
}
