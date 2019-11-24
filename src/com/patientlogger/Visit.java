package com.patientlogger;

public class Visit 
{
	String myVisitID, myDate, myTHCNumber, myVisitSequence, myProblemRank, myCategory,
		   myProtocol, myFU, myInstrument, myREM, myComments, myNextVisit;
	
	public Visit()
	{
		
	}
	
	public Visit(String visitID, String date, String THCNumber, String visitSequence,
				 String problemRank, String category, String FU, String protocol, String instrument,
				 String REM, String comments, String nextVisit)
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
	}
	
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
	
	public String getREM()
	{
		return myREM;
	}
	
	public void setREM(String r)
	{
		myREM = r;
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





























