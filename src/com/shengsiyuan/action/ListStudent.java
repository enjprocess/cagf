package com.shengsiyuan.action;

import com.shengsiyuan.model.*;
import com.shengsiyuan.service.*;

import java.util.*;
import com.opensymphony.xwork2.ActionSupport;

public class ListStudent extends ActionSupport{
   
   private StudentService studentService;
   
   private List<Student> studentList;
   
   public void setStudentService(StudentService studentService) {
       this.studentService = studentService;
   }
   
   public StudentService getStudentService() {
       return studentService;
   }
   
   public void setStudentList(List<Student> studentList) {
       this.studentList = studentList;
   }
   
   public List<Student> getStudentList() {
       return this.studentList;
   }

   public String execute() {
        
       studentList = studentService.listStudent(0, 10);
       return "success";
   }
}