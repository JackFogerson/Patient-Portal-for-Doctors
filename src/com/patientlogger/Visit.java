package com.patientlogger;

/**
 * @title	Visit Class
 * @author	Nick Fulton, Jack Fogerson
 * @desc	This is the class for visits
 */
public class Visit 
{
	// All of the variables needed for visit.
	String myVisitID, myDate, myTHCNumber, myVisitSequence, myProblemRank, myCategory,
		   myProtocol, myFU, myInstrument, myREM, myComments, myNextVisit, myNextVisitMonth,
		   myNextVisitDay, myNextVisitYear;
	
	/**
	 * @title	Visit Constructor
	 * @desc	Data starts null. This is a blank constructor 
	 * 			to build an empty visit to start off with.
	 */
	public Visit()
	{
		this.myVisitID = null;
		this.myDate = null;
		this.myTHCNumber = null;
		this.myVisitSequence = null;
		this.myProblemRank = null;
		this.myCategory = null;
		this.myFU = null;
		this.myProtocol = null;
		this.myInstrument = null;
		this.myREM = null;
		this.myComments = null;
		this.myNextVisit = null;
		this.myNextVisitMonth = null;
		this.myNextVisitDay = null;
		this.myNextVisitYear = null;	
	}

	/**
	 * @title	Patient Constructor
	 * @desc	Sets all of the data to what is provided.
	 */
	public Visit(String visitID, String date, String THCNumber, String visitSequence,
				 String problemRank, String category, String FU, String protocol, String instrument,
				 String REM, String comments, String nextVisit, String nextVisitMonth, String nextVisitDay,
				 String nextVisitYear)
	{
		this.myVisitID = visitID;
		this.myDate = date;
		this.myTHCNumber = THCNumber;
		this.myVisitSequence = visitSequence;
		this.myProblemRank = problemRank;
		this.myCategory = category;
		this.myFU = FU;
		this.myProtocol = protocol;
		this.myInstrument = instrument;
		this.myREM = REM;
		this.myComments = comments;
		this.myNextVisit = nextVisit;
		this.myNextVisitMonth = nextVisitMonth;
		this.myNextVisitDay = nextVisitDay;
		this.myNextVisitYear = nextVisitYear;
	}
	
	/**
	 * @title	getVisitID
	 * @return	myVisitID
	 */
	public String getVisitID()
	{
		return myVisitID;
	}
	
	public void setVisitID(String v)
	{
		this.myVisitID = v;
	}
	
	public String getDate()
	{
		return myDate;
	}
	
	public void setDate(String d)
	{
		this.myDate = d;
	}
	
	public String getTHCNumber()
	{
		return myTHCNumber;
	}
	
	public void setTHCNumber(String t)
	{
		this.myTHCNumber = t;
	}
	
	public String getVisitSequence()
	{
		return myVisitSequence;
	}
	
	public void setVisitSequence(String v)
	{
		myVisitSequence = v;
	}
	
	public String getProblemRank()
	{
		return myProblemRank;
	}
	
	public void setProblemRank(String p)
	{
		myProblemRank = p;
	}
	
	public String getCategory()
	{
		return myCategory;
	}
	
	public void getNextVisitMonth()
	{
		//TODO
		return;
	}
	public void getNextVisitDay()
	{
		//TODO
		return;
	}
	public void getNextVisitYear()
	{
		//TODO
		return;
	}
	
	
	
	
	public void setCategory(String c)
	{
		myCategory = c;
	}
	
	public String getFU()
	{
		return myFU;
	}
	
	public void setFU(String f)
	{
		myFU = f;
	}
	
	public String getProtocol()
	{
		return myProtocol;
	}
	
	public void setProtocol(String p)
	{
		myProtocol = p;
	}
	
	public String getInstrument()
	{
		return myInstrument;
	}
	
	public void setInstrument(String i)
	{
		myInstrument = i;
	}
	
	public boolean getREM()
	{
		boolean r = false;
		
		switch(myREM)
		{
			case "1":
				r = true;
				break;
			case "0":
				r = false;
				break;
		}
		return r;
	}
	
	public void setREM(String r)
	{
		myREM = r;
	}
	
	public void setREM(boolean b)
	{
		if(b)
			myREM = "1";
		else
			myREM = "0";
	}
	
	public String getComments()
	{
		return myComments;
	}
	
	public void setComments(String c)
	{
		myComments = c;
	}
	
	public String getNextVisit()
	{
		return myNextVisit;
	}
	
	public void setNextVisit(String n)
	{
		myNextVisit = n;
	}
	
	public String[] getVisitInfo()
	{
		String[] visitInfo = {myVisitID, myDate, myTHCNumber, myVisitSequence,
							  myProblemRank, myCategory, myProtocol, myInstrument,
							  myREM, myFU, myComments};
		return visitInfo;
	}
}
