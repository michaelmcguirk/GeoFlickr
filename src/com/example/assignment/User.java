package com.example.assignment;

import java.io.Serializable;

public class User implements Serializable {
	private String name, gender, jobTitle, emailAddr;
	private int age;
	private double budget;
	
	public User(String name, String gender, String jobTitle, String emailAddr, int age, double budget)
	{
		this.name = name;
		this.gender = gender;
		this.jobTitle = jobTitle;
		this.emailAddr = emailAddr;
		this.age = age;
		this.budget = budget;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getEmailAddr() {
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}
	
	
}
