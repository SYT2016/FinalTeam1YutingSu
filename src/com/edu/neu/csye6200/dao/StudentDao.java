package com.edu.neu.csye6200.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.edu.neu.csye6200.model.Student;
import com.edu.neu.csye6200.util.StringUtil;

public class StudentDao {

	/**
	 * @param con
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con, Student student)throws Exception{
		String sql="insert into t_student values(null,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, student.getName());
		pstmt.setString(2, student.getSn());
		pstmt.setString(3, student.getSex());
		pstmt.setString(4, student.getDept());
		pstmt.setInt(5, student.getClassId());
		pstmt.setString(6, student.getAddress());
		return pstmt.executeUpdate();
	}
	
//search for student
	public ResultSet list(Connection con,Student student)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_student b,t_school_class bt where b.classId=bt.id");
		if(StringUtil.isNotEmpty(student.getName())){
			sb.append(" and b.name like '%"+student.getName()+"%'");
		}
		if(StringUtil.isNotEmpty(student.getSn())){
			sb.append(" and b.sn like '%"+student.getSn()+"%'");
		}
		if(student.getClassId()!=null && student.getClassId()!=-1){
			sb.append(" and b.classId="+student.getClassId());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}

	public int delete(Connection con,String id)throws Exception{
		
		String sql="delete from t_student where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}
	

	public int update(Connection con,Student student)throws Exception{
		String sql="update t_student set name=?,sn=?,sex=?,dept=?,address=?,classId=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, student.getName());
		pstmt.setString(2, student.getSn());
		pstmt.setString(3, student.getSex());
		pstmt.setString(4, student.getDept());
		pstmt.setString(5, student.getAddress());
		pstmt.setInt(6, student.getClassId());
		pstmt.setInt(7, student.getId());
		return pstmt.executeUpdate();
	}
	

	public boolean existStudentByclassId(Connection con,String classId)throws Exception{
		String sql="select * from t_student where classId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, classId);
		ResultSet rs=pstmt.executeQuery();
		return rs.next();
	}
}
