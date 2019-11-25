package com.patientlogger;

import java.sql.Connection;

import javax.swing.JPanel;

public class DemographicsDataPanel extends JPanel
{
	Connection conn;
	
	public DemographicsDataPanel(Connection c)
	{
		conn = c;
	}
}
