package com.patientlogger;

import java.sql.Connection;

import javax.swing.JPanel;

public class ViewVisitsPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	Connection conn;
	
	public ViewVisitsPanel(Connection c)
	{
		conn = c;
	}
}
