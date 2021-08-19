package com.edu.neu.csye6200.model;

public class Person {
	private int id; // 编号
	private String name; // 姓名
	private String sn; // 员工编号
	private String sex; // 性别
	private String dept; // 所在院系
	private Integer classId; // 班级Id
	private String className; // 班级名称
	private String address; // 家庭住址
	
	
	public Person() {
		super();
		
	}
	
	public Person(String name, String sn, String sex, String dept, Integer calssId, String address) {
		super();
		this.name = name;
		this.sn = sn;
		this.sex = sex;
		this.dept = dept;
		this.classId = calssId;
		this.address = address;
	}
	public Person(int id, String name, String sn, String sex, String dept, Integer calssId, String address) {
		super();
		this.id = id;
		this.name = name;
		this.sn = sn;
		this.sex = sex;
		this.dept = dept;
		this.classId = calssId;
		this.address = address;
	}



	public Person(String name, String sn, Integer calssId) {
		super();
		this.name = name;
		this.sn = sn;
		this.classId = calssId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return  this.getName() + "," + this.getSn() + "," + 
	this.getSex() + "," + this.getDept() + "," + this.getClassId() + "," + this.getAddress();
	}
	

}
