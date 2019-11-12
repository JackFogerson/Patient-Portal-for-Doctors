package com.patientlogger;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AddNewPatientsPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	GroupLayout layout;
	
	final String[] genderList = {"Select One", "Male", "Female", "Other"};
	final String[] cityList = {"Select One", "Albany", "Annapolis", "Atlanta", "Augusta", "Austin",
							   "Baton Rouge", "Bismarck", "Boise", "Boston", "Carson City", 
							   "Charleston", "Cheyenne", "Columbia", "Columbus", "Concord", "Denver",
							   "Des Moines", "Dover", "Frankfort", "Harrisburg", "Hartford", "Helena",
							   "Honolulu", "Indianapolis", "Jackson", "Jefferson City", "Juneau", 
							   "Lansing", "Lincoln", "Little Rock", "Madison", "Montgomery", "Montpelier",
							   "Nashville", "Oklahoma City", "Olympia", "Phoenix", "Pierre", "Providence",
							   "Raleigh", "Richmond", "Sacramento", "Saint Paul", "Salem", "Salt Lake City",
							   "Santa Fe", "Springfield", "Tallahassee", "Topeka", "Trenton"};
	final String[] stateList = {"Select One", "Alabama", "Arkansas", "Arizona", "Alaska", "California", 
								"Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii",
								"Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky",
								"Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan",
								"Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska",
								"Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York",
								"North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon",
								"Pennsylvania", "Rhode Island", "South Carolina", "South Dakota",
								"Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington",
								"West Virginia", "Wisconsin", "Wyoming" };
	
	final String[] countryList = {"Select One", "United States of America"};
	
	JFrame demographicsFrame;
	
	Connection conn;	
	
	JTextField THCNumberField, currentDateField, firstNameField, middleNameField, lastNameField, monthField,
	 		   dayField, yearField, phoneField, emailField, streetField, zipField, ssnField, insuranceField,
	 		   occupationField, workStatusField, educationField, tOnsetField, tEtioField, hOnsetField,
	 		   hEtioField, commentField;
	JComboBox<String> genderField, cityField, stateField, countryField;
	
	JButton photoField;
	
	JPanel dobField;
	
	JLabel THCNumberLabel, currentDateLabel, firstNameLabel, middleNameLabel, lastNameLabel, dobLabel,
		   genderLabel, phoneLabel, emailLabel, streetLabel, cityLabel, stateLabel, zipLabel, countryLabel,
		   photoLabel, ssnLabel, insuranceLabel, blankSpace1, blankSpace2, demographicsLabel, occupationLabel,
		   workStatusLabel, educationLabel, tOnsetLabel, tEtioLabel, hOnsetLabel, hEtioLabel, commentLabel;
	
	JButton saveButton, addDemoButton, newVisitButton, cancelButton;
	
	public AddNewPatientsPanel(Connection c)
	{
		this.conn = c;
		setLayout(new GridLayout(10, 4));
		buildPanel();
	}
	
	private void buildPanel()
	{	
		ImageIcon ogUnknownPicture = new ImageIcon("src/images/unknownPicture.png");
		ImageIcon unknownPicture = new ImageIcon(ogUnknownPicture.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		
		// Initalize the following for the normal form.
		THCNumberField = new JTextField();
		currentDateField = new JTextField();
		firstNameField = new JTextField();
		middleNameField = new JTextField();
		lastNameField = new JTextField();
		dobField = new JPanel(new GridLayout(1, 3));
		monthField = new JTextField("MM");
		dayField = new JTextField("DD");
		yearField = new JTextField("YYYY");
		dobField.add(monthField);
		dobField.add(dayField);
		dobField.add(yearField);
		genderField = new JComboBox<String>(genderList);
		phoneField = new JTextField();
		emailField = new JTextField();
		streetField = new JTextField();
		cityField = new JComboBox<String>(cityList);
		stateField = new JComboBox<String>(stateList);
		zipField = new JTextField();
		countryField = new JComboBox<String>(countryList);
		photoField = new JButton(unknownPicture);
		ssnField = new JTextField();
		insuranceField = new JTextField();
		
		// Initialize the following for the demographics form.
		occupationField = new JTextField();
		workStatusField = new JTextField();
		educationField = new JTextField();
		tOnsetField = new JTextField();
		tEtioField = new JTextField();
		hOnsetField = new JTextField();
		hEtioField = new JTextField();
		commentField = new JTextField();
		demographicsLabel = new JLabel("Demographics");
		occupationLabel = new JLabel("Occupation");
		workStatusLabel = new JLabel("Work Status");
		educationLabel = new JLabel("Educational Degree");
		tOnsetLabel = new JLabel("Tinnitus Onset");
		tEtioLabel = new JLabel("Tinnitus Etiology");
		hOnsetLabel = new JLabel("Hyperacusis Onset");
		hEtioLabel = new JLabel("Hyperacusis Etiology");
		commentLabel = new JLabel("Additional Comments");
		
		monthField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				monthField.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				// Do Nothing	
			}
		});
		dayField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				dayField.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				// Do Nothing	
			}
		});
		yearField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				yearField.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				// Do Nothing	
			}
		});
		
		photoField.setBorderPainted(false);
		photoField.addActionListener(event -> changePicture(photoField));
		
		int currentTHC = getRowCount();
		THCNumberField.setText(Integer.toString(currentTHC + 1));
		THCNumberField.setEditable(false);
		THCNumberField.setBackground(Color.GRAY);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		LocalDate localDate = LocalDate.now();
		
		currentDateField.setEditable(false);
		currentDateField.setText(dtf.format(localDate));
		currentDateField.setBackground(Color.GRAY);
		
		THCNumberLabel = new JLabel("THC Number");
		currentDateLabel = new JLabel("Current Date");
		firstNameLabel = new JLabel("First Name");
		middleNameLabel = new JLabel("Middle Name");
		lastNameLabel = new JLabel("Last Name");
		dobLabel = new JLabel("Date of Birth");
		genderLabel = new JLabel("Gender");
		phoneLabel = new JLabel("Phone");
		emailLabel = new JLabel("Email");
		streetLabel = new JLabel("Street");
		cityLabel = new JLabel("City");
		stateLabel = new JLabel("State");
		zipLabel = new JLabel("Zipcode");
		countryLabel = new JLabel("Country");
		photoLabel = new JLabel("Photo");
		ssnLabel = new JLabel("SSN");
		insuranceLabel = new JLabel("Insurance");	
		blankSpace1 = new JLabel();
		blankSpace2 = new JLabel();
		
		saveButton = new JButton("Save");
		addDemoButton = new JButton("Add Demographics");
		newVisitButton = new JButton("New Visit");
		cancelButton = new JButton("Cancel");	
		
		saveButton.addActionListener(e -> submitInformation(THCNumberField, currentDateField, firstNameField,
									 middleNameField, lastNameField, monthField, dayField, yearField, genderField, phoneField,
									 emailField, streetField, cityField, zipField, countryField, photoField, ssnField,
									 insuranceField));

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
	
	private void changePicture(JButton photoField)
	{
		File inputPicture = null;
		
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new FileNameExtensionFilter("JPG and PNG", "jpg", "png"));
		int returnVal = jfc.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
			inputPicture = jfc.getSelectedFile();
		}
		
		if(inputPicture != null)
		{
			try 
			{
				Files.copy(inputPicture.toPath(), new File("src/images/inputPicture.png").toPath(), StandardCopyOption.REPLACE_EXISTING);
				
				ImageIcon ogInPic = new ImageIcon("src/images/inputPicture.png");
				ImageIcon inPic = new ImageIcon(ogInPic.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
				
				photoField.setIcon(inPic);
				photoField.repaint();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	 
	private void submitInformation(JTextField THCNumberField, JTextField currentDateField, JTextField firstNameField,
			JTextField middleNameField, JTextField lastNameField, JTextField monthField, JTextField dayField, JTextField yearField, JComboBox<String> genderField, JTextField phoneField,
			JTextField emailField, JTextField streetField, JComboBox<String> cityField, JTextField zipField, JComboBox<String> countryField, JButton photoField, JTextField ssnField,
			JTextField insuranceField)
	{
		boolean isError = false;
		String errorLog = "";
		
		if(firstNameField.getText().equals(""))
		{
			errorLog += "First Name, ";
			isError = true;
		}
		if(lastNameField.getText().equals(""))
		{
			errorLog += "Last Name, ";
			isError = true;
		}
		if(monthField.getText().equals("MM") || monthField.getText().equals(""))
		{
			errorLog += "Month of Birth, ";
			isError = true;
		}
		if(dayField.getText().equals("DD") || dayField.getText().equals(""))
		{
			errorLog += "Day of Birth, ";
			isError = true;
		}
		if(yearField.getText().equals("YYYY") || yearField.getText().equals(""))
		{
			errorLog += "Year";
			isError = true;
		}
		if(genderField.getSelectedItem().equals("Select One"))
		{
			errorLog += "Gender, ";
			isError = true;
		}
		if(phoneField.getText().equals(""))
		{
			errorLog += "Phone, ";
			isError = true;
		}
		if(streetField.getText().equals(""))
		{
			errorLog += "Street, ";
			isError = true;
		}
		if(cityField.getSelectedItem().equals("Select One"))
		{
			errorLog += "City, ";
			isError = true;
		}
		if(zipField.getText().equals(""))
		{
			errorLog += "Zip, ";
			isError = true;
		}
		if(countryField.getSelectedItem().equals("Select One"))
		{
			errorLog += "Country, ";
			isError = true;
		}
		if(isError)
		{
			errorLog = errorLog.substring(0, errorLog.length() - 2) + ".";
			JOptionPane.showMessageDialog(null, "The following fields can not be empty: " + errorLog, "eTRT - Decision Support System for Tinnitus Restraining Therapy", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(AddNewPatientsPanel.class.getResource("/images/eTRT_icon.png")));
			return;
		}
		return;
	}
	
	private int getRowCount()
	{
		int rowCount = 0;
		
		try 
		{
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery ("SELECT THCNumber FROM Patients ORDER BY THCNumber DESC LIMIT 1;");
			rset.next();
			rowCount = rset.getInt(1);
		} 
		catch (SQLException e) 
		{
			System.out.println("Couldn't get row count.");
			e.printStackTrace();
		}
		
		return rowCount;
	}
}
