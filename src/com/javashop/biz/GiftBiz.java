package com.javashop.biz;

import java.util.ArrayList;
import java.util.List;

import com.javashop.dao.GiftDao;
import com.javashop.entity.Gift;
import com.javashop.entity.MyGifts;

public class GiftBiz {
	GiftDao dao = new GiftDao();

	public List<Gift> getGifts(int userId) {
		return dao.getGiftsByUserId(userId);
	}

	/**
	 * 计算获得的礼物的数量
	 * 
	 * @param userId
	 *            用户id
	 * @return 返回礼物和礼物数量的类
	 */
	public List<MyGifts> getCalGifts(int userId) {
		List<Gift> gifts = getGifts(userId);
		List<MyGifts> myGifts = new ArrayList<>();
		for (Gift gift : gifts) {
			boolean have = false;
			for (MyGifts myGift : myGifts) {
				// 如果多次出现，则number+1
				if (gift.getId() == myGift.getGift().getId()) {
					have = true;
					myGift.setNumber(myGift.getNumber() + 1);
					break;
				}
			}
			// 未出现，则添加gift和number
			if (!have) {
				myGifts.add(new MyGifts(gift, 1));
			}
		}
		return myGifts;
	}
	
	public Gift getGiftByGiftId(int giftId){
		return dao.getGiftByGiftId(giftId);
	}
}
