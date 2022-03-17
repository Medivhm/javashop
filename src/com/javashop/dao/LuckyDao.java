package com.javashop.dao;


import util.DataUtil;

public class LuckyDao {
	DBHelper db=new DBHelper();
	/**
	 * 为用户添加获得的礼物
	 * @param giftId 礼物的编号
	 * @return 返回1添加成功，返回0添加失败
	 */
	public int addGiftByGiftId(int giftId){
		int res=0;
		String sql = "insert into usergift(userId,giftId) values(?,?)";
		String[] param = { String.valueOf(DataUtil.users.getId()),String.valueOf(giftId) };
		res = db.executeUpdate(sql, param);
		db.close();
		return res;
	}
}
