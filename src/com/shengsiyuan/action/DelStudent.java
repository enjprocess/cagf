package com.shengsiyuan.action;

import com.shengsiyuan.model.*;
import com.shengsiyuan.service.*;

import java.util.*;
import com.opensymphony.xwork2.ActionSupport;

public class DelStudent extends ActionSupport{

   private long id;

   private StudentService studentService;
   
   public void setStudentService(StudentService studentService) {
       this.studentService = studentService;
   }
   
   public StudentService getStudentService() {
       return studentService;
   }
   
   public void setId(long id) {
       this.id = id;
   }
   
   public long getId() {
       return this.id;
   }
   
   public String execute() {
       studentService.deleteStudent(id);
       return "success";
   }
}