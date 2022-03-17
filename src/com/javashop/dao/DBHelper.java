package com.javashop.dao;

import java.sql.*;

/**
 * @author Medivhm
 *
 */
public class DBHelper {
	private Connection conn = null; 
	private void openConn() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javashop?characaterEncoding=utf8", "root",
				"123456");

	}

	/**
	 * 执行数据库增删改操作
	 * 
	 * @param sql
	 *            要执行的sql语句，接受参数
	 * @param param
	 *            要注入sql语句中的参数数组，如果没有参数传递null值
	 * @return 返回执行成功的影响行数
	 */
	public int executeUpdate(String sql, String[] param) {
		int ok = 0;
		try {
			openConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					ps.setString(i + 1, param[i]);
				}
			}
			ok = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("执行DBHelper.executeUpdate方法出错");
			e.printStackTrace();
		} finally {
			close();
		}

		return ok;
	}

	/**
	 * 执行数据库查操作，此方法调用完，务必记得再次调用close方法关闭连接
	 * 
	 * @param sql
	 *            要执行的sql语句，接受参数
	 * @param param
	 *            要注入sql语句中的参数数组，如果没有参数传递null值
	 * @return 返回执行成功的函数
	 */
	public ResultSet executeQuery(String sql, String[] param) {
		ResultSet rs = null;
		try {
			openConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					ps.setString((i + 1), param[i]);
				}
			}
			rs = ps.executeQuery();
		} catch (Exception e) {
			System.out.println("执行DBHelper.executeQuery方法出错");
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 执行查询，得到第一行第一列的值
	 * 
	 * @param sql
	 *            要执行的sql语句，接受参数
	 * @param param
	 *            要注入sql语句中的参数数组，如果没有参数传递null值
	 * @return 返回第一行第一列数据的数字结果
	 */
	public int getScalarQuery(String sql, String[] param) {
		int val = 0;
		ResultSet rs = null;
		try {
			openConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					ps.setString((i + 1), param[i]);
				}
			}
			rs = ps.executeQuery(sql);
			if (rs.next()) {
				val = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("执行DBHelper.executeQuery方法出错");
			e.printStackTrace();
		} finally {
			close();
		}
		return val;
	}

	/**
	 * 关闭Connection
	 */
	public void close() {
		try {
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
