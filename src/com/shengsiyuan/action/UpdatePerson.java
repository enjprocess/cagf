package com.shengsiyuan.action;

import com.shengsiyuan.model.*;
import com.shengsiyuan.service.*;

import java.util.*;
import com.opensymphony.xwork2.ActionSupport;

public class UpdatePerson extends ActionSupport{
   
    private PersonService personService;
   
	private long id;
	private java.lang.String username;
	private java.lang.String address;
	private int age;

   
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
   
    public PersonService getPersonService() {
        return personService;
    }

    public String execute() {
        
        Person bean = personService.getPerson(id);
        
		bean.setId(id);
		bean.setUsername(username);
		bean.setAddress(address);
		bean.setAge(age);

   
        personService.updatePerson(bean);
        return "success";
   }
   

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public java.lang.String getUsername() {
		return username;
	}
	public void setUsername(java.lang.String username) {
		this.username = username;
	}
	public java.lang.String getAddress() {
		return address;
	}
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}