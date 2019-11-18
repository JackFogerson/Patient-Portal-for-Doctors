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

public class EditPatientScreen extends AddNewPatientsPanel
{
	private static final long serialVersionUID = 1L;
	Patient myPatient;
	
	public EditPatientScreen(Connection conn, Patient account)
	{
		super(conn);
		myPatient = account;
		fillInfo();
	}
	
	private void fillInfo()
	{
		ImageIcon ogUnknownPicture = new ImageIcon(myPatient.getPhoto());
		ImageIcon unknownPicture = new ImageIcon(ogUnknownPicture.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		
		THCNumberField.setText(myPatient.getTHCNumber());
		currentDateField.setText(myPatient.getDate());
		firstNameField.setText(myPatient.getFirstName());
		middleNameField.setText(myPatient.getMiddleName());
		lastNameField.setText(myPatient.getLastName());
		monthField.setText(myPatient.getDob().substring(5,7));
		dayField.setText(myPatient.getDob().substring(8,10));
		yearField.setText(myPatient.getDob().substring(0,4));
		genderField.setSelectedItem(myPatient.getGender());
		phoneField.setText(myPatient.getPhone());
		emailField.setText(myPatient.getEmail());
		streetField.setText(myPatient.getStreetAddress());
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
		
		for(ActionListener al : super.getSaveButton().getActionListeners())
		{
			super.getSaveButton().removeActionListener(al);
		}
		
		for(ActionListener al : super.getCancelButton().getActionListeners())
		{
			super.getCancelButton().removeActionListener(al);
		}
		
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
	
	private void submitNewInformation() throws SQLException
	{	
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		
		PreparedStatement deleteStmt = conn.prepareStatement("DELETE FROM PATIENTS WHERE THCNumber ='" + myPatient.getTHCNumber() + "';");
		deleteStmt.execute();

		
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
		
		SwingUtilities.windowForComponent(this).dispose();
		
		return;
	}
	
}
