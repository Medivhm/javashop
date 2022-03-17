package com.javashop.dao;

import java.sql.ResultSet;

import com.javashop.entity.Users;

import util.DataUtil;

/**
 * @author Medivhm
 *
 */
public class UsersDao {
	DBHelper dbHelper = new DBHelper();

	public Users login(Users users) {
		String sql = "select id,realName,score from users where loginId=? and loginPwd=?";
		String[] param = { users.getLoginId(), users.getLoginPwd() };
		ResultSet rs = null;
		try {
			rs=dbHelper.executeQuery(sql, param);
			if (rs.next()) {
				users.setId(rs.getInt("id"));
				users.setRealName(rs.getString("realName"));
				users.setScore(rs.getInt("score"));
				DataUtil.users=users;
			} else {
				users = null;
			}
		} catch (Exception e) {
			System.out.println("ÓÃ»§µÇÂ¼Òì³£" + e.getMessage());
			e.printStackTrace();
		} finally{
			dbHelper.close();
		}
		return users;
	}

	public int editPwdById(String pwd, int id) {
		int ok = 0;
		String sql = "update users set loginPwd=? where id=?";
		String[] param = { pwd, String.valueOf(id) };
		ok = dbHelper.executeUpdate(sql, param);
		return ok;
	}
}
