package com.javashop.biz;

import java.util.List;

import com.javashop.dao.GoodsDao;
import com.javashop.entity.Goods;

public class GoodsBiz {
	private GoodsDao goodsDao = new GoodsDao();
	
	public List<Goods> getGoodsList(){
		return goodsDao.getGoodsList();
	}
}
