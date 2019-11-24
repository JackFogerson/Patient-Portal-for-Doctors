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
		fillInfo();
	}
	
	private void fillInfo()
	{
		visitIDField.setText(myVisit.getVisitID());
		visitDateField.setText(myVisit.getDate());
		nameField.setText(getName());
		thcField.setText(myVisit.getTHCNumber());
		visitSequenceField.setText(myVisit.getVisitSequence());
		problemRankField.setSelectedItem(myVisit.getProblemRank());
		categoryField.setSelectedItem(myVisit.getCategory());
		protocolField.setSelectedItem(myVisit.getProtocol());
		fuField.setSelectedItem(myVisit.getFU());
		instrumentField.setSelectedItem(myVisit.getInstrument());
		nextVisitField.setText(myVisit.getNextVisit());
		remField.setSelected(myVisit.getREM());
		commentField.setText(myVisit.getComments());
	}
}
