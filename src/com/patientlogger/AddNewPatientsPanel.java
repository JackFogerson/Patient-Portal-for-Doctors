package com.patientlogger;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddNewPatientsPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	GroupLayout layout;
			
	public AddNewPatientsPanel()
	{
		setLayout(new GridLayout(12, 4));
		buildPanel();
	}
	
	private void buildPanel()
	{
		ImageIcon ogUnknownPicture = new ImageIcon("src/unknownPicture.png");
		ImageIcon unknownPicture = new ImageIcon(ogUnknownPicture.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		
		JTextField THCNumberField = new JTextField();
		JTextField currentDateField = new JTextField();
		JTextField firstNameField = new JTextField();
		JTextField middleNameField = new JTextField();
		JTextField lastNameField = new JTextField();
		JTextField dobField = new JTextField();
		JComboBox<JLabel> genderField = new JComboBox<JLabel>();
		JTextField phoneField = new JTextField();
		JTextField emailField = new JTextField();
		JTextField streetField = new JTextField();
		JComboBox<JLabel> cityField = new JComboBox<JLabel>();
		JComboBox<JLabel> stateField = new JComboBox<JLabel>();
		JTextField zipField = new JTextField();
		JComboBox<JLabel> countryField = new JComboBox<JLabel>();
		JLabel photoField = new JLabel(unknownPicture);
		JTextField ssnField = new JTextField();
		JTextField insuranceField = new JTextField();
		
		JLabel THCNumberLabel = new JLabel("THC Number");
		JLabel currentDateLabel = new JLabel("Current Date");
		JLabel firstNameLabel = new JLabel("First Name");
		JLabel middleNameLabel = new JLabel("Middle Name");
		JLabel lastNameLabel = new JLabel("Last Name");
		JLabel dobLabel = new JLabel("Date of Birth");
		JLabel genderLabel = new JLabel("Gender");
		JLabel phoneLabel = new JLabel("Phone");
		JLabel emailLabel = new JLabel("Email");
		JLabel streetLabel = new JLabel("Street");
		JLabel cityLabel = new JLabel("City");
		JLabel stateLabel = new JLabel("State");
		JLabel zipLabel = new JLabel("Zipcode");
		JLabel countryLabel = new JLabel("Country");
		JLabel photoLabel = new JLabel("Photo");
		JLabel ssnLabel = new JLabel("SSN");
		JLabel insuranceLabel = new JLabel("Insurance");	
		
		JButton saveButton = new JButton("Save");
		JButton addDemoButton = new JButton("Add Demographics");
		JButton newVisitButton = new JButton("New Visit");
		JButton cancelButton = new JButton("Cancel");	

		add(THCNumberLabel, 0 , 0);
		add(THCNumberField, 0, 1);
		add(currentDateLabel, 0, 2);
		add(currentDateField, 0, 3);
		add(firstNameLabel, 1, 0);
		add(firstNameField, 1, 1);
		add(middleNameLabel, 1, 2);
		add(middleNameField, 1, 3);
		add(lastNameLabel, 2, 0);
		add(lastNameField, 2, 1);
		add(dobLabel, 2, 2);
		add(dobField, 2, 3);
		add(genderLabel, 3, 0);
		add(genderField, 3, 1);
		add(phoneLabel, 3, 2);
		add(phoneField, 3, 3);
		add(emailLabel, 4, 0);
		add(emailField, 4, 1);
		add(streetLabel, 4, 2);
		add(streetField, 4, 3);
		add(cityLabel, 5, 0);
		add(cityField, 5, 1);
		add(stateLabel, 5, 2);
		add(stateField, 5, 3);
		add(zipLabel, 6, 0);
		add(zipField, 6, 1);
		add(countryLabel, 6, 2);
		add(countryField, 6, 3);
		add(photoLabel, 7, 0);
		add(photoField, 7, 1);
		add(ssnLabel, 7, 2);
		add(ssnField, 7, 3);
		add(insuranceLabel, 8, 0);
		add(insuranceField, 8, 1);
		add(saveButton, 9, 0);
		add(addDemoButton, 9, 1);
		add(newVisitButton, 9, 2);
		add(cancelButton, 9, 3);
				
				
	}
}
