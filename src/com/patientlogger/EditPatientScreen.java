package com.patientlogger;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

/**
 * @title	EditPatientScreen
 * @author	Nick Fulton, Jack Fogerson
 * @desc	This JPanel is an extension of the AddNewPatients Panel, similar except
 * 			it allows for patient editing instead of creation.
 */
public class EditPatientScreen extends AddNewPatientsPanel
{
	// Instantiate the needed variables.
	private static final long serialVersionUID = 1L;
	Patient myPatient;
	
	/**
	 * @title	EditPatientScreen
	 * @param	conn - The connection to the database.
	 * @param	account - The account to be edited.
	 * @desc	Send the connection to super and then fill the info.
	 */
	public EditPatientScreen(Connection conn, Patient account)
	{
		super(conn);
		myPatient = account;
		fillInfo();
	}
	
	/**
	 * @title	fillInfo
	 * @desc	Imports all of the Patient's info into the form.
	 */
	private void fillInfo()
	{
		// Set the picture to the patient's stored picture and scale it.
		ImageIcon ogUnknownPicture = new ImageIcon(myPatient.getPhoto());
		ImageIcon unknownPicture = new ImageIcon(ogUnknownPicture.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		
		//Import the data to all of the text fields.
		THCNumberField.setText(myPatient.getTHCNumber());
		currentDateField.setText(myPatient.getDate());
		firstNameField.setText(myPatient.getFirstName());
		middleNameField.setText(myPatient.getMiddleName());
		lastNameField.setText(myPatient.getLastName());
		monthField.setSelectedItem(myPatient.getDOBMonth());
		dayField.setSelectedItem(myPatient.getDOBDay());
		yearField.setText(myPatient.getDOBYear());
		genderField.setSelectedItem(myPatient.getGender());	
		areaCodeField.setText(myPatient.getareaCode());
		phone1Field.setText(myPatient.getphone1());
		phone2Field.setText(myPatient.getphone2());		
		emailField.setText(myPatient.getEmail());
		addressField.setText(myPatient.getStreetAddress());
		cityField.setSelectedItem(myPatient.getCity());
		stateField.setSelectedItem(myPatient.getState());
		zipField.setText(myPatient.getZip());
		countryField.setSelectedItem(myPatient.getCountry());
		photoField.setIcon(unknownPicture);
		
		ssnField.setText(myPatient.getSsid());
		insuranceField.setText(myPatient.getInsurance());
		occupationField.setText(myPatient.getOccupation());
		workStatusField.setText(myPatient.getWorkStatus());
		educationField.setText(myPatient.getEducationalDegree());
		tOnsetField.setText(myPatient.getTOnset());
		tEtioField.setText(myPatient.getTEtio());
		hOnsetField.setText(myPatient.getHOnset());
		hEtioField.setText(myPatient.getHEtio());
		commentField.setText(myPatient.getComments());
		
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
	
	/**
	 * @title	newCancel Method
	 * @desc	Closes the edit form for the patient.
	 */
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
		
		// Delete the current patient from the database.
		PreparedStatement deleteStmt = conn.prepareStatement("DELETE FROM PATIENTS WHERE THCNumber ='" + myPatient.getTHCNumber() + "';");
		deleteStmt.execute();

		// Reinput the user.
		String query = "INSERT INTO Patients(THCNumber, Date, FirstName, MiddleName, LastName, DOB, Gender, Phone, Email, StreetAddress, City, State, Zip, Country, Photo, SSID, Insurance, Occupation, WorkStatus, EducationalDegree, TOnset, TEtiology, HOnset, HEtiology, Comments) "
								   + "VALUES(" + THCNumberField.getText() + ", "
								   		  + "'" + dtf.format(localDate) + "', "
								   		  + "'" + firstNameField.getText() + "', "
								   		  + "'" + middleNameField.getText() + "', "
								   		  + "'" + lastNameField.getText() + "', "
								   		  + "'" + yearField.getText() + "-" + monthField.getText() + "-" + dayField.getText() + "', "
								   		  + "'" + genderField.getSelectedItem() + "', "
								   		  + "'" + phoneField.getText() + "', "
								   		  + "'" + emailField.getText() + "', "
								   		  + "'" + streetField.getText() + "', "
								   		  + "'" + cityField.getSelectedItem() + "', "
								   		  + "'" + stateField.getSelectedItem() + "', "
								   		  + "'" + zipField.getText() + "', "
								   		  + "'" + countryField.getSelectedItem() + "', "
								   		  + "'" + "src/images/" + THCNumberField.getText() + ".png', "
								   		  + "'" + ssnField.getText() + "', "
								   		  + "'" + insuranceField.getText() + "', "
								   		  + "'" + occupationField.getText() + "', "
								   		  + "'" + workStatusField.getText() + "', "
								   		  + "'" + educationField.getText() + "', "
								   		  + "'" + tOnsetField.getText() + "', "
								   		  + "'" + tEtioField.getText() + "', "
								   		  + "'" + hOnsetField.getText() + "', "
								   		  + "'" + hEtioField.getText() + "', "
								   		  + "'" + commentField.getText() + "')";
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.execute();
		
		// Close the patient screen.
		SwingUtilities.windowForComponent(this).dispose();
		
		return;
	}
	
}
