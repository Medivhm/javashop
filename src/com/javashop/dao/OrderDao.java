package com.javashop.dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.javashop.entity.Goods;
import com.javashop.entity.OrderDetail;
import com.javashop.entity.Orders;
import com.javashop.entity.SelectedGoods;

import util.DataUtil;

public class OrderDao {
	DBHelper db = new DBHelper();

	public List<Orders> getOrdersByUserId(int userId) {
		List<Orders> orders = new ArrayList<>();
		String sql = "select id,total,addDate,ispay from orders where userId=?";
		String[] param = { String.valueOf(userId) };
		ResultSet rs = db.executeQuery(sql, param);
		try {
			while (rs.next()) {
				Orders order = new Orders();
				order.setId(rs.getInt("id"));
				order.setTotal(rs.getDouble("total"));
				order.setAddDate(rs.getString("addDate"));
				order.setIsPay(rs.getString("isPay"));
				sql = "select goodsName,prodArea,price,number from orderDetail,goods where "
						+ "orderdetail.goodsId=goods.id and orderdetail.orderId=" + order.getId();
				List<OrderDetail> orderDetails = new ArrayList<>();
				ResultSet detailRS = db.executeQuery(sql, null);
				while (detailRS.next()) {
					OrderDetail oDetail = new OrderDetail();

					Goods goods = new Goods();
					goods.setGoodsName(detailRS.getString("goodsName"));
					goods.setProdArea(detailRS.getString("prodArea"));
					goods.setPrice(detailRS.getDouble("price"));

					oDetail.setGoods(goods);
					oDetail.setNumber(detailRS.getInt("number"));

					orderDetails.add(oDetail);
				}
				order.setOrderDetails(orderDetails);
				orders.add(order);
				detailRS.close();
			}
			rs.close();
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return orders;
	}

	/**
	 * 生成订单
	 * 
	 * @param car
	 *            用户选择的代购买商品列表
	 * @param total
	 *            代购买商品总金额
	 * @return
	 */
	public int createOrder(List<SelectedGoods> car, double total) {
		int ok = 0;
		String sql = "insert into orders(userId,total,addDate,isPay) values(?,?,?,?)";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String addDate = format.format(new Date());
		String[] param = { String.valueOf(DataUtil.users.getId()), String.valueOf(total), addDate, "0" };
		ok=db.executeUpdate(sql, param);
		if(ok>0){
			sql="select max(id) from orders where userId="+DataUtil.users.getId();
			int orderId=db.getScalarQuery(sql, null);
			for(SelectedGoods item : car){
				sql="insert into orderDetail(orderId,goodsId,number) values("+orderId+","+item.getGoodsId()+","+item.getNumber()+")";
				ok+=db.executeUpdate(sql, null);
			}
		}
		return ok;
	}

}
