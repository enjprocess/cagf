package com.shengsiyuan.service.impl;

import com.shengsiyuan.model.*;
import com.shengsiyuan.dao.*;
import com.shengsiyuan.service.*;

import java.util.*;

public class StudentServiceImpl implements StudentService{

   private StudentDao studentDao;
   
   private String LIST_ALL_DESC = "from Student bean order by bean.id desc";
   
   private String LIST_ALL_ASC = "from Student bean order by bean.id asc";
   
   public StudentDao getStudent() {
       return studentDao;
   }
   
   public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
   }   

   public void saveStudent(Student bean) {
       
       studentDao.saveStudent(bean);
   }
   public void deleteStudent(Long id) {
       studentDao.removeStudent(id);
   }
   
   public void updateStudent(Student bean) {
       studentDao.updateStudent(bean);
   }
   
   public Student getStudent(Long id) {
       return studentDao.getStudent(id);
   }
   
   public long getStudentCount() {
       return studentDao.getStudentCount(LIST_ALL_DESC);
   }
   
   public List<Student> listStudent(int start, int number) {
       return this.listStudentDesc(start, number);
   }
   
   public List<Student> listStudentDesc(int start, int number) {
       return studentDao.searchStudents(LIST_ALL_DESC, new String[]{}, start, number);
   }
   
   public List<Student> listStudentAsc(int start, int number) {
       return studentDao.searchStudents(LIST_ALL_ASC, new String[]{}, start, number);
   }
   
   
	
	
	
}