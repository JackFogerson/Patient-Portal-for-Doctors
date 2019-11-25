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
	
	/**
	 * @title setVisitID
	 * @param t - myVisitID
	 */
	public void setVisitID(String v)
	{
		this.myVisitID = v;
	}
	
	/**
	 * @title	getDate
	 * @return	myDate
	 */
	public String getDate()
	{
		return myDate;
	}
	
	/**
	 * @title setDate
	 * @param t - myDate
	 */
	public void setDate(String d)
	{
		this.myDate = d;
	}
	
	/**
	 * @title	getTHCNumber
	 * @return	myTHCNumber
	 */
	public String getTHCNumber()
	{
		return myTHCNumber;
	}
	
	/**
	 * @title setTHCNumber
	 * @param t - myTHCNumber
	 */
	public void setTHCNumber(String t)
	{
		this.myTHCNumber = t;
	}
	
	/**
	 * @title	getVisitSequence
	 * @return	myVisitSequence
	 */
	public String getVisitSequence()
	{
		return myVisitSequence;
	}
	
	/**
	 * @title setVisitSequence
	 * @param v - myVisitSequence
	 */
	public void setVisitSequence(String v)
	{
		this.myVisitSequence = v;
	}
	
	/**
	 * @title	getProblemRank
	 * @return	myProblemRank
	 */
	public String getProblemRank()
	{
		return myProblemRank;
	}
	
	/**
	 * @title setProblemRank
	 * @param p - myProblemRank
	 */
	public void setProblemRank(String p)
	{
		this.myProblemRank = p;
	}
	
	/**
	 * @title	getCategory
	 * @return	myCategory
	 */
	public String getCategory()
	{
		return myCategory;
	}
	
	/**
	 * @title setCategory
	 * @param c - myCategory
	 */
	public void setCategory(String c)
	{
		this.myCategory = c;
	}
	
	/**
	 * @title	getFU
	 * @return	myFU
	 */
	public String getFU()
	{
		return myFU;
	}
	
	/**
	 * @title setFU
	 * @param f - myFU
	 */
	public void setFU(String f)
	{
		this.myFU = f;
	}
	
	/**
	 * @title	getProtocol
	 * @return	myProtocol
	 */
	public String getProtocol()
	{
		return myProtocol;
	}
	
	/**
	 * @title setProtocol
	 * @param p - myProtocol
	 */
	public void setProtocol(String p)
	{
		this.myProtocol = p;
	}
	
	/**
	 * @title	getInstrument
	 * @return	myInstrument
	 */
	public String getInstrument()
	{
		return myInstrument;
	}
	
	/**
	 * @title setInstrument
	 * @param i - myInstrument
	 */
	public void setInstrument(String i)
	{
		this.myInstrument = i;
	}
	
	/**
	 * @title	getREM
	 * @return	myREM
	 */
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
	
	/**
	 * @title setREM
	 * @param r - myREM
	 */
	public void setREM(String r)
	{
		this.myREM = r;
	}
	
	/**
	 * @title setREM
	 * @param b - myREM
	 */
	public void setREM(boolean b)
	{
		if(b)
			this.myREM = "1";
		else
			this.myREM = "0";
	}
	
	/**
	 * @title	getComments
	 * @return	myComments
	 */
	public String getComments()
	{
		return myComments;
	}
	
	/**
	 * @title setComments
	 * @param c - myComments
	 */
	public void setComments(String c)
	{
		myComments = c;
	}
	
	/**
	 * @title	getNextVisit
	 * @return	myNextVisit
	 */
	public String getNextVisit()
	{
		return myNextVisit;
	}
	
	/**
	 * @title setNextVisit
	 * @param n - myNextVisit
	 */
	public void setNextVisit(String n)
	{
		myNextVisit = n;
	}
	
	/**
	 * @title	getNextVisitMonth
	 * @return	myNextVisitMonth
	 */
	public String getNextVisitMonth()
	{
		return myNextVisitMonth;
	}
	
	/**
	 * @title setNextVisitMonth
	 * @param n - myNextVisitMonth
	 */
	public void setNextVisitMonth(String n)
	{
		myNextVisitMonth = n;
	}
	
	/**
	 * @title	getNextVisitDay
	 * @return	myNextVisitDay
	 */
	public String getNextVisitDay()
	{
		return myNextVisitDay;
	}
	
	/**
	 * @title setNextVisitDay
	 * @param n - myNextVisitDay
	 */
	public void setNextVisitDay(String n)
	{
		myNextVisitDay = n;
	}
	
	/**
	 * @title	getNextVisitYear
	 * @return	myNextVisitYear
	 */
	public String getNextVisitYear()
	{
		return myNextVisitYear;
	}
	
	/**
	 * @title setNextVisitYear
	 * @param n - myNextVisitYear
	 */
	public void setNextVisitYear(String n)
	{
		myNextVisitYear = n;
	}
	
	/**
	 * @title	getVisitInfo
	 * @return	Info for the visit
	 * @desc	Finds the information for the visit that is to be displayed in the table.
	 */
	public String[] getVisitInfo()
	{
		String[] visitInfo = {myVisitID, myDate, myTHCNumber, myVisitSequence,
							  myProblemRank, myCategory, myProtocol, myInstrument,
							  myREM, myFU, myComments};
		return visitInfo;
	}
}
