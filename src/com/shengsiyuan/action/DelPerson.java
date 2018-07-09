package com.shengsiyuan.action;

import com.shengsiyuan.model.*;
import com.shengsiyuan.service.*;

import java.util.*;
import com.opensymphony.xwork2.ActionSupport;

public class DelPerson extends ActionSupport{

   private long id;

   private PersonService personService;
   
   public void setPersonService(PersonService personService) {
       this.personService = personService;
   }
   
   public PersonService getPersonService() {
       return personService;
   }
   
   public void setId(long id) {
       this.id = id;
   }
   
   public long getId() {
       return this.id;
   }
   
   public String execute() {
       personService.deletePerson(id);
       return "success";
   }
}