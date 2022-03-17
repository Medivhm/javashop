package com.javashop.ui;

import java.util.List;
import java.util.Scanner;

import com.javashop.biz.GoodsBiz;
import com.javashop.biz.OrderBiz;
import com.javashop.entity.Goods;
import com.javashop.entity.SelectedGoods;

import util.DataUtil;

/**
 * @author Medivhm
 *
 */
public class Shopping implements Operate {
	private GoodsBiz goodsBiz = new GoodsBiz();
	OrderBiz orderBiz = new OrderBiz();
	List<Goods> list = null;

	@Override
	public void start() {
		if (DataUtil.users == null) {
			boolean ok = MenuFactory.createMenu().Login();
			if (ok) {
				buy();
			} else {
				System.out.println("��¼ʧ�ܣ������µ�¼");
				MenuFactory.createMenu().start();
			}
		} else {
			buy();
		}
	}

	public void buy() {
		boolean loop = true;
		String[] ids = showGoods();
		String id = "";
		String str = "";
		String[] value = { "1", "2", "3" };
		while (loop) {
			loop = false;
			id = MenuFactory.createMenu().acceptInput(ids);
			if (addToCar(id)) {
				System.out.println("��ӳɹ�����ѡ����һ��������1-������ӣ�2-������㣻3-�������");
				str = MenuFactory.createMenu().acceptInput(value);
				switch (str) {
				case "1":
					loop = true;
					break;
				case "2":
					order();
					break;
				default:
					MenuFactory.createMenu().start();
				}
			} else {
				System.out.println("����ʧ��");
			}
		}
	}

	/**
	 * ��ʾ������Ʒ
	 * 
	 * @return ����������ƷID��ɵ�����
	 */
	public String[] showGoods() {
		int i = 0;
		String[] ids = null;
		list = goodsBiz.getGoodsList();
		ids = new String[list.size()];
		System.out.println("---------------------------��Ʒ��Ϣ���£�---------------------------");
		System.out.println(
				"��Ʒ���                ��Ʒ����                                                     ����                    �۸�");
		for (Goods item : list) {
			System.out.printf("%-25s%-50s\t%-20s%-10s\n", item.getId(), item.getGoodsName(), item.getProdArea(),
					item.getPrice());
			ids[i++] = String.valueOf(item.getId());
		}
		System.out.println("������Ҫ��ӵ���Ʒ�ı�Ű�");
		return ids;
	}

	/**
	 * ����빺�ﳵ
	 * 
	 * @param goodsId
	 *            ��ѡid
	 */
	public boolean addToCar(String goodsId) {
		boolean isExist = false;
		for (int i = 0; i < DataUtil.car.size(); i++) {
			if (DataUtil.car.get(i).getGoodsId() == Integer.parseInt(goodsId)) {
				isExist = true;
				DataUtil.car.get(i).setNumber(DataUtil.car.get(i).getNumber() + 1);
				break;
			}
		}
		if (!isExist) {
			SelectedGoods goods = new SelectedGoods();
			goods.setGoodsId(Integer.parseInt(goodsId));
			goods.setNumber(1);
			DataUtil.car.add(goods);
			isExist = true;
		}
		return isExist;
	}

	public void order() {
		int ok = orderBiz.createOrder(DataUtil.car, list);
		// System.out.println("ѡ������Ʒ���£�");
		// System.out.println("��Ʒ��� ��Ʒ�۸� ��Ʒ����");
		// for(SelectedGoods item:DataUtil.car){
		// System.out.printf("%-15s%-15s%-10s\n",item.getGoodsId(),"100$",item.getNumber());
		// }
		if(ok>0){
			System.out.println("����ɹ�");
		}else{
			System.out.println("����ʧ��");
		}
		MenuFactory.createMenu().start();
	}
}
