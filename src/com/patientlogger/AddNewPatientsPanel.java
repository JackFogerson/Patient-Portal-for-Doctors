package com.patientlogger;

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
		setLayout(new GridLayout(10, 4));
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
		JLabel blankSpace1 = new JLabel();
		JLabel blankSpace2 = new JLabel();
		
		JButton saveButton = new JButton("Save");
		JButton addDemoButton = new JButton("Add Demographics");
		JButton newVisitButton = new JButton("New Visit");
		JButton cancelButton = new JButton("Cancel");	

		add(THCNumberLabel);
		add(THCNumberField);
		add(currentDateLabel);
		add(currentDateField);
		add(firstNameLabel);
		add(firstNameField);
		add(middleNameLabel);
		add(middleNameField);
		add(lastNameLabel);
		add(lastNameField);
		add(dobLabel);
		add(dobField);
		add(genderLabel);
		add(genderField);
		add(phoneLabel);
		add(phoneField);
		add(emailLabel);
		add(emailField);
		add(streetLabel);
		add(streetField);
		add(cityLabel);
		add(cityField);
		add(stateLabel);
		add(stateField);
		add(zipLabel);
		add(zipField);
		add(countryLabel);
		add(countryField);
		add(photoLabel);
		add(photoField);
		add(ssnLabel);
		add(ssnField);
		add(insuranceLabel);
		add(insuranceField);
		add(blankSpace1);
		add(blankSpace2);
		add(saveButton);
		add(addDemoButton);
		add(newVisitButton);
		add(cancelButton);
				
				
	}
}
