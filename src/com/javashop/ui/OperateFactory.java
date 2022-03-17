package com.javashop.ui;

/**
 * @author Medivhm
 *
 */
public class OperateFactory {
	/**
	 * ���������࣬����ȷ��ʹ���ĸ�����Ĳ�������
	 * @param str-�����ж�����ĵ��ж����ݣ�ֵΪ1��2��3���е�һ��
	 * @return-���ؾ���Ĳ������࣬����жϴ��󣬷���null
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
