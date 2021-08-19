package com.edu.neu.csye6200.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.edu.neu.csye6200.model.Person;

public class CsvAPI {
	public static void write(String s, String[] CsvFile) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(s);
            BufferedWriter out = new BufferedWriter(fw);

            System.out.println("\n" + "BufferedWriter: " + s
                    + " write " + CsvFile.length + " items" + "\n");

            for (String name: CsvFile) {
                out.write(name);
                out.newLine();
            }
            out.flush();
        } catch( Exception e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (Exception e2) {
                    e2.printStackTrace();;
                }
            }
        }
    }
	
	public static List<Person> read(String s) {
		List<Person> list = new ArrayList<>();
		FileReader fr = null;
		try	
		{
			fr = new FileReader(s);
			@SuppressWarnings("resource")
			BufferedReader inLine = new BufferedReader(fr);
			String inputLine = null;
			while ((inputLine = inLine.readLine()) != null) {
				String[] strs = inputLine.split(",");
				// whatever student or teacher
				String name = strs[0];
				String sn = strs[1];
				String sex = strs[2];
				String dept = strs[3];
				String classId = strs[4];
				String address = strs[5];
				list.add(new Person(name, sn, sex, dept, Integer.parseInt(classId), address));
				
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(fr != null)
					fr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
//	public boolean runCSV(){
//		DbUtil dbUtil=new DbUtil();
//		Connection con=null;
//		try{
//			con=dbUtil.getCon();
//			ResultSet rs=studentDao.list(con, student);
//			while(rs.next()){
//				Vector v=new Vector();
//				v.add(rs.getString("id"));
//				v.add(rs.getString("name"));
//				v.add(rs.getString("sn"));
//				v.add(rs.getString("sex"));
//				v.add(rs.getString("dept"));
//				v.add(rs.getString("address"));
//				v.add(rs.getString("className"));
//				dtm.addRow(v);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			try {
//				dbUtil.closeCon(con);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		return true;
//
//	}

}
