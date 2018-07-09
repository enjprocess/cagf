package com.shengsiyuan.service.impl;

import com.shengsiyuan.model.*;
import com.shengsiyuan.dao.*;
import com.shengsiyuan.service.*;

import java.util.*;

public class PersonServiceImpl implements PersonService{

   private PersonDao personDao;
   
   private String LIST_ALL_DESC = "from Person bean order by bean.id desc";
   
   private String LIST_ALL_ASC = "from Person bean order by bean.id asc";
   
   public PersonDao getPerson() {
       return personDao;
   }
   
   public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
   }   

   public void savePerson(Person bean) {
       
       personDao.savePerson(bean);
   }
   public void deletePerson(Long id) {
       personDao.removePerson(id);
   }
   
   public void updatePerson(Person bean) {
       personDao.updatePerson(bean);
   }
   
   public Person getPerson(Long id) {
       return personDao.getPerson(id);
   }
   
   public long getPersonCount() {
       return personDao.getPersonCount(LIST_ALL_DESC);
   }
   
   public List<Person> listPerson(int start, int number) {
       return this.listPersonDesc(start, number);
   }
   
   public List<Person> listPersonDesc(int start, int number) {
       return personDao.searchPersons(LIST_ALL_DESC, new String[]{}, start, number);
   }
   
   public List<Person> listPersonAsc(int start, int number) {
       return personDao.searchPersons(LIST_ALL_ASC, new String[]{}, start, number);
   }
   
   
	
	
	
}