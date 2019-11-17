package com.patientlogger;

import java.time.LocalDate;

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
	
	public void setTHCNumber(String t)
	{
		myTHCNumber = t;
	}
	
	public String getDate()
	{
		return myDate;
	}
	
	public void setDate(String t)
	{
		myDate = t;
	}
	
	public String getFirstName()
	{
		return myFirstName;
	}
	
	public void setFirstName(String t)
	{
		myFirstName = t;
	}
	
	public String getMiddleName()
	{
		return myMiddleName;
	}
	
	public void setMiddleName(String t)
	{
		myMiddleName = t;
	}
	
	public String getLastName()
	{
		return myLastName;
	}
	
	public void setLastName(String t)
	{
		myLastName = t;
	}
	
	public String getDob()
	{
		return myDob;
	}
	
	public void setDob(String t)
	{
		myDob = t;
	}
	
	public String getGender()
	{
		return myGender;
	}
	
	public void setGender(String t)
	{
		myGender = t;
	}
	
	public String getPhone()
	{
		return myPhone;
	}
	
	public void setPhone(String t)
	{
		myPhone = t;
	}
	
	public String getEmail()
	{
		return myEmail;
	}
	
	public void setEmail(String t)
	{
		myEmail = t;
	}
	
	public String getStreetAddress()
	{
		return myStreetAddress;
	}
	
	public void setStreetAddress(String t)
	{
		myStreetAddress = t;
	}
	
	public String getCity()
	{
		return myCity;
	}
	
	public void setCity(String t)
	{
		myCity = t;
	}
	
	public String getState()
	{
		return myState;
	}
	
	public void setState(String t)
	{
		myState = t;
	}
	
	public String getZip()
	{
		return myZip;
	}
	
	public void setZip(String t)
	{
		myZip = t;
	}
	
	public String getCountry()
	{
		return myCountry;
	}
	
	public void setCountry(String t)
	{
		myCountry = t;
	}
	
	public String getPhoto()
	{
		return myPhoto;
	}
	
	public void setPhoto(String t)
	{
		myPhoto = t;
	}
	
	public String getSsid()
	{
		return mySsid;
	}
	
	public void setSsid(String t)
	{
		mySsid = t;
	}
	
	public String getInsurance()
	{
		return myInsurance;
	}
	
	public void setInsurance(String t)
	{
		myInsurance = t;
	}
	
	public String getOccupation()
	{
		return myOccupation;
	}
	
	public void setOccupation(String t)
	{
		myOccupation = t;
	}
	
	public String getWorkStatus()
	{
		return myWorkStatus;
	}
	
	public void setWorkStatus(String t)
	{
		myWorkStatus = t;
	}
	
	public String getEducationalDegree()
	{
		return myEducationalDegree;
	}
	
	public void setEducationalDegree(String t)
	{
		myEducationalDegree = t;
	}
	
	public String getTOnset()
	{
		return myTOnset;
	}
	
	public void setTOnset(String t)
	{
		myTOnset = t;
	}
	
	public String getTEtio()
	{
		return myTEtio;
	}
	
	public void setTEtio(String t)
	{
		myTEtio = t;
	}
	
	public String getHOnset()
	{
		return myHOnset;
	}
	
	public void setHOnset(String t)
	{
		myHOnset = t;
	}
	
	public String getHEtio()
	{
		return myHEtio;
	}
	
	public void setHEtio(String t)
	{
		myHEtio = t;
	}
	
	public String getComments()
	{
		return myComments;
	}
	
	public void setComments(String t)
	{
		myComments = t;
	}
	
	public String getAge()
	{
		int month, day, year, age;
		
		month = Integer.parseInt(myDob.substring(5,7));
		day = Integer.parseInt(myDob.substring(8,10));
		year = Integer.parseInt(myDob.substring(0,4));
		
		LocalDate today = LocalDate.now();
		
		age = today.getYear() - year;
		
		if(today.getMonthValue() >= month)
		{
			if(today.getMonthValue() == month && today.getDayOfMonth() >= day)
			{
				age++;
			}
		}
		
		return "" + age;
	}
	
	public String convertDate()
	{
		int month, day, year;

		month = Integer.parseInt(myDate.substring(5,7));
		day = Integer.parseInt(myDate.substring(8,10));
		year = Integer.parseInt(myDate.substring(0,4));
		
		return month + "-" + day + "-" + year;
	}
	
	public String[] getPatientInfo()
	{
		String[] results = {myTHCNumber, myFirstName + " " + myLastName, getAge(), myGender,
							myCity, myState, convertDate()};
		return results;
	}
}



















