package com.patientlogger;

import java.sql.Connection;

public class EditVisitScreen extends AddNewVisitPanel
{
	private static final long serialVersionUID = 1L;
	Connection conn;
	Visit myVisit;

	public EditVisitScreen(Connection c, Visit visit) {
		super(c);
		myVisit = visit;
	}

}
