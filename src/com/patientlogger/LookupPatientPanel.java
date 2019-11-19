package com.patientlogger;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.Connection;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LookupPatientPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	Connection conn;
	
	String[] searchOptions = {"Choose One", "Name", "THC Number", "SSN"};
	JComboBox<String> searchCriteria;
	JTextField searchBox;
	JButton searchButton;
	
	JTextField THCNumberField, currentDateField, firstNameField, middleNameField, lastNameField, monthField,
	   		   dayField, yearField, phoneField, emailField, streetField, zipField, ssnField, insuranceField,
	   		   occupationField, workStatusField, educationField, tOnsetField, tEtioField, hOnsetField,
	   		   hEtioField, genderField, cityField, stateField, countryField;

	JTextArea commentField;

	JButton photoField;

	JPanel dobField, patientPanel;

	JLabel THCNumberLabel, currentDateLabel, firstNameLabel, middleNameLabel, lastNameLabel, dobLabel,
		   genderLabel, phoneLabel, emailLabel, streetLabel, cityLabel, stateLabel, zipLabel, countryLabel,
		   photoLabel, ssnLabel, insuranceLabel, blankSpace1, blankSpace2, demographicsLabel, occupationLabel,
		   workStatusLabel, educationLabel, tOnsetLabel, tEtioLabel, hOnsetLabel, hEtioLabel, commentLabel;
	
	JScrollPane patientScrollable;
	
	
	public LookupPatientPanel(Connection c)
	{
		conn = c;
		setLayout(new GridBagLayout());
		buildPanel();
	}
	
	private void buildPanel()
	{
		ImageIcon ogUnknownPicture = new ImageIcon("src/images/unknownPicture.png");
		ImageIcon unknownPicture = new ImageIcon(ogUnknownPicture.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		
		searchCriteria = new JComboBox<String>(searchOptions);
		searchBox = new JTextField(10);
		searchButton = new JButton("Search");
		patientPanel = new JPanel();
		
		patientPanel.setLayout(new GridBagLayout());
		buildPatientPanel();
		
		patientScrollable = new JScrollPane(patientPanel);
		patientScrollable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		searchButton.addActionListener(e -> search());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		c.ipadx = 0;
		add(searchCriteria, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.ipady = 0;
		c.ipadx = 250;
		add(searchBox, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		c.ipadx = 50;
		add(searchButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 3;
		c.gridheight = 10;
		c.ipady = 300;
		c.ipadx = 300;
		add(patientScrollable, c);
	}
	
	private void buildPatientPanel()
	{	
		currentDateField = new JTextField(10);
		dobField = new JPanel(new GridLayout(1, 3));
		monthField = new JTextField("MM");
		dayField = new JTextField("DD");
		yearField = new JTextField("YYYY");
		dobField.add(monthField);
		dobField.add(dayField);
		dobField.add(yearField);
		genderField = new JTextField();
		phoneField = new JTextField();
		emailField = new JTextField();
		streetField = new JTextField();
		cityField = new JTextField();
		stateField = new JTextField();
		zipField = new JTextField();
		countryField = new JTextField();
		ssnField = new JTextField();
		insuranceField = new JTextField();	
		occupationField = new JTextField();
		workStatusField = new JTextField();
		educationField = new JTextField();
		tOnsetField = new JTextField();
		tEtioField = new JTextField();
		hOnsetField = new JTextField();
		hEtioField = new JTextField();
		commentField = new JTextArea(4, 30);
		currentDateLabel = new JLabel("Current Date");
		dobLabel = new JLabel("Date of Birth");
		genderLabel = new JLabel("Gender");
		phoneLabel = new JLabel("Phone");
		emailLabel = new JLabel("Email");
		streetLabel = new JLabel("Street");
		cityLabel = new JLabel("City");
		stateLabel = new JLabel("State");
		zipLabel = new JLabel("Zipcode");
		countryLabel = new JLabel("Country");
		ssnLabel = new JLabel("SSN");
		insuranceLabel = new JLabel("Insurance");
		occupationLabel = new JLabel("Occupation");
		workStatusLabel = new JLabel("Work Status");
		educationLabel = new JLabel("Educational Degree");
		tOnsetLabel = new JLabel("Tinnitus Onset");
		tEtioLabel = new JLabel("Tinnitus Etiology");
		hOnsetLabel = new JLabel("Hyperacusis Onset");
		hEtioLabel = new JLabel("Hyperacusis Etiology");
		commentLabel = new JLabel("Additional Comments");
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(currentDateLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(currentDateField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(dobLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(dobField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(genderLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(genderField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(phoneLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(phoneField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(emailLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(emailField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(streetLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(streetField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(cityLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(cityField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(stateLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 7;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(stateField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(zipLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 8;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(zipField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(countryLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 9;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(countryField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(ssnLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 10;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(ssnField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 11;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(insuranceLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 11;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(insuranceField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 12;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(occupationLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 12;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(occupationField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 13;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(workStatusLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 13;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(workStatusField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 14;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(educationLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 14;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(educationField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 15;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(tOnsetLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 15;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(tOnsetField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 16;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(tEtioLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 16;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(tEtioField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 17;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(hOnsetLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 17;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(hOnsetField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 18;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(hEtioLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 18;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(hEtioField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 19;
		c.gridwidth = 1;
		c.gridheight = 1;
		patientPanel.add(commentLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 20;
		c.gridwidth = 2;
		c.gridheight = 3;
		patientPanel.add(commentField, c);
	}
	
	private void search()
	{
		
	}
}
