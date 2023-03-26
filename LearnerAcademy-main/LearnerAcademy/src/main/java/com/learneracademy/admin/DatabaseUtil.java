package com.learneracademy.admin;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.learneracademy.models.Student;
import com.learneracademy.models.Subject;
import com.learneracademy.models.Teacher;
import com.learneracademy.models.Class;

public class DatabaseUtil {
	private DataSource dataSource;

	public DatabaseUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Student> getStudents(){
		List<Student> students = new ArrayList<>();
		
		Connection con = null;
		Statement stmt = null;
		ResultSet res = null;
		
		try {
			con = dataSource.getConnection();
			
			String sql = "select *from students";
			stmt = con.createStatement();
			res = stmt.executeQuery(sql);
			
			while(res.next()) {
				int id = res.getInt("id");
				String firstName = res.getString("fname");
				String lastName = res.getString("lname");
				int age = res.getInt("age");
				int aclass = res.getInt("class");
				
				Student student = new Student(id,firstName,lastName,age,aclass);
				students.add(student);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(con,stmt,res);
		}
		
		return students;
	}
	
	public List<Teacher> getTeachers(){
		List<Teacher> teachers = new ArrayList<>();
		
		Connection con = null;
		Statement stmt = null;
		ResultSet res = null;
		
		try {
			con = dataSource.getConnection();
			
			String sql = "select *from teachers";
			stmt = con.createStatement();
			res = stmt.executeQuery(sql);
			
			while(res.next()) {
				int id = res.getInt("id");
				String firstName = res.getString("fname");
				String lastName = res.getString("lname");
				int age = res.getInt("age");
				
				Teacher teacher = new Teacher(id,firstName,lastName,age);
				teachers.add(teacher);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(con,stmt,res);
		}
		
		return teachers;
	}
	
	public List<Subject> getSubjects(){
		List<Subject> subjects = new ArrayList<>();
		
		Connection con = null;
		Statement stmt = null;
		ResultSet res = null;
		
		try {
			con = dataSource.getConnection();
			
			String sql = "select *from subjects";
			stmt = con.createStatement();
			res = stmt.executeQuery(sql);
			
			while(res.next()) {
				int id = res.getInt("id");
				String name = res.getString("name");
				String shortcut = res.getString("shortcut");
				
				Subject subject = new Subject(id,name,shortcut);
				subjects.add(subject);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(con,stmt,res);
		}
		return subjects;
	}
	
	public Subject loadSubject(int subjectId) {
		Subject theSubject = null;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet res = null;
		
		try {
			con = dataSource.getConnection();
			String sql = "select *from subjects where id = "+ subjectId;
			stmt = con.createStatement();
			res = stmt.executeQuery(sql);
			
			while(res.next()) {
				int id = res.getInt("id");
				String name = res.getString("name");
				String shortcut = res.getString("shortcut");
				
				theSubject = new Subject(id,name,shortcut);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(con,stmt,res);
		}
		return theSubject;

	}
	
	public List<Class> getClasses(){
		List<Class> classes  = new ArrayList<>();
		
		Connection con = null;
		Statement stmt = null;
		ResultSet res = null;
		
		try {
			con = dataSource.getConnection();
			
			String sql = "select *from classes";
			stmt = con.createStatement();
			res = stmt.executeQuery(sql);
			
			while(res.next()) {
				int id = res.getInt("id");
				int section = res.getInt("section");
				int subject = res.getInt("subject");
				int teacher = res.getInt("teacher");
				String time = res.getString("time");
				
				Teacher tempTeacher = loadTeacher(teacher);
				Subject tempSubject = loadSubject(subject);
				
				String teacher_name = tempTeacher.getFname()+" "+tempTeacher.getLname();
				
				Class tempClass = new Class(id,section,teacher_name,tempSubject.getName(),time);
				classes.add(tempClass);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(con,stmt,res);
		}
		
		return classes;
	}
	

	public Teacher loadTeacher(int teacherId) {
		Teacher theTeacher = null;
		
		Connection con = null;
		Statement stmt = null;
		ResultSet res = null;
		
		try {
			con = dataSource.getConnection();
			
			String sql = "select *from teachers where id = "+ teacherId ;
			
			stmt = con.createStatement();
			res = stmt.executeQuery(sql);
			
			while(res.next()) {
				int id = res.getInt("id");
				String fname = res.getString("fname");
				String lname = res.getString("lname");
				int age = res.getInt("age");
				theTeacher = new Teacher(id,fname,lname,age);
			
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(con,stmt,res);
		}
		return theTeacher;
	}
	
	public List<Student> loadClassStudents(int classId){
		List<Student> students = new ArrayList<>();
		
		Connection con = null;
		Statement stmt = null;
		ResultSet res = null;
		
		try {
			con = dataSource.getConnection();
			
			String sql = "select *from students where class = "+classId;
			stmt = con.createStatement();
			res = stmt.executeQuery(sql);
			
			while(res.next()) {
				int id = res.getInt("id");
				String firstName = res.getString("fname");
				String lastName = res.getString("lname");
				int age = res.getInt("age");
				int aclass = res.getInt("class");
				
				Student student = new Student(id,firstName,lastName,age,aclass);
				students.add(student);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(con,stmt,res);
		}
		
		return students;
		
	}
	
	public void assignNewClass(int section,int teacher,int subject,String time) {
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = this.dataSource.getConnection();
			String sql = "insert into classes (section,teacher,subject,time) values(?,?,?,?)";
			
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, section);
			stmt.setInt(2, teacher);
			stmt.setInt(3, subject);
			stmt.setString(4, time);
			
			stmt.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(con,stmt,null);
		}
		
	}
	
	private void close(Connection con, Statement stmt, ResultSet res) {
		try {
			if(res != null) {
				res.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			if(con != null) {
				con.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	

	
	
	
}
