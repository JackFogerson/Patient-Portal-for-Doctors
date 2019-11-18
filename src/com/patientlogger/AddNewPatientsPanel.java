package com.patientlogger;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @title	AddNewPatientsPanel
 * @author	Nick Fulton, Jack Fogerson
 * @desc	This class is an extension of JPanel which allows the user to add new patients to the
 * 			database.
 */
public class AddNewPatientsPanel extends JPanel
{
	// First thing's first, go through and instantiate all of the needed variables.
	private static final long serialVersionUID = 1L;
	
	GroupLayout layout;
	
	//These four variables are for demographic info collected from the patient to add to database
	
	final String[] genderList = {"Select One", "Male", "Female", "Other"};
	
	final String[] cityList = {"Select One", "Albany", "Annapolis", "Atlanta", "Augusta", "Austin",
							   "Baton Rouge", "Bismarck", "Boise", "Boston", "Carson City", 
							   "Charleston", "Cheyenne", "Columbia", "Columbus", "Concord", "Denver",
							   "Des Moines", "Dover", "Frankfort", "Harrisburg", "Hartford", "Helena",
							   "Honolulu", "Indianapolis", "Jackson", "Jefferson City", "Juneau", 
							   "Lansing", "Lincoln", "Little Rock", "Los Angeles", "Madison", "Montgomery",
							   "Montpelier", "Nashville", "New York","Oklahoma City", "Olympia", "Phoenix", 
							   "Pierre", "Providence", "Raleigh", "Richmond", "Sacramento", "Saint Paul", 
							   "Salem", "Salt Lake City", "San Jose", "Santa Fe", "Springfield", "Tallahassee",
							   "Topeka", "Trenton"};
	final String[] stateList = {"Select One", "Alabama", "Arkansas", "Arizona", "Alaska", "California", 
								"Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii",
								"Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky",
								"Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan",
								"Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska",
								"Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York",
								"North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon",
								"Pennsylvania", "Rhode Island", "South Carolina", "South Dakota",
								"Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington",
								"Washington, D.C.",	"West Virginia", "Wisconsin", "Wyoming" };
	final String[] countryList = {"Select One", "United States of America"};
	
	JFrame demographicsFrame;
	
	Connection conn;	
	
	//Contains all data fields needed from patient	
	JTextField THCNumberField, currentDateField, firstNameField, middleNameField, lastNameField, monthField,
	 		   dayField, yearField, phoneField, emailField, streetField, zipField, ssnField, insuranceField,
	 		   occupationField, workStatusField, educationField, tOnsetField, tEtioField, hOnsetField,
	 		   hEtioField;
	
	JTextArea commentField;
	
	JComboBox<String> genderField, cityField, stateField, countryField;
	
	JButton photoField;
	
	JPanel dobField;
	
	JLabel THCNumberLabel, currentDateLabel, firstNameLabel, middleNameLabel, lastNameLabel, dobLabel,
		   genderLabel, phoneLabel, emailLabel, streetLabel, cityLabel, stateLabel, zipLabel, countryLabel,
		   photoLabel, ssnLabel, insuranceLabel, blankSpace1, blankSpace2, demographicsLabel, occupationLabel,
		   workStatusLabel, educationLabel, tOnsetLabel, tEtioLabel, hOnsetLabel, hEtioLabel, commentLabel;
	
	JButton saveButton, addDemoButton, newVisitButton, cancelButton, demoSaveButton, demoCancelButton;
	
	/**
	 * @title	AddNewPatientsPanel Constructor
	 * @param 	c - Is the connection to the database.
	 */
	public AddNewPatientsPanel(Connection c)
	{
		this.conn = c;
		buildPanel();
	}
	
	/**
	 *	@title	buildPanel method
	 *	@desc	Is the local method used to build this panel, everything from assigning listeners to putting the
	 *			components in the correct place.
	 */
	private void buildPanel()
	{	
		// Take care of the picture, we need to pull the unknown avatar and scale it to the needed size.
		ImageIcon ogUnknownPicture = new ImageIcon("src/images/unknownPicture.png");
		ImageIcon unknownPicture = new ImageIcon(ogUnknownPicture.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		
		// Handle the layout of the panel.
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		// Initalize the following for the main form.
		THCNumberField = new JTextField(10);
		currentDateField = new JTextField(10);
		firstNameField = new JTextField(10);
		middleNameField = new JTextField(10);
		lastNameField = new JTextField(10);
		dobField = new JPanel(new GridLayout(1, 3));
		monthField = new JTextField("MM");
		dayField = new JTextField("DD");
		yearField = new JTextField("YYYY");
		dobField.add(monthField);
		dobField.add(dayField);
		dobField.add(yearField);
		genderField = new JComboBox<String>(genderList);
		phoneField = new JTextField(10);
		emailField = new JTextField(10);
		streetField = new JTextField(10);
		cityField = new JComboBox<String>(cityList);
		stateField = new JComboBox<String>(stateList);
		zipField = new JTextField(10);
		countryField = new JComboBox<String>(countryList);
		photoField = new JButton(unknownPicture);
		ssnField = new JTextField(10);
		insuranceField = new JTextField(10);
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
		
		// Add a listener for the fields in the year to revert the box to empty once clicked.
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
		
		// Make the photo button look more like a picture, and not like a button.
		photoField.setBorderPainted(false);
		// Add the option of changing the picture once it is cicked.
		photoField.addActionListener(event -> changePicture(photoField));
		
		// Figure out what the next THC number is and then fill the THC field with it.
		// This field will be gray to stand for it not being editable.
		int currentTHC = getRowCount();
		THCNumberField.setText(Integer.toString(currentTHC + 1));
		THCNumberField.setEditable(false);
		THCNumberField.setBackground(Color.GRAY);
		
		// Find today's date.
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		LocalDate localDate = LocalDate.now();
		
		// Make it so you can't change today's date either.
		currentDateField.setEditable(false);
		currentDateField.setText(dtf.format(localDate));
		currentDateField.setBackground(Color.GRAY);
		
		// Add a listener for if the save button is clicked. Once clicked, submit the information provided.
		saveButton.addActionListener(e -> {
			try 
			{
				submitInformation();
			} 
			catch (SQLException ex) 
			{
				ex.printStackTrace();
			}
		});
		
		// Add action listeners for the cancel button and the demographics button as well.
		cancelButton.addActionListener(e -> rebuildPanel());
		// The demographics pane is already open, it just needs to be made visible.
		addDemoButton.addActionListener(e -> demographicsFrame.setVisible(true));
		
		// Save the picture locally, and set it to the thcnumber.png
		try 
		{
			Files.copy(new File("src/images/unknownPicture.png").toPath(), new File("src/images/" + THCNumberField.getText() + ".png").toPath(), StandardCopyOption.REPLACE_EXISTING);
		} 
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}

		// All of the following is to design the panel and put all the components in the correct place.
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 6;
		add(photoField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(THCNumberLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(THCNumberField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(currentDateLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(currentDateField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2; 
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(firstNameLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(firstNameField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(middleNameLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(middleNameField, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(lastNameLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(lastNameField, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 5;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(dobLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 5;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(dobField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(genderLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(genderField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 6;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(phoneLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 6;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(phoneField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(emailLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 7;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(emailField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 7;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(streetLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 7;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(streetField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(cityLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 8;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(cityField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 8;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(stateLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 8;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(stateField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(zipLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 9;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(zipField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 9;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(countryLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 9;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(countryField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(ssnLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 10;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(ssnField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 10;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(insuranceLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 10;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(insuranceField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 11;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(saveButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 11;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(addDemoButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 11;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(newVisitButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 11;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(cancelButton, c);
		
		// Build the demographics frame during this stage as well.
		buildDemoFrame();
	}
	
	/**
	 * @title	changePicture method
	 * @param	photoField - The button that houses the picture.
	 */
	private void changePicture(JButton photoField)
	{
		// Instantiate the file that will hold the picture.
		File inputPicture = null;
		
		// Create a file chooser to pull the picture when selected.
		JFileChooser jfc = new JFileChooser();
		// Make sure the file chosen is a picture.
		jfc.setFileFilter(new FileNameExtensionFilter("JPG and PNG", "jpg", "png"));
		int returnVal = jfc.showOpenDialog(null);
		
		// If the file is good, then get the file.
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
			inputPicture = jfc.getSelectedFile();
		}
		
		if(inputPicture != null)
		{
			try 
			{
				// Save the file locally under the patient's THC number.
				Files.copy(inputPicture.toPath(), new File("src/images/" + THCNumberField.getText() + ".png").toPath(), StandardCopyOption.REPLACE_EXISTING);
				
				// Scale the image
				ImageIcon ogInPic = new ImageIcon("src/images/inputPicture.png");
				ImageIcon inPic = new ImageIcon(ogInPic.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
				
				// Set the image.
				photoField.setIcon(inPic);
				photoField.repaint();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @title	errorCheck method
	 * @return	Boolean on whether or not there is an error.
	 * @desc	This method is called to check to see if all the needed fields are filled out in the form before firing
	 * 			to the database.
	 */
	private boolean errorCheck()
	{
		// Instantiate the needed variables.
		boolean isError = false;
		String errorLog = "";
		
		// All of the following checks to see if the field is empty.
		// If it is, add it to the list of errors.
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
		
		// If there is an error, alert the user to everywhere it went wrong.
		if(isError)
		{
			errorLog = errorLog.substring(0, errorLog.length() - 2) + ".";
			JOptionPane.showMessageDialog(null, "The following fields can not be empty: " + errorLog, "eTRT - Decision Support System for Tinnitus Restraining Therapy", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(AddNewPatientsPanel.class.getResource("/images/eTRT_icon.png")));
		}
		
		return isError;
	}
	 
	/**
	 * @title	submitInformation method
	 * @throws 	SQLException - If there is an error when submitting to the database.
	 */
	private void submitInformation() throws SQLException
	{
		// If there is an error, don't submit the information.
		if(errorCheck())
		{
			return;
		}
		
		// Find the current date again, to make it easier for data submission.
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();

		// Create the query for submitting all the information into the patients table.
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
		
		// Preform the query.
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.execute();
		
		// Rebuild the panel once it is submitted so it is blank for the next data entry.
		rebuildPanel();
		
		return;
	}
	
	/**
	 * @title	getRowCount Method
	 * @return	The highest thc number in the table.
	 */
	private int getRowCount()
	{
		// Start out at 0.
		int rowCount = 0;
		
		try 
		{
			// Ask the database for the highest THC number.
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery ("SELECT MAX(THCNumber) FROM Patients;");
			rset.next();
			rowCount = rset.getInt(1);
		} 
		catch (SQLException e) 
		{
			System.out.println("Couldn't get row count.");
			e.printStackTrace();
		}
		
		// Return the highest THC number added to the table.
		return rowCount;
	}
	
	/**
	 * @title	rebuildPanel Method
	 * @desc	Destroys the panel and then rebuilds it to clear all data.
	 */
	protected void rebuildPanel()
	{
		remove(THCNumberLabel);
		remove(THCNumberField);
		remove(currentDateLabel);
		remove(currentDateField);
		remove(firstNameLabel);
		remove(firstNameField);
		remove(middleNameLabel);
		remove(middleNameField);
		remove(lastNameLabel);
		remove(lastNameField);
		remove(dobLabel);
		remove(dobField);
		remove(genderLabel);
		remove(genderField);
		remove(phoneLabel);
		remove(phoneField);
		remove(emailLabel);
		remove(emailField);
		remove(streetLabel);
		remove(streetField);
		remove(cityLabel);
		remove(cityField);
		remove(stateLabel);
		remove(stateField);
		remove(zipLabel);
		remove(zipField);
		remove(countryLabel);
		remove(countryField);
		remove(photoLabel);
		remove(photoField);
		remove(ssnLabel);
		remove(ssnField);
		remove(insuranceLabel);
		remove(insuranceField);
		remove(blankSpace1);
		remove(blankSpace2);
		remove(saveButton);
		remove(addDemoButton);
		remove(newVisitButton);
		remove(cancelButton);
		demographicsFrame.dispose();
		buildPanel();
		repaint();
		revalidate();
	}
	
	/**
	 * @title	rebuildDemoFrame Method
	 * @desc	Destroys the demo frame and then rebuilds it to erase all inputted data.
	 */
	private void rebuildDemoFrame()
	{
		demographicsFrame.dispose();
		buildDemoFrame();
	}
	
	/**
	 * @title	buildDemoFrame Method
	 * @desc	Builds the demographic frame and makes it not visible.
	 */
	private void buildDemoFrame()
	{
		// Start with all the basic JFrame stuff.
		demographicsFrame = new JFrame("eTRT - Add Demographics");
		demographicsFrame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		// Initalize all of the components.
		occupationField = new JTextField(10);
		workStatusField = new JTextField(10);
		educationField = new JTextField(10);
		tOnsetField = new JTextField(10);
		tEtioField = new JTextField(10);
		hOnsetField = new JTextField(10);
		hEtioField = new JTextField(10);
		commentField = new JTextArea(4, 30);
		demographicsLabel = new JLabel("Demographics");
		occupationLabel = new JLabel("Occupation");
		workStatusLabel = new JLabel("Work Status");
		educationLabel = new JLabel("Educational Degree");
		tOnsetLabel = new JLabel("Tinnitus Onset");
		tEtioLabel = new JLabel("Tinnitus Etiology");
		hOnsetLabel = new JLabel("Hyperacusis Onset");
		hEtioLabel = new JLabel("Hyperacusis Etiology");
		commentLabel = new JLabel("Additional Comments");
		demoSaveButton = new JButton("Save");
		demoCancelButton = new JButton("Cancel");
		
		demographicsLabel.setHorizontalAlignment(JLabel.CENTER);
		commentField.setLineWrap(true);
		
		// Make sure that there is no more than 150 characters in the comment field.
		commentField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) 
			{
				if(commentField.getText().length() >= 150)
					e.consume();
			}
		});
		
		// Set listeners for the buttons at the bottom of he frame.
		demoSaveButton.addActionListener(e -> demographicsFrame.setVisible(false));
		demoCancelButton.addActionListener(e -> rebuildDemoFrame());
		
		
		// All of the following is for positioning the components correctly.
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		demographicsFrame.add(demographicsLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		demographicsFrame.add(occupationLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		demographicsFrame.add(occupationField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		demographicsFrame.add(workStatusLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		demographicsFrame.add(workStatusField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		demographicsFrame.add(educationLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		demographicsFrame.add(educationField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		demographicsFrame.add(tOnsetLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		demographicsFrame.add(tOnsetField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		demographicsFrame.add(tEtioLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		demographicsFrame.add(tEtioField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		demographicsFrame.add(hOnsetLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		demographicsFrame.add(hOnsetField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		demographicsFrame.add(hEtioLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		demographicsFrame.add(hEtioField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		c.gridheight = 1;
		demographicsFrame.add(commentLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 3;
		c.gridheight = 4;
		c.insets = new Insets(0, 5, 0, 0);
		demographicsFrame.add(commentField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 9;
		c.gridwidth = 1;
		c.gridheight = 1;
		demographicsFrame.add(demoCancelButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 9;
		c.gridwidth = 1;
		c.gridheight = 1;
		demographicsFrame.add(demoSaveButton, c);
		
		// Set the rest of the JFrame stuff.
		demographicsFrame.setSize(new Dimension(600, 250));
		demographicsFrame.setResizable(false);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		demographicsFrame.setLocation(d.width/2-demographicsFrame.getSize().width/2, d.height/2-demographicsFrame.getSize().height/2);
		demographicsFrame.setVisible(false);
	}
	
	/**
	 * @title	getSaveButton Method
	 * @return	JButton - The save button of the panel.
	 */
	protected JButton getSaveButton()
	{
		return saveButton;
	}
	
	/**
	 * @tite	getCancelButton Method
	 * @return	JButton - The cancel button of the panel.
	 */
	protected JButton getCancelButton()
	{
		return cancelButton;
	}
}
