package com.patientlogger;

import java.awt.Dimension;

import javax.swing.GroupLayout;
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
		layout = new GroupLayout(this);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		setLayout(layout);
		buildPanel();
	}
	
	private void buildPanel()
	{
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
		JLabel photoField = new JLabel();
		JTextField ssnField = new JTextField();
		JTextField insuranceField = new JTextField();
		
		JLabel THCNumberLabel = new JLabel("THC Number");
		THCNumberLabel.add(THCNumberField);
		
		JLabel currentDateLabel = new JLabel("Current Date");
		currentDateLabel.add(currentDateField);
		
		JLabel firstNameLabel = new JLabel("First Name");
		firstNameLabel.add(firstNameField);
		
		JLabel middleNameLabel = new JLabel("Middle Name");
		middleNameLabel.add(middleNameField);
		
		JLabel lastNameLabel = new JLabel("Last Name");
		lastNameLabel.add(lastNameField);
		
		JLabel dobLabel = new JLabel("Date of Birth");
		dobLabel.add(dobField);
		
		JLabel genderLabel = new JLabel("Gender");
		genderLabel.add(genderField);
		
		JButton saveButton = new JButton("Save");
		JButton addDemoButton = new JButton("Add Demographics");
		JButton newVisitButton = new JButton("New Visit");
		JButton cancelButton = new JButton("Cancel");
		
		
	}
}
