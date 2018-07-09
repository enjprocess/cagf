package com.shengsiyuan.action;

import com.shengsiyuan.model.*;
import com.shengsiyuan.service.*;

import java.util.*;
import com.opensymphony.xwork2.ActionSupport;

public class ListPerson extends ActionSupport{
   
   private PersonService personService;
   
   private List<Person> personList;
   
   public void setPersonService(PersonService personService) {
       this.personService = personService;
   }
   
   public PersonService getPersonService() {
       return personService;
   }
   
   public void setPersonList(List<Person> personList) {
       this.personList = personList;
   }
   
   public List<Person> getPersonList() {
       return this.personList;
   }

   public String execute() {
        
       personList = personService.listPerson(0, 10);
       return "success";
   }
}