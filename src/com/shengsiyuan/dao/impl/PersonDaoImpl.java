package com.shengsiyuan.dao.impl;

import com.shengsiyuan.model.*;
import com.shengsiyuan.dao.*;
import java.util.*;
import com.cagf.tool.util.*;


public class PersonDaoImpl extends BaseDao<Person> implements PersonDao {
	
	public void savePerson(Person bean) {
		storeObj(bean);
	}
	
	public void savePersons(List<Person> beans) {
		storeObjs(beans);
	}

	public void removePerson(Long id) {
		removeObj(Person.class, id);
	}

	public void removePerson(Person bean) {
		this.removePerson(bean.getId());
	}

	public void removePersons(List<Long> ids) {
		removeObjs(Person.class, ids);
	}

	public void updatePerson(Person bean) {
		updateObj(bean);
	}

	public void updatePersons(List<Person> beans) {
		updateObjs(beans);
	}

	public Person getPerson(Long id) {
		return retrieveObj(Person.class, id);
	}

	public long getPersonCount(String queryString) {
		return retrieveObjsCount(queryString);
	}

	public long getPersonCount(String queryString, String value) {
		return retrieveObjsCount(queryString, value);
	}

	public long getPersonCount(String queryString, String[] value) {
		return retrieveObjsCount(queryString, value);
	}

	public List<Person> searchPersons(String queryString) {
		return retrieveObjs(queryString);
	}

	public List<Person> searchPersons(String queryString, String value) {
		return retrieveObjs(queryString, value);
	}

	public List<Person> searchPersons(String queryString, String[] value) {
		return retrieveObjs(queryString, value);
	}

	public Person searchPerson(String queryString) {
		return retrieveObj(queryString);
	}

	public Person searchPerson(String queryString, String value) {
		return retrieveObj(queryString, value);
	}

	public Person searchPerson(String queryString, String[] value) {
		return retrieveObj(queryString, value);
	}

	public List<Person> searchPersons(String queryString, String value, int start, int number) {
		return retrieveObjs(queryString, value, start, number);
	}

	public List<Person> searchPersons(String queryString, String[] value, int start, int number) {
		return retrieveObjs(queryString, value, start, number);
	}
    
	
	
}