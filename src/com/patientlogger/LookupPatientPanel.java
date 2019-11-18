package com.patientlogger;

import java.sql.Connection;

import javax.swing.JPanel;

public class LookupPatientPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	Connection conn;
	
	public LookupPatientPanel(Connection c)
	{
		conn = c;
		buildPanel();
	}
	
	private void buildPanel()
	{
		
	}
}
