package com.shengsiyuan.service;

import com.shengsiyuan.model.*;

import java.util.*;

public interface PersonService {

   void savePerson(Person bean);
   
   void deletePerson(Long id);
   
   void updatePerson(Person bean);
   
   Person getPerson(Long id);
   
   long getPersonCount();
   
   List<Person> listPerson(int start, int number);
   
   List<Person> listPersonDesc(int start, int number);
   
   List<Person> listPersonAsc(int start, int number);
	
	
	
}