package com.patientlogger;

public class Patient 
{
	String myTHCNumber, myDate, myFirstName, myMiddleName, myLastName, myDob, myGender, myPhone,
		   myEmail, myStreetAddress, myCity, myState, myZip, myCountry, myPhoto, mySsid, myInsurance,
		   myOccupation, myWorkStatus, myEducationalDegree, myTOnset, myTEtio, myHOnset, myHEtio, myComments;
	
	public Patient()
	{
		
	}
	
	public Patient(String THCNumber, String date, String firstName, String middleName, String lastName, String dob,
				   String gender, String phone, String email, String streetAddress, String city, String state,
				   String zip, String country, String photo, String ssid, String insurance, String occupation,
				   String workStatus, String educationalDegree, String TOnset, String TEtio, String HOnset,
				   String HEtio, String comments)
	{
		this.myTHCNumber = THCNumber;
		this.myDate = date;
		this.myFirstName = firstName;
		this.myMiddleName = middleName;
		this.myLastName = lastName;
		this.myDob = dob;
		this.myGender = gender;
		this.myPhone = phone;
		this.myEmail = email;
		this.myStreetAddress = streetAddress;
		this.myCity = city;
		this.myState = state;
		this.myZip = zip;
		this.myCountry = country;
		this.myPhoto = photo;
		this.mySsid = ssid;
		this.myInsurance = insurance;
		this.myOccupation = occupation;
		this.myWorkStatus = workStatus;
		this.myEducationalDegree = educationalDegree;
		this.myTOnset = TOnset;
		this.myTEtio = TEtio;
		this.myHOnset = HOnset;
		this.myHEtio = HEtio;
		this.myComments = comments;
	}
	
	public String getTHCNumber()
	{
		return myTHCNumber;
	}
	
	public String getDate()
	{
		return myDate;
	}
	
	public String getFirstName()
	{
		return myFirstName;
	}
	
	public String getMiddleName()
	{
		return myMiddleName;
	}
	
	public String getLastName()
	{
		return myLastName;
	}
	
	public String getDob()
	{
		return myDob;
	}
	
	public String getGender()
	{
		return myGender;
	}
	
	public String getPhone()
	{
		return myPhone;
	}
	
	public String getEmail()
	{
		return myEmail;
	}
	
	public String getStreetAddress()
	{
		return myStreetAddress;
	}
	
	public String getCity()
	{
		return myCity;
	}
	
	public String getState()
	{
		return myState;
	}
	
	public String getZip()
	{
		return myZip;
	}
	
	public String getCountry()
	{
		return myCountry;
	}
	
	public String getPhoto()
	{
		return myPhoto;
	}
	
	public String getSsid()
	{
		return mySsid;
	}
	
	public String getInsurance()
	{
		return myInsurance;
	}
	
	public String getOccupation()
	{
		return myOccupation;
	}
	
	public String getWorkStatus()
	{
		return myWorkStatus;
	}
	
	public String getEducationalDegree()
	{
		return myEducationalDegree;
	}
	
	public String getTOnset()
	{
		return myTOnset;
	}
	
	public String getTEtio()
	{
		return myTEtio;
	}
	
	public String getHOnset()
	{
		return myHOnset;
	}
	
	public String getHEtio()
	{
		return myHEtio;
	}
	
	public String getComments()
	{
		return myComments;
	}
	
}



















