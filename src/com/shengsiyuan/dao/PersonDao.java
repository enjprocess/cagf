package com.shengsiyuan.dao;

import com.shengsiyuan.model.*;

import java.util.*;

public interface PersonDao {

    void savePerson(Person bean);
	
	void savePersons(List<Person> beans);
	
	void removePerson(Long id);
	
	void removePerson(Person bean);
	
	void removePersons(List<Long> ids);
	
	void updatePerson(Person bean);
	
	void updatePersons(List<Person> beans);
	
	Person getPerson(Long id);
	
	long getPersonCount(String queryString);
	
	long getPersonCount(String queryString, String value);
	
	long getPersonCount(String queryString, String[] value);
	
	List<Person> searchPersons(String queryString);
	
	List<Person> searchPersons(String queryString, String value);
	
	List<Person> searchPersons(String queryString, String[] value);
	
	Person searchPerson(String queryString);
	
	Person searchPerson(String queryString, String value);
	
	Person searchPerson(String queryString, String[] value);
	
	List<Person> searchPersons(String queryString, String value, int start, int number);
	
	List<Person> searchPersons(String queryString, String[] value, int start, int number);
	
	
	
}