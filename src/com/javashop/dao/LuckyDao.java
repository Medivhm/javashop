package com.javashop.dao;


import util.DataUtil;

public class LuckyDao {
	DBHelper db=new DBHelper();
	/**
	 * Ϊ�û���ӻ�õ�����
	 * @param giftId ����ı��
	 * @return ����1��ӳɹ�������0���ʧ��
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
