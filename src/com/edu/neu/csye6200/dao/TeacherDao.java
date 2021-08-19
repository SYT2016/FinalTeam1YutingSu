package com.edu.neu.csye6200.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import com.edu.neu.csye6200.model.Teacher;
import com.edu.neu.csye6200.util.StringUtil;

public class TeacherDao {
	public int add(Connection con, Teacher teacher)throws Exception{
		String sql="insert into t_teacher values(null,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, teacher.getName());
		pstmt.setString(2, teacher.getSn());
		pstmt.setString(3, teacher.getSex());
		pstmt.setString(4, teacher.getDept());
		pstmt.setInt(5, teacher.getClassId());
		pstmt.setString(6, teacher.getAddress());
		return pstmt.executeUpdate();
	}
	

	public ResultSet list(Connection con,Teacher teacher)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_teacher b,t_school_class bt where b.classId=bt.id");
		if(StringUtil.isNotEmpty(teacher.getName())){
			sb.append(" and b.name like '%"+teacher.getName()+"%'");
		}
		if(StringUtil.isNotEmpty(teacher.getSn())){
			sb.append(" and b.sn like '%"+teacher.getSn()+"%'");
		}
		if(teacher.getClassId()!=null && teacher.getClassId()!=-1){
			sb.append(" and b.classId="+teacher.getClassId());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	

	public int delete(Connection con,String id)throws Exception{
		String sql="delete from t_teacher where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}
	

	public int update(Connection con,Teacher teacher)throws Exception{
		String sql="update t_teacher set name=?,sn=?,sex=?,dept=?,address=?,classId=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, teacher.getName());
		pstmt.setString(2, teacher.getSn());
		pstmt.setString(3, teacher.getSex());
		pstmt.setString(4, teacher.getDept());
		pstmt.setString(5, teacher.getAddress());
		pstmt.setInt(6, teacher.getClassId());
		pstmt.setInt(7, teacher.getId());
		return pstmt.executeUpdate();
	}
	

	public boolean existStudentByclassId(Connection con,String classId)throws Exception{
		String sql="select * from t_teacher where classId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, classId);
		ResultSet rs=pstmt.executeQuery();
		return rs.next();
	}
}
