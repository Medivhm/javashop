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
	 * ִ�����ݿ���ɾ�Ĳ���
	 * 
	 * @param sql
	 *            Ҫִ�е�sql��䣬���ܲ���
	 * @param param
	 *            Ҫע��sql����еĲ������飬���û�в�������nullֵ
	 * @return ����ִ�гɹ���Ӱ������
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
			System.out.println("ִ��DBHelper.executeUpdate��������");
			e.printStackTrace();
		} finally {
			close();
		}

		return ok;
	}

	/**
	 * ִ�����ݿ��������˷��������꣬��ؼǵ��ٴε���close�����ر�����
	 * 
	 * @param sql
	 *            Ҫִ�е�sql��䣬���ܲ���
	 * @param param
	 *            Ҫע��sql����еĲ������飬���û�в�������nullֵ
	 * @return ����ִ�гɹ��ĺ���
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
			System.out.println("ִ��DBHelper.executeQuery��������");
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * ִ�в�ѯ���õ���һ�е�һ�е�ֵ
	 * 
	 * @param sql
	 *            Ҫִ�е�sql��䣬���ܲ���
	 * @param param
	 *            Ҫע��sql����еĲ������飬���û�в�������nullֵ
	 * @return ���ص�һ�е�һ�����ݵ����ֽ��
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
			System.out.println("ִ��DBHelper.executeQuery��������");
			e.printStackTrace();
		} finally {
			close();
		}
		return val;
	}

	/**
	 * �ر�Connection
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
