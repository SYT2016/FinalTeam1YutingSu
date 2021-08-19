package com.edu.neu.csye6200.util;

//import java.text.NumberFormat.Style;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.edu.neu.csye6200.model.Student;
import com.edu.neu.csye6200.model.Teacher;

public class Statistics {

	private List<Student> stu; // teacher list
	private List<Teacher> tea; // student list
	private int stu_run_count = 0; // count for student thread run
	private int tea_run_count = 0; // count for teacher thread run
	private int stu_idx = 0; // index for locate stu obj
	private int tea_idx = 0; // index for locate Teacher obj
	private StringBuilder sb = new StringBuilder(); // build string for each student obj
	
	
	// default constructor to get two lists
	public Statistics() {
		stu = Student.getStuList();
		tea = Teacher.getTeaList();
	}
	
	// Student info display and sorting
	
	/*
	 * Function to print stu info to console
	 * @param - student collections
	 */
	public void normalStudentDisplay(List<Student> stu) {
		for(Student s : stu) {
			System.out.println(s.toString());
		}
	}
	
	public void getNormalStudentDisplay() {
		normalStudentDisplay(stu);
	}
	
	/*
	 * function to use two threads to print Student info
	 * @param - Collections of Student
	 */
	public List<String> studentDisplay(List<Student> stu){
		
		int total_size = stu.size() - 1; // total size for Student collection
        boolean[] next = {false}; // flag for thread switch
        List<String> output = new ArrayList<String>(); // string list for return
        
        
	    	Thread identifyInfoThread = new Thread(new Runnable(){
	        	
	        	@Override
	        	public void run() {
	        		try {
	        			synchronized (next) {
	        				while(stu_run_count <= total_size * 2) {
	        					while (next[0]) {
	        						next.wait();
	        					}
	        					
	        					sb.append("name: " + stu.get(stu_idx).getName() + ", sex: " + stu.get(stu_idx).getSex() + ", sn: " + stu.get(stu_idx).getSn() + ".");
	        					stu_run_count++;
	        					
	        					next[0] = true;
	        					next.notifyAll();
	        				}
	        			}
	        		} catch (InterruptedException e) {
	        			// expected
	        			e.printStackTrace();
	        		}
	        	}
	        });
	        
	        Thread relatedInfoThread = new Thread(new Runnable(){
	        	
	        	@Override
	        	public void run() {
	        		try {
	        			synchronized (next) {
	        				while(stu_run_count <= total_size * 2) {
	        					while (!next[0]) {
	        						next.wait();
	        					}
	        					
	        					sb.append(" address: " + stu.get(stu_idx).getAddress() + ", dept: " + stu.get(stu_idx).getDept() + ", class id: " + stu.get(stu_idx).getClassId() + ".");
	        					stu_run_count++;
	        					stu_idx++; // update student obj
	        					// System.out.println(sb.toString());
	        					output.add(sb.toString());
	        					sb = new StringBuilder(); // create a new builder for next obj
	        					
	        					next[0] = false;
	        					next.notifyAll();
	        				}
	        			}
	        		} catch (InterruptedException e) {
	        			// expected
	        			e.printStackTrace();
	        		}
	        	}
	        });
	        
	        
	        System.out.println("*** Strat print info of Student Collection ***");
	        
	        identifyInfoThread.start();
	        relatedInfoThread.start();
	        
	        try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	        
	        identifyInfoThread.interrupt();
	        relatedInfoThread.interrupt();
	        
	    	System.out.println("*** Done ***");
        
	    	return output;
	}
	
	public void getStudentDisplay() {
		studentDisplay(stu);
	}
	
	/*
	 * function to sort student by their name
	 * @param - Collctions of Student objects
	 */
	public void studentSortedByName(List<Student> stu) {
		
		System.out.println("***** Sort student by name *****");
		
		Collections.sort(stu, new Comparator<Student>() {
			
			@Override
			public int compare(Student s1, Student s2) {
				return s1.getName().compareTo(s2.getName());
			}
		});
		
		normalStudentDisplay(stu);
	}
	
	public void getStudentSortedByName() {
		studentSortedByName(stu);
	}
	
	/*
	 * function to sort student by their dept
	 * @param - Collctions of Student objects
	 */
	public void studentSortedByDept(List<Student> stu) {
		
		System.out.println("***** Sort student by dept *****");
		
		Collections.sort(stu, new Comparator<Student>() {
			
			@Override
			public int compare(Student s1, Student s2) {
				return s1.getDept().compareTo(s2.getDept());
			}
		});
		
		normalStudentDisplay(stu);
	}
	
	public void getStudentSortedByDept() {
		studentSortedByDept(stu);
	}
	
	/*
	 * function to sort student by their class id
	 * @param - Collctions of Student objects
	 */
	public void studentSortedClassId(List<Student> stu) {
		
		System.out.println("***** Sort student by class id *****");
		
		Collections.sort(stu, new Comparator<Student>() {
			
			@Override
			public int compare(Student s1, Student s2) {
				return Integer.compare(s1.getClassId(), s2.getClassId());
			}
		});
		
		normalStudentDisplay(stu);
	}
	
	public void getStudentSortedClassId() {
		studentSortedClassId(stu);
	}
	
	/*
	 * function to count student number by sex
	 * @param - Collections of Student objects
	 */
	public String countStudentBySex(List<Student> stu) {
		
		int male = (int) stu.stream()
				.filter(s -> s.getSex().equals("male"))
				.count();
		
		int female = (int) stu.stream()
				.filter(s -> s.getSex().equals("female"))
				.count();
		
		System.out.println("Male: " + male + ". Female: " + female + ".");
		return "Student - Male: " + male + ". Female: " + female + ".";
	}
	
	public void getCountStudentBySex() {
		countStudentBySex(stu);
	}
	
	/*
	 * Function to print stu info to console
	 * @param - student collections
	 */
	public void normalTeacherDisplay(List<Teacher> tea) {
		for(Teacher t : tea) {
			System.out.println(t.toString());
		}
	}
	
	public void getNormalTeacherDisplay() {
		normalTeacherDisplay(tea);
	}
	
	/*
	 * function to use two threads to print Teacher info
	 * @param - Collections of Teacher
	 */
	public List<String> teacherDisplay(List<Teacher> tea) {
		
		int total_size = tea.size() - 1; // total size for Student collection
        boolean[] next = {false}; // flag for thread switch
        List<String> output = new ArrayList<>(); // string list for return
        
        
	    	Thread identifyInfoThread = new Thread(new Runnable(){
	        	
	        	@Override
	        	public void run() {
	        		try {
	        			synchronized (next) {
	        				while(tea_run_count <= total_size * 2) {
	        					while (next[0]) {
	        						next.wait();
	        					}
	        					
	        					sb.append("name: " + tea.get(tea_idx).getName() + ", sex: " + tea.get(tea_idx).getSex() + ", sn: " + tea.get(tea_idx).getSn() + ".");
	        					tea_run_count++;
	        					
	        					next[0] = true;
	        					next.notifyAll();
	        				}
	        			}
	        		} catch (InterruptedException e) {
	        			// expected
	        			e.printStackTrace();
	        		}
	        	}
	        });
	        
	        Thread relatedInfoThread = new Thread(new Runnable(){
	        	
	        	@Override
	        	public void run() {
	        		try {
	        			synchronized (next) {
	        				while(tea_run_count <= total_size * 2) {
	        					while (!next[0]) {
	        						next.wait();
	        					}
	        					
	        					sb.append(" address: " + tea.get(tea_idx).getAddress() + ", dept: " + tea.get(tea_idx).getDept() + ", class id: " + tea.get(tea_idx).getClassId() + ".");
	        					tea_run_count++;
	        					tea_idx++; // update student obj
	        					output.add(sb.toString());
	        					sb = new StringBuilder(); // create a new builder for next obj
	        					
	        					next[0] = false;
	        					next.notifyAll();
	        				}
	        			}
	        		} catch (InterruptedException e) {
	        			// expected
	        			e.printStackTrace();
	        		}
	        	}
	        });
	        
	        
	        System.out.println("*** Start print info of Teacher Collection ***");
	        
	        identifyInfoThread.start();
	        relatedInfoThread.start();
	        
	        try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	        
	        identifyInfoThread.interrupt();
	        relatedInfoThread.interrupt();
	        
	    	System.out.println("*** Done ***");
	    	
	    	return output;
        
	}
	
	public void getTeacherDisplay() {
		teacherDisplay(tea);
	}
	
	/*
	 * function to sort teacher by their name
	 * @param - Collctions of Teacher objects
	 */
	public void teacherSortedByName(List<Teacher> tea) {
		
		System.out.println("***** Sort teacher by name *****");
		
		Collections.sort(tea, new Comparator<Teacher>() {
			
			@Override
			public int compare(Teacher t1, Teacher t2) {
				return t1.getName().compareTo(t2.getName());
			}
		});
		
		normalTeacherDisplay(tea);
	}
	
	public void getTeacherSortedByName() {
		teacherSortedByName(tea);
	}
	
	/*
	 * function to sort teacher by their dept
	 * @param - Collctions of Teacher objects
	 */
	public void teacherSortedByDept(List<Teacher> tea) {
		
		System.out.println("***** Sort teacher by dept *****");
		
		Collections.sort(tea, new Comparator<Teacher>() {
			
			@Override
			public int compare(Teacher t1, Teacher t2) {
				return t1.getDept().compareTo(t2.getDept());
			}
		});
		
		normalTeacherDisplay(tea);
	}
	
	public void getTeacherSortedByDept() {
		teacherSortedByDept(tea);
	}
	
	/*
	 * function to count teacher number by sex
	 * @param - Collections of Student objects
	 */
	public String countTeacherBySex(List<Teacher> tea) {
		
		int male = (int) tea.stream()
				.filter(t -> t.getSex().equals("male"))
				.count();
		
		int female = (int) tea.stream()
				.filter(t -> t.getSex().equals("female"))
				.count();
		
		System.out.println("Teacher - Male: " + male + ". Female: " + female + ".");
		return "Male: " + male + ". Female: " + female + ".";
	}
	
	public void getCountTeacherBySex() {
		countTeacherBySex(tea);
	}
	
	/*
	 * function to sort teacher by their class id
	 * @param - Collctions of Student objects
	 */
	public void teacherSortedClassId(List<Teacher> tea) {
		
		System.out.println("***** Sort teacher by class id *****");
		
		Collections.sort(tea, new Comparator<Teacher>() {
			
			@Override
			public int compare(Teacher t1, Teacher t2) {
				return Integer.compare(t1.getClassId(), t2.getClassId());
			}
		});
		
		normalTeacherDisplay(tea);
	}
	
	public void getTeacherSortedClassId() {
		teacherSortedClassId(tea);
	}
	
	
	/******************* Unit Test *********************/
	public static void main(String[] args) {
		// String name, String sn, String sex, String dept, Integer calssId, String address
		Student s1 = new Student("Alex", "B", "male", "Bio", 1, "34659 Chi Street");
		Student s2 = new Student("Jason", "K", "male", "Cs", 3, "898 Newsom Ave");
		Student s3 = new Student("Crystal", "L", "female", "Math", 2, "814 Luci Road");
		
		List<Student> stu = Student.getStuList();
		
		List<String> ret = new ArrayList<>();
		Statistics s = new Statistics();
		
		System.out.println("normal display");
		s.normalStudentDisplay(stu);
		
		System.out.println("Student display");
		ret = s.studentDisplay(stu);
		for(String str : ret) {
			System.out.println(str);
		}
		
		s.countStudentBySex(stu);
		s.studentSortedByDept(stu);
		s.studentSortedByName(stu);
		s.studentSortedClassId(stu);
		
		System.out.println("++++++++++++++++++++++++");
		// String name, String sn, String sex, String dept, Integer calssId, String address
		Teacher t1 = new Teacher("Bob", "B", "male", "Bio", 1, "34659 Chi Street");
		Teacher t2 = new Teacher("Lili", "K", "female", "Cs", 3, "898 Newsom Ave");
		Teacher t3 = new Teacher("Iris", "L", "female", "Math", 2, "814 Luci Road");
		
		List<Teacher> tea = Teacher.getTeaList();
		
		List<String> ret1 = new ArrayList<>();
		Statistics sta1 = new Statistics();
		
		System.out.println("normal display");
		s.normalTeacherDisplay(tea);
		
		System.out.println("Teacher display");
		ret1 = sta1.teacherDisplay(tea);
		for(String str : ret1) {
			System.out.println(str);
		}
		
		sta1.countTeacherBySex(tea);
		sta1.teacherSortedByDept(tea);
		sta1.teacherSortedByName(tea);
		sta1.teacherSortedClassId(tea);
	}
}

/******** output **********
 * 
 * *** Strat print info of Student Collection ***
*** Done ***
name: Alex, sex: male, sn: B. address: 34659 Chi Street, dept: Bio, class id: 1.
name: Jason, sex: male, sn: K. address: 898 Newsom Ave, dept: Cs, class id: 3.
name: Crystal, sex: female, sn: L. address: 814 Luci Road, dept: Math, class id: 2.
Male: 2. Female: 1.
***** Sort student by dept *****
Alex,B,male,Bio,1,34659 Chi Street
Jason,K,male,Cs,3,898 Newsom Ave
Crystal,L,female,Math,2,814 Luci Road
***** Sort student by name *****
Alex,B,male,Bio,1,34659 Chi Street
Crystal,L,female,Math,2,814 Luci Road
Jason,K,male,Cs,3,898 Newsom Ave
***** Sort student by class id *****
Alex,B,male,Bio,1,34659 Chi Street
Crystal,L,female,Math,2,814 Luci Road
Jason,K,male,Cs,3,898 Newsom Ave
++++++++++++++++++++++++
*** Start print info of Teacher Collection ***
*** Done ***
name: Bob, sex: male, sn: B. address: 34659 Chi Street, dept: Bio, class id: 1.
name: Lili, sex: female, sn: K. address: 898 Newsom Ave, dept: Cs, class id: 3.
name: Iris, sex: female, sn: L. address: 814 Luci Road, dept: Math, class id: 2.
Teacher - Male: 1. Female: 2.
***** Sort teacher by name *****
Bob,B,male,Bio,1,34659 Chi Street
Lili,K,female,Cs,3,898 Newsom Ave
Iris,L,female,Math,2,814 Luci Road
***** Sort teacher by name *****
Bob,B,male,Bio,1,34659 Chi Street
Iris,L,female,Math,2,814 Luci Road
Lili,K,female,Cs,3,898 Newsom Ave
***** Sort teacher by class id *****
Bob,B,male,Bio,1,34659 Chi Street
Iris,L,female,Math,2,814 Luci Road
Lili,K,female,Cs,3,898 Newsom Ave
*****************************/
