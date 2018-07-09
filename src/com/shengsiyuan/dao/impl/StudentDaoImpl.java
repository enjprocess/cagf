package com.shengsiyuan.dao.impl;

import com.shengsiyuan.model.*;
import com.shengsiyuan.dao.*;
import java.util.*;
import com.cagf.tool.util.*;


public class StudentDaoImpl extends BaseDao<Student> implements StudentDao {
	
	public void saveStudent(Student bean) {
		storeObj(bean);
	}
	
	public void saveStudents(List<Student> beans) {
		storeObjs(beans);
	}

	public void removeStudent(Long id) {
		removeObj(Student.class, id);
	}

	public void removeStudent(Student bean) {
		this.removeStudent(bean.getId());
	}

	public void removeStudents(List<Long> ids) {
		removeObjs(Student.class, ids);
	}

	public void updateStudent(Student bean) {
		updateObj(bean);
	}

	public void updateStudents(List<Student> beans) {
		updateObjs(beans);
	}

	public Student getStudent(Long id) {
		return retrieveObj(Student.class, id);
	}

	public long getStudentCount(String queryString) {
		return retrieveObjsCount(queryString);
	}

	public long getStudentCount(String queryString, String value) {
		return retrieveObjsCount(queryString, value);
	}

	public long getStudentCount(String queryString, String[] value) {
		return retrieveObjsCount(queryString, value);
	}

	public List<Student> searchStudents(String queryString) {
		return retrieveObjs(queryString);
	}

	public List<Student> searchStudents(String queryString, String value) {
		return retrieveObjs(queryString, value);
	}

	public List<Student> searchStudents(String queryString, String[] value) {
		return retrieveObjs(queryString, value);
	}

	public Student searchStudent(String queryString) {
		return retrieveObj(queryString);
	}

	public Student searchStudent(String queryString, String value) {
		return retrieveObj(queryString, value);
	}

	public Student searchStudent(String queryString, String[] value) {
		return retrieveObj(queryString, value);
	}

	public List<Student> searchStudents(String queryString, String value, int start, int number) {
		return retrieveObjs(queryString, value, start, number);
	}

	public List<Student> searchStudents(String queryString, String[] value, int start, int number) {
		return retrieveObjs(queryString, value, start, number);
	}
    
	
	
}