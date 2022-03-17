package com.javashop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javashop.entity.Gift;

public class GiftDao {
	DBHelper db = new DBHelper();

	public List<Gift> getGiftsByUserId(int userId) {
		List<Gift> gifts = new ArrayList<>();
		String sql = "select giftId from usergift where userId=?";
		String[] param = { String.valueOf(userId) };
		ResultSet rs = db.executeQuery(sql, param);
		try {
			while (rs.next()) {
				int giftId = (rs.getInt("giftId"));
				sql = "select giftName,price from gift where id=" + giftId;
				ResultSet detailRS = db.executeQuery(sql, null);
				while (detailRS.next()) {
					Gift gift = new Gift();

					gift.setId(giftId);
					gift.setGiftName(detailRS.getString("giftName"));
					gift.setPrice(detailRS.getDouble("price"));

					gifts.add(gift);
				}

				detailRS.close();
			}
			rs.close();
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return gifts;
	}

	public Gift getGiftByGiftId(int giftId) {
		Gift gift = new Gift();
		String sql = "select giftName,price from gift where id=?";
		String[] param = { String.valueOf(giftId) };
		ResultSet rs = db.executeQuery(sql, param);
		try {
			while (rs.next()) {
				gift.setId(giftId);
				gift.setGiftName(rs.getString("giftName"));
				gift.setPrice(rs.getDouble("price"));
			}
			rs.close();
			db.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gift;
	}
}
