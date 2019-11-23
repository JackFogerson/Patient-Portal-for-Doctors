package com.patientlogger;

import java.time.LocalDate;

/**
 * @title	Patient Class
 * @author	Nick Fulton
 *
 */
public class Patient 
{
	// Declare all of the variables.
	String myTHCNumber, myDate, myFirstName, myMiddleName, myLastName, myDob, myGender, myPhone,
		   myEmail, myStreetAddress, myCity, myState, myZip, myCountry, myPhoto, mySsid, myInsurance,
		   myOccupation, myWorkStatus, myEducationalDegree, myTOnset, myTEtio, myHOnset, myHEtio, myComments;
	
	/**
	 * @title	Patient Constructor
	 * @desc	Nulls all of the data. This is a blank constructor to build an empty patient to start off with.
	 */
	public Patient()
	{
		this.myTHCNumber = null;
		this.myDate = null;
		this.myFirstName = null;
		this.myMiddleName = null;
		this.myLastName = null;
		this.myDob = null;
		this.myGender = null;
		this.myPhone = null;
		this.myEmail = null;
		this.myStreetAddress = null;
		this.myCity = null;
		this.myState = null;
		this.myZip = null;
		this.myCountry = null;
		this.myPhoto = null;
		this.mySsid = null;
		this.myInsurance = null;
		this.myOccupation = null;
		this.myWorkStatus = null;
		this.myEducationalDegree = null;
		this.myTOnset = null;
		this.myTEtio = null;
		this.myHOnset = null;
		this.myHEtio = null;
		this.myComments = null;
	}
	
	/**
	 * @title	Patient Constructor
	 * @param	THCNumber - THC Number of patient
	 * @param	date - Date of patient creation
	 * @param	firstName - First name of patient.
	 * @param	middleName - Middle name of patient.
	 * @param	lastName - Last name of patient.
	 * @param	dob - Date of birth of the patient.
	 * @param	gender - Gender of patient.
	 * @param	phone - Phone number of patient.
	 * @param	email - Email address of patient.
	 * @param	streetAddress - Street address of patient.
	 * @param	city - City of patient.
	 * @param	state - State of patient.
	 * @param	zip - Zip code of patient.
	 * @param	country - Country of patient.
	 * @param	photo - Photo address of patient.
	 * @param	ssid - SSN of patient.
	 * @param	insurance - Insurance of patient.
	 * @param	occupation - Patient occupation.
	 * @param	workStatus - Work Status of patient.
	 * @param	educationalDegree - Educational degree  of patient.
	 * @param 	TOnset - TOnset of patient.
	 * @param 	TEtio - TEtio of patient.
	 * @param 	HOnset - HOnset of patient.
	 * @param 	HEtio - HEtio of patient.
	 * @param 	comments - Patient comments.
	 * @desc	Sets all of the data to what is provided.
	 */
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
	
	/**
	 * @title	getTHCNumber method
	 * @return	myTHCNumber
	 */
	public String getTHCNumber()
	{
		return myTHCNumber;
	}
	
	/**
	 * @title setTHCNumber method
	 * @param t - THCNumber
	 */
	public void setTHCNumber(String t)
	{
		myTHCNumber = t;
	}
	
	/**
	 * @title	getDate method
	 * @return	myDate
	 */
	public String getDate()
	{
		return myDate;
	}

	/**
	 * @title setDate method
	 * @param t - Date
	 */
	public void setDate(String t)
	{
		myDate = t;
	}
	
	/**
	 * @title	getFirstName method
	 * @return	myFirstName
	 */
	public String getFirstName()
	{
		return myFirstName;
	}

	/**
	 * @title setFirstName method
	 * @param t - FirstName
	 */
	public void setFirstName(String t)
	{
		myFirstName = t;
	}
	
	/**
	 * @title	getMiddleName method
	 * @return	myMiddleName
	 */
	public String getMiddleName()
	{
		return myMiddleName;
	}
	

	/**
	 * @title setMiddle method
	 * @param t - THCNumber
	 */
	public void setMiddleName(String t)
	{
		myMiddleName = t;
	}
	
	/**
	 * @title	getLastName method
	 * @return	myLastName
	 */
	public String getLastName()
	{
		return myLastName;
	}

	/**
	 * @title setLastName method
	 * @param t - myLastName
	 */
	public void setLastName(String t)
	{
		myLastName = t;
	}
	
	/**
	 * @title	getDOB method
	 * @return	myDob
	 */
	public String getDob()
	{
		return myDob;
	}

	/**
	 * @title setDob method
	 * @param t - myDob
	 */
	public void setDob(String t)
	{
		myDob = t;
	}
	
	/**
	 * @title	getGender method
	 * @return	myGender
	 */
	public String getGender()
	{
		return myGender;
	}

	/**
	 * @title setGender method
	 * @param t - myGender
	 */
	public void setGender(String t)
	{
		myGender = t;
	}
	
	/**
	 * @title	getPhone method
	 * @return	myPhone
	 */
	public String getPhone()
	{
		return myPhone;
	}
	

	/**
	 * @title setPhone method
	 * @param t - myPhone
	 */
	public void setPhone(String t)
	{
		myPhone = t;
	}
	
	/**
	 * @title	getEmail method
	 * @return	myEmail
	 */
	public String getEmail()
	{
		return myEmail;
	}

	/**
	 * @title setEmail method
	 * @param t - myEmail
	 */
	public void setEmail(String t)
	{
		myEmail = t;
	}
	
	/**
	 * @title	getStreetAddress method
	 * @return	myStreetAddress
	 */
	public String getStreetAddress()
	{
		return myStreetAddress;
	}

	/**
	 * @title setStreetAddress method
	 * @param t - myStreetAddress
	 */
	public void setStreetAddress(String t)
	{
		myStreetAddress = t;
	}
	
	/**
	 * @title	getCity method
	 * @return	myCity
	 */
	public String getCity()
	{
		return myCity;
	}

	/**
	 * @title setCity method
	 * @param t - myCity
	 */
	public void setCity(String t)
	{
		myCity = t;
	}
	
	/**
	 * @title	getState method
	 * @return	myState
	 */
	public String getState()
	{
		return myState;
	}
	
	/**
	 * @title setState method
	 * @param t - myState
	 */
	public void setState(String t)
	{
		myState = t;
	}
	
	/**
	 * @title	getZip method
	 * @return	myZip
	 */
	public String getZip()
	{
		return myZip;
	}
	
	/**
	 * @title setZip method
	 * @param t - myZip
	 */
	public void setZip(String t)
	{
		myZip = t;
	}
	
	/**
	 * @title	getCountry method
	 * @return	myCountry
	 */
	public String getCountry()
	{
		return myCountry;
	}

	/**
	 * @title setCountry method
	 * @param t - myCountry
	 */
	public void setCountry(String t)
	{
		myCountry = t;
	}
	
	/**
	 * @title	getPhoto method
	 * @return	myPhoto
	 */
	public String getPhoto()
	{
		return myPhoto;
	}

	/**
	 * @title setPhoto method
	 * @param t - myPhoto
	 */
	public void setPhoto(String t)
	{
		myPhoto = t;
	}
	
	/**
	 * @title	getSsid method
	 * @return	mySsid
	 */
	public String getSsid()
	{
		return mySsid;
	}

	/**
	 * @title setSsid method
	 * @param t - mySsid
	 */
	public void setSsid(String t)
	{
		mySsid = t;
	}
	
	/**
	 * @title	getInsurance method
	 * @return	myInsurance
	 */
	public String getInsurance()
	{
		return myInsurance;
	}

	/**
	 * @title setInsurance method
	 * @param t - myInsurance
	 */
	public void setInsurance(String t)
	{
		myInsurance = t;
	}
	
	/**
	 * @title	getOccupation method
	 * @return	myOccupation
	 */
	public String getOccupation()
	{
		return myOccupation;
	}
	

	/**
	 * @title setOccupation method
	 * @param t - myOccupation
	 */
	public void setOccupation(String t)
	{
		myOccupation = t;
	}
	
	/**
	 * @title	getWorkStatus method
	 * @return	myWorkStatus
	 */
	public String getWorkStatus()
	{
		return myWorkStatus;
	}

	/**
	 * @title setWorkStatus method
	 * @param t - myWorkStatus
	 */
	public void setWorkStatus(String t)
	{
		myWorkStatus = t;
	}

	/**
	 * @title	getEducationalDegree method
	 * @return	myEducationalDegree
	 */
	public String getEducationalDegree()
	{
		return myEducationalDegree;
	}

	/**
	 * @title setEducationalDegree method
	 * @param t - myEducationalDegree
	 */
	public void setEducationalDegree(String t)
	{
		myEducationalDegree = t;
	}
	
	/**
	 * @title	getTOnset method
	 * @return	myTOnset
	 */
	public String getTOnset()
	{
		return myTOnset;
	}

	/**
	 * @title setTOnset method
	 * @param t - myTOnset
	 */
	public void setTOnset(String t)
	{
		myTOnset = t;
	}
	
	/**
	 * @title	getTEtio method
	 * @return	myTEtio
	 */
	public String getTEtio()
	{
		return myTEtio;
	}

	/**
	 * @title setTEtio method
	 * @param t - myTEtio
	 */
	public void setTEtio(String t)
	{
		myTEtio = t;
	}
	
	/**
	 * @title	getHOnset method
	 * @return	myHonset
	 */
	public String getHOnset()
	{
		return myHOnset;
	}

	/**
	 * @title setHOnset method
	 * @param t - myHOnset
	 */
	public void setHOnset(String t)
	{
		myHOnset = t;
	}
	
	/**
	 * @title	getHEtio method
	 * @return	myHEtio
	 */
	public String getHEtio()
	{
		return myHEtio;
	}

	/**
	 * @title setHEtio method
	 * @param t - myHEtio
	 */
	public void setHEtio(String t)
	{
		myHEtio = t;
	}
	
	/**
	 * @title	getComments method
	 * @return	myComments
	 */
	public String getComments()
	{
		return myComments;
	}

	/**
	 * @title setComments method
	 * @param t - myComments
	 */
	public void setComments(String t)
	{
		myComments = t;
	}
	
	/**
	 * @title	getAge Method
	 * @return	Computes how old the patient is.
	 */
	public String getAge()
	{
		// Declare the different variables.
		int month, day, year, age;
		
		// Pull the birthday of the patient.
		month = Integer.parseInt(myDob.substring(5,7));
		day = Integer.parseInt(myDob.substring(8,10));
		year = Integer.parseInt(myDob.substring(0,4));
		
		// Find today's date.
		LocalDate today = LocalDate.now();
		
		// Find the age in years.
		age = today.getYear() - year;
		
		// Modify the age if the patient had their birthday already this year.
		if(today.getMonthValue() >= month)
		{
			if(today.getMonthValue() == month && today.getDayOfMonth() >= day)
			{
				age++;
			}
		}
		
		// Return the age in a string.
		return "" + age;
	}
	
	/**
	 * @title	convertDate
	 * @return	convertedDate
	 * @desc	Converts the date from a form the database can read (yyyy-mm-dd) into the typical American way (mm-dd-yyyy).
	 */
	public String convertDate()
	{
		int month, day, year;

		month = Integer.parseInt(myDate.substring(5,7));
		day = Integer.parseInt(myDate.substring(8,10));
		year = Integer.parseInt(myDate.substring(0,4));
		
		return month + "-" + day + "-" + year;
	}
	
	/**
	 * @title	getPatientInfo method
	 * @return	Info for the patient
	 * @desc	Finds the information for the patient that is to be displayed in the table.
	 */
	public String[] getPatientInfo()
	{
		String[] results = {myTHCNumber, myFirstName + " " + myLastName, getAge(), myGender,
							myCity, myState, convertDate()};
		return results;
	}
}
