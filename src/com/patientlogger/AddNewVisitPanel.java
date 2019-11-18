package com.patientlogger;

import java.sql.Connection;

import javax.swing.JPanel;

public class AddNewVisitPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	Connection conn;
	
	public AddNewVisitPanel(Connection c)
	{
		conn = c;
	}
}
