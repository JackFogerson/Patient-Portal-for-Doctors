package com.patientlogger;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.SwingUtilities;

public class EditVisitScreen extends AddNewVisitPanel
{
	private static final long serialVersionUID = 1L;
	Connection conn;
	Visit myVisit;

	public EditVisitScreen(Connection c, Visit visit) 
	{
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
		
		// Remove the current action listeners of the save and cancel buttons. This is needed
		// because they are still linked with the super's action listeners, which mess up the
		// editing process. We need to change the action listeners to the edit version of the
		// save and cancel button.
		for(ActionListener al : super.getSaveButton().getActionListeners())
		{
			super.getSaveButton().removeActionListener(al);
		}
		
		for(ActionListener al : super.getCancelButton().getActionListeners())
		{
			super.getCancelButton().removeActionListener(al);
		}
		
		// Add the edit patient version of the action listeners to the buttons.
		cancelButton.addActionListener(e -> newCancel());
		saveButton.addActionListener(e -> {
			try 
			{
				submitNewInformation();
			} 
			catch (SQLException ex) 
			{
				ex.printStackTrace();
			}
		});
		
		
	}
	
	private void newCancel()
	{
		SwingUtilities.windowForComponent(this).dispose();
	}
	
	/**
	 * @title	submitNewInformation Method
	 * @throws	SQLException - If the form has a problem with submitting data to the database.
	 * @desc	Submits the newest information to the table by deleting the user, and reinputting it under the new data.
	 */
	private void submitNewInformation() throws SQLException
	{	
		// Get the currnet date.
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		
		String rem = "";
		
		if(remField.isSelected())
		{
			rem += "1";
		}
		else
		{
			rem += "0";
		}
		
		// Delete the current patient from the database.
		PreparedStatement deleteStmt = conn.prepareStatement("DELETE FROM VISITS WHERE VisitID ='" + myVisit.getVisitID() + "';");
		deleteStmt.execute();

		// Reinput the user.
		String query = "INSERT INTO Visits(VisitID, Date, THCNumber, VisitSequence, ProblemRank, Category, Protocol, FU, Instrument, REM, Comments, NextVisit) "
								   + "VALUES(" + visitIDField.getText() + ", "
								   		  + "'" + dtf.format(localDate) + "', "
								   		  + "'" + thcField.getText() + "', "
								   		  + "'" + visitSequenceField.getText() + "', "
								   		  + "'" + problemRankField.getSelectedItem() + "', "
								   		  + "'" + categoryField.getSelectedItem() + "', "
								   		  + "'" + protocolField.getSelectedItem() + "', "
								   		  + "'" + fuField.getSelectedItem() + "', "
								   		  + "'" + instrumentField.getSelectedItem() + "', "
								   		  + "'" + rem + "', "
								   		  + "'" + commentField.getText() + "', "
								   		  + "'" + nextVisitField.getText() + "')";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.execute();
		
		// Close the patient screen.
		SwingUtilities.windowForComponent(this).dispose();
		
		return;
	}
}
