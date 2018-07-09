package com.shengsiyuan.action;

import com.shengsiyuan.model.*;
import com.shengsiyuan.service.*;

import java.util.*;
import com.opensymphony.xwork2.ActionSupport;

public class UpdatePStudent extends ActionSupport{

   private long id;

   private Student student;
   
   private StudentService studentService;
   
   public void setStudentService(StudentService studentService) {
       this.studentService = studentService;
   }
   
   public StudentService getStudentService() {
       return studentService;
   }
   
   public void setStudent(Student student) {
       this.student = student;
   }
   
   public Student getStudent() {
       return this.student;
   }
   
   public void setId(long id) {
       this.id = id;
   }
   
   public long getId() {
       return this.id;
   }
   
   public String execute() {
       this.student = studentService.getStudent(id);
       return "success";
   }
}