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
	 * �����õ����������
	 * 
	 * @param userId
	 *            �û�id
	 * @return ���������������������
	 */
	public List<MyGifts> getCalGifts(int userId) {
		List<Gift> gifts = getGifts(userId);
		List<MyGifts> myGifts = new ArrayList<>();
		for (Gift gift : gifts) {
			boolean have = false;
			for (MyGifts myGift : myGifts) {
				// �����γ��֣���number+1
				if (gift.getId() == myGift.getGift().getId()) {
					have = true;
					myGift.setNumber(myGift.getNumber() + 1);
					break;
				}
			}
			// δ���֣������gift��number
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
