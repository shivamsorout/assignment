package com.shivam;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomerData 
{
	@Id
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String jobTitle;
	
	public CustomerData() {}
	public CustomerData(int userId, String firstName, String lastName, String email, String jobTitle) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.jobTitle = jobTitle;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	@Override
	public String toString() {
		return "CustomerData [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", jobTitle=" + jobTitle + "]";
	}
}
