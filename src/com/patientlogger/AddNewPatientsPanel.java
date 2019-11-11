package com.patientlogger;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
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
	
	final String[] monthList = {"--", "01", "02", "03", "04", "05", "06", "07",
								"08", "09", "10", "11", "12"};
	
	final String[] dayList = {"--", "01", "02", "03", "04", "05", "06", "07", "08", "09",
							  "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", 
							  "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
							  "30", "31"};
	
	final String[] yearList = {"----", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012",
							   "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003",
							   "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994",
							   "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985",
							   "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976",
							   "1975", "1974", "1973", "1972", "1971", "1970"};
	
	Connection conn;		
	
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
		
		JTextField THCNumberField = new JTextField();
		JTextField currentDateField = new JTextField();
		JTextField firstNameField = new JTextField();
		JTextField middleNameField = new JTextField();
		JTextField lastNameField = new JTextField();
		JPanel dobField = new JPanel(new GridLayout(1, 3));
		JTextField monthField = new JTextField("MM");
		JTextField dayField = new JTextField("DD");
		JTextField yearField = new JTextField("YYYY");
		dobField.add(monthField);
		dobField.add(dayField);
		dobField.add(yearField);
		JComboBox<String> genderField = new JComboBox<String>(genderList);
		JTextField phoneField = new JTextField();
		JTextField emailField = new JTextField();
		JTextField streetField = new JTextField();
		JComboBox<String> cityField = new JComboBox<String>(cityList);
		JComboBox<String> stateField = new JComboBox<String>(stateList);
		JTextField zipField = new JTextField();
		JComboBox<String> countryField = new JComboBox<String>(countryList);
		JButton photoField = new JButton(unknownPicture);
		JTextField ssnField = new JTextField();
		JTextField insuranceField = new JTextField();

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
