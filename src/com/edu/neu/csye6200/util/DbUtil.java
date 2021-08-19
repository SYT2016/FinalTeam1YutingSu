package com.edu.neu.csye6200.util;


import java.sql.Connection;
import java.sql.DriverManager;



/**
 * Database Utility
 * @author Runyao Xia
 *
 */
public class DbUtil {

	private String dbUrl="jdbc:mysql://localhost:3306/db_student_swing?characterEncoding=utf8"; // ���ݿ����ӵ�ַ
	private String dbUserName="root"; // �û���,��д���Լ������ݿ��û���
	private String dbPassword="123456"; // ���룬���Լ����û������룬��������ҵģ������Ҳ��޸���
	private String jdbcName="com.mysql.jdbc.Driver"; // ��������
	
	/**
	 * ��ȡ���ݿ�����
	 * @return
	 * @throws Exception
	 */
	public Connection getCon()throws Exception{
		Class.forName(jdbcName);
		Connection con=DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return con;
	}
	
	/**
	 * �ر����ݿ�����
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con)throws Exception{
		if(con!=null){
			con.close();
		}
	}
	
	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("connected��");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("disconnected");
		}
	}
	
}
