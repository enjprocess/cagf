package com.shengsiyuan.service;

import com.shengsiyuan.model.*;

import java.util.*;

public interface StudentService {

   void saveStudent(Student bean);
   
   void deleteStudent(Long id);
   
   void updateStudent(Student bean);
   
   Student getStudent(Long id);
   
   long getStudentCount();
   
   List<Student> listStudent(int start, int number);
   
   List<Student> listStudentDesc(int start, int number);
   
   List<Student> listStudentAsc(int start, int number);
	
	
	
}