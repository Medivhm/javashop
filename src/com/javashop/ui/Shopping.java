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
				System.out.println("登录失败，请重新登录");
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
				System.out.println("添加成功，请选择下一步操作：1-继续添加；2-进入结算；3-结束添加");
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
				System.out.println("购买失败");
			}
		}
	}

	/**
	 * 显示所有商品
	 * 
	 * @return 返回所有商品ID组成的数组
	 */
	public String[] showGoods() {
		int i = 0;
		String[] ids = null;
		list = goodsBiz.getGoodsList();
		ids = new String[list.size()];
		System.out.println("---------------------------商品信息如下：---------------------------");
		System.out.println(
				"商品编号                商品名称                                                     产地                    价格");
		for (Goods item : list) {
			System.out.printf("%-25s%-50s\t%-20s%-10s\n", item.getId(), item.getGoodsName(), item.getProdArea(),
					item.getPrice());
			ids[i++] = String.valueOf(item.getId());
		}
		System.out.println("输入想要添加的商品的编号吧");
		return ids;
	}

	/**
	 * 添加入购物车
	 * 
	 * @param goodsId
	 *            所选id
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
		// System.out.println("选购的商品如下：");
		// System.out.println("商品编号 商品价格 商品数量");
		// for(SelectedGoods item:DataUtil.car){
		// System.out.printf("%-15s%-15s%-10s\n",item.getGoodsId(),"100$",item.getNumber());
		// }
		if(ok>0){
			System.out.println("购买成功");
		}else{
			System.out.println("购买失败");
		}
		MenuFactory.createMenu().start();
	}
}
