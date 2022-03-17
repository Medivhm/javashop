package com.javashop.ui;

/**
 * @author Medivhm
 *
 */
public class OperateFactory {
	/**
	 * 工厂操作类，用来确定使用哪个具体的操作子类
	 * @param str-用来判断子类的的判断依据，值为1，2，3当中的一个
	 * @return-返回具体的操作子类，如果判断错误，返回null
	 */
	public static Operate createOperate(String str){
		Operate ope = null;
		switch (str) {
		case "1":
			ope = new Member();
			break;
		case "2":
			ope = new Shopping();
			break;
		case "3":
			ope = new Lucky();
			break;
		}
		return ope;
	}
}
