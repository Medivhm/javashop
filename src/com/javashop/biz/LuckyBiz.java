package com.javashop.biz;

import com.javashop.dao.LuckyDao;

public class LuckyBiz {
	LuckyDao dao=new LuckyDao();
	
	public void addGift(int giftId){
		dao.addGiftByGiftId(giftId);
	}
}
