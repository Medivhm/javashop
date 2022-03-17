package com.javashop.biz;

import java.sql.SQLException;

import com.javashop.dao.UsersDao;
import com.javashop.entity.Users;

public class UsersBiz {
	private UsersDao dao = new UsersDao();

	public Users login(Users users) throws SQLException {
		return dao.login(users);
	}

	public int editPwd(String pwd,int id) throws SQLException{
		return dao.editPwdById(pwd, id);
	}
}
