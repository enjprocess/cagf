package com.shengsiyuan.action;

import com.shengsiyuan.model.*;
import com.shengsiyuan.service.*;

import java.util.*;
import com.opensymphony.xwork2.ActionSupport;

public class UpdatePPerson extends ActionSupport{

   private long id;

   private Person person;
   
   private PersonService personService;
   
   public void setPersonService(PersonService personService) {
       this.personService = personService;
   }
   
   public PersonService getPersonService() {
       return personService;
   }
   
   public void setPerson(Person person) {
       this.person = person;
   }
   
   public Person getPerson() {
       return this.person;
   }
   
   public void setId(long id) {
       this.id = id;
   }
   
   public long getId() {
       return this.id;
   }
   
   public String execute() {
       this.person = personService.getPerson(id);
       return "success";
   }
}