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
				System.out.println("��¼ʧ�ܣ������µ�¼");
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
			System.out.println("����0-9֮������ѡ��һ��������Ϊ����������֡�");
			System.out.print("�����룺");
			String playerNum = sc.next();
			String returnStr = "";
			if (correctNum == Integer.valueOf(playerNum)) {
				System.out.println("��ϲ�������һ�����" + gift.getGiftName() + "����ֵ��" + gift.getPrice() + "Ԫ������'�ҵ�����'�в鿴");

				lbiz.addGift(gift.getId());

				System.out.println("����������֮��������#�����������������˵�");
			} else {
				System.out.println("���ź�����δ�ܻ������������������ǣ�" + correctNum + "��������" + times + "�λ���");
				System.out.println("����������1������������֮��������#�����������������˵�");
			}
			returnStr = sc.next();
			if ("1".equals(returnStr)) {
				if(times==0){
					System.out.println("��Ϸ�������������˵�");
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
