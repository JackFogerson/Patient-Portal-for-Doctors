package com.patientlogger;

import java.sql.Connection;
import javax.swing.JPanel;

//TODO: Should we add to this panel? Unsure of
//		use since it is blank

/**
 * @title	AddNewVisitPanel
 * @author	Nick Fulton, Jack Fogerson
 * @desc	This class is an extension of JPanel
 */
public class DemographicsDataPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	Connection conn;
	
	/**
	 *	@title	DemographicsDataPanel
	 *  @desc	constructor, builds panel
	 *  @param 	c - Is the connection to the database.
	 */
	public DemographicsDataPanel(Connection c)
	{
		conn = c;
	}
}
