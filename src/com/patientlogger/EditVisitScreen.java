package com.patientlogger;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.SwingUtilities;

/**
 * @title	EditVisitScreen
 * @author	Nick Fulton, Jack Fogerson
 * @desc	This JPanel is an extension of the AddNewVisit Panel, similar except
 * 			it allows for visit editing instead of creation.
 */
public class EditVisitScreen extends AddNewVisitPanel
{
	private static final long serialVersionUID = 1L;
	Connection conn;
	Visit myVisit;

	/**
	 * @title	EditVisitScreen
	 * @param	c - The connection to the database.
	 * @param	visit - The account to be edited.
	 * @desc	Send the connection to super and then fill the info.
	 */
	public EditVisitScreen(Connection c, Visit visit) 
	{
		super(c);
		myVisit = visit;
		fillInfo();
	}
	
	/**
	 * @title	fillInfo
	 * @desc	Imports Visit info into the form.
	 */
	private void fillInfo()
	{
		//Import the data to all of the fields.
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
		monthField.setSelectedItem(myVisit.getNextVisitMonth());
		dayField.setSelectedItem(myVisit.getNextVisitDay());
		yearField.setText(myVisit.getNextVisitYear());		
		remField.setSelected(myVisit.getREM());
		commentField.setText(myVisit.getComments());
		
		// Removes action listeners of the save and cancel buttons. This is needed
		// because they are linked with the super's action listeners, which mess up the
		// editing process
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
	
	/**
	 * @title	newCancel
	 * @desc	Closes the visit edit form
	 */
	private void newCancel()
	{
		SwingUtilities.windowForComponent(this).dispose();
	}
	
	/**
	 * @title	submitNewInformation
	 * @throws	SQLException - If the form has a problem with submitting data to the database.
	 * @desc	Submits newest information to the table by deleting the user, and inputting the new data.
	 */
	private void submitNewInformation() throws SQLException
	{	
		// Get current date.
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		
		//For CheckBox
		String rem = "";
		
		if(remField.isSelected())
		{
			rem += "1";
		}
		else
		{
			rem += "0";
		}
		
		// Delete the current patient from database.
		PreparedStatement deleteStmt = conn.prepareStatement("DELETE FROM VISITS WHERE VisitID ='" + myVisit.getVisitID() + "';");
		deleteStmt.execute();

		// Re-input the user.
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
									  + "'" + dayField.getSelectedItem() + "/" + monthField.getSelectedItem() + "/" + yearField.getText() + "')";

		// Perform query
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.execute();
		
		// Close the patient screen.
		SwingUtilities.windowForComponent(this).dispose();
		
		return;
	}
}
