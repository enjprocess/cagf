package com.shengsiyuan.dao;

import com.shengsiyuan.model.*;

import java.util.*;

public interface StudentDao {

    void saveStudent(Student bean);
	
	void saveStudents(List<Student> beans);
	
	void removeStudent(Long id);
	
	void removeStudent(Student bean);
	
	void removeStudents(List<Long> ids);
	
	void updateStudent(Student bean);
	
	void updateStudents(List<Student> beans);
	
	Student getStudent(Long id);
	
	long getStudentCount(String queryString);
	
	long getStudentCount(String queryString, String value);
	
	long getStudentCount(String queryString, String[] value);
	
	List<Student> searchStudents(String queryString);
	
	List<Student> searchStudents(String queryString, String value);
	
	List<Student> searchStudents(String queryString, String[] value);
	
	Student searchStudent(String queryString);
	
	Student searchStudent(String queryString, String value);
	
	Student searchStudent(String queryString, String[] value);
	
	List<Student> searchStudents(String queryString, String value, int start, int number);
	
	List<Student> searchStudents(String queryString, String[] value, int start, int number);
	
	
	
}