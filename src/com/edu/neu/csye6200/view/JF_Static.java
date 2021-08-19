package com.edu.neu.csye6200.view;

import com.edu.neu.csye6200.dao.CourseDao;
import com.edu.neu.csye6200.dao.StudentDao;
import com.edu.neu.csye6200.model.Course;
import com.edu.neu.csye6200.util.DbUtil;
import com.edu.neu.csye6200.util.GuiUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

public class JF_Static extends JInternalFrame {
    private JTable table_classes;
    private JTextArea area_classdesc=null;

    private DbUtil dbUtil=new DbUtil();
    private CourseDao courseDao =new CourseDao();
    private StudentDao studentDao=new StudentDao();


    public JF_Static() {
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        setClosable(true);
        setTitle("Static");
        setBounds(0, 0, GuiUtil.w-30, GuiUtil.h-90);
        JLabel jLabel=new JLabel("Sorted By:");
        jLabel.setBounds(5,5,150,30);
        contentPane.add(jLabel);

                    //创建并添加一个button
        JButton btn_name = new JButton("Name");
        btn_name.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //初始化table 按name顺序 在这里调初始化table的函数！！！！！！！
                System.out.println("press name btn");
            }
        });
        btn_name.setBounds(155,5,150,30);//改button大小！！！！！！x y是水平距离 w h是btn的长宽！
        contentPane.add(btn_name);

        //创建并添加一个button
        JButton btn_sex = new JButton("Sex");
        btn_sex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //初始化table 按name顺序 在这里调初始化table的函数！！！！！！！
                System.out.println("press sex btn");
            }
        });
        btn_sex.setBounds(355,5,150,30);
        contentPane.add(btn_sex);













        JScrollPane scrollPane = new JScrollPane();
        table_classes = new JTable();
        table_classes.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {"ID", "Class Name", "Class Description"}) {//设置表格title
            boolean[] columnEditables = new boolean[] {
                    false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        table_classes.getColumnModel().getColumn(1).setPreferredWidth(110);
        table_classes.getColumnModel().getColumn(2).setPreferredWidth(123);
        scrollPane.setViewportView(table_classes);
        scrollPane.setBounds(0,40, GuiUtil.w-30,200);
        contentPane.add(scrollPane);

        //初始化表格
        initTable(new Course());


    }



    private void initTable(Course schoolClass){
        DefaultTableModel dtm=(DefaultTableModel) table_classes.getModel();
        dtm.setRowCount(0); // 设置成0行
        Connection con=null;
        try{
            con=dbUtil.getCon();
            ResultSet rs= courseDao.list(con, schoolClass);
            while(rs.next()){
                Vector v=new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("className"));
                v.add(rs.getString("classDesc"));
                dtm.addRow(v);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                dbUtil.closeCon(con);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
