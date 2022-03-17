package com.javashop.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.javashop.entity.Goods;

public class GoodsDao {
	DBHelper db = new DBHelper();
	
	public List<Goods> getGoodsList(){
		List<Goods> list=new ArrayList<Goods>();
		String sql="select * from goods";
		ResultSet rs=db.executeQuery(sql, null);
		try{
			while(rs.next()){
				Goods goods=new Goods();
				goods.setId(rs.getInt("id"));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setProdArea(rs.getString("prodArea"));
				goods.setSpec(rs.getString("spec"));
				goods.setPrice(rs.getDouble("price"));
				list.add(goods);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		db.close();
		return list;
	}
}
