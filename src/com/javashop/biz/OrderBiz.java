package com.javashop.biz;

import java.util.List;

import com.javashop.dao.OrderDao;
import com.javashop.entity.Goods;
import com.javashop.entity.Orders;
import com.javashop.entity.SelectedGoods;

public class OrderBiz {
	OrderDao dao=new OrderDao();
	
	public List<Orders> getOrders(int userId){
		return dao.getOrdersByUserId(userId);
	}
	
	/**
	 * ���ɶ���
	 * @param car ���ﳵ�б�
	 * @param goods ������Ʒ�б�
	 * @return
	 */
	public int createOrder(List<SelectedGoods> car,List<Goods> goods){
		double total=0;
		double price=0;
		for(SelectedGoods selGoods:car){
			for(Goods good : goods){
				if(selGoods.getGoodsId()==good.getId()){
					price = good.getPrice();
					break;
				}
			}
			total+=price*selGoods.getNumber();
		}
		return dao.createOrder(car, total);
	}
}
