package model;

import utils.MyUtils;

public class Student {
	private String studentID, fullName, dob;
	private boolean gender;
	private String address;
	
	public Student(String studentID) {
		this.studentID = studentID;
	}

	public Student(String studentID, String fullName, String dob, boolean gender, String address) {
		this.studentID = studentID;
		this.fullName = fullName;
		this.dob = dob;
		this.gender = gender;
		this.address = address;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDob() {
		return MyUtils.convertToJavaDate(dob);
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
