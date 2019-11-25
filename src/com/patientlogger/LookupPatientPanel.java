package com.patientlogger;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * @title	LookupPatientPanel
 * @author	Nick Fulton, Jack Fogerson
 * @desc	This class is an extension of JPanel which allows the user to lookup a patient in 
 * 			the database.
 */
public class LookupPatientPanel extends JPanel
{
	//Go through and instantiate all of the needed variables.
	private static final long serialVersionUID = 1L;
	Connection conn;
	
	//Field to choose how to lookup patients
	String[] searchOptions = {"Choose One", "Name", "THC Number", "SSN"};
	
	//Drop Down menu for search
	JComboBox<String> searchCriteria;
	
	//Field to insert search terms
	JTextField searchBox;
	
	//Initialize Search Button
	JButton searchButton;
	
	//Patient being looked up
	Patient myPatient;
	
	//text fields with patient data	
	JTextField THCNumberField, currentDateField, firstNameField, middleNameField, lastNameField, monthField,
	   		   dayField, yearField, phoneField, emailField, streetField, zipField, ssnField, insuranceField,
	   		   occupationField, workStatusField, educationField, tOnsetField, tEtioField, hOnsetField,
	   		   hEtioField, genderField, cityField, stateField, countryField;

	//Patient's comment field	
	JTextArea commentField;

	//Patient's photo
	JLabel photoField;

	//Used for fields with multiple entry boxes
	JPanel dobField, patientPanel;

	//labels to be used in lookup of patient data
	JLabel THCNumberLabel, currentDateLabel, firstNameLabel, middleNameLabel, lastNameLabel, dobLabel,
		   genderLabel, phoneLabel, emailLabel, streetLabel, cityLabel, stateLabel, zipLabel, countryLabel,
		   photoLabel, ssnLabel, insuranceLabel, blankSpace1, blankSpace2, demographicsLabel, occupationLabel,
		   workStatusLabel, educationLabel, tOnsetLabel, tEtioLabel, hOnsetLabel, hEtioLabel, commentLabel;
	
	//make pane scrollable
	JScrollPane patientScrollable;
	
	//Initialize all buttons
	JButton editPatientButton, addNewVisitButton, currentVisitButton;
	
	/**
	 * @title	LookupPatientPanel
	 * @desc	constructor, builds panel
	 * @param 	c - Is the connection to the database.
	 */
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
		photoLabel = new JLabel(unknownPicture);
		firstNameLabel = new JLabel("Unkown", SwingConstants.CENTER);
		lastNameLabel = new JLabel("Patient", SwingConstants.CENTER);
		THCNumberLabel = new JLabel("THC#: ", SwingConstants.CENTER);
		
		editPatientButton = new JButton("Edit Patient");
		addNewVisitButton = new JButton("New Visit");
		currentVisitButton = new JButton("Current Visit");
		
		patientPanel.setLayout(new GridBagLayout());
		buildPatientPanel();
		
		patientScrollable = new JScrollPane(patientPanel);
		patientScrollable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		searchButton.addActionListener(e -> {
			try 
			{
				search();
			} 
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
		});
		
		editPatientButton.addActionListener(e -> edit());
		
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
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		c.ipadx = 0;
		add(photoLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		c.ipadx = 0;
		add(firstNameLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		c.ipadx = 0;
		add(lastNameLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		c.ipadx = 0;
		add(THCNumberLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		c.ipadx = 0;
		add(editPatientButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		c.ipadx = 0;
		add(addNewVisitButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		c.ipadx = 0;
		add(currentVisitButton, c);
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
		
		currentDateField.setEditable(false);
		monthField.setEditable(false);
		dayField.setEditable(false);
		yearField.setEditable(false);
		genderField.setEditable(false);
		phoneField.setEditable(false);
		emailField.setEditable(false);
		streetField.setEditable(false);
		cityField.setEditable(false);
		stateField.setEditable(false);
		zipField.setEditable(false);
		countryField.setEditable(false);
		ssnField.setEditable(false);
		insuranceField.setEditable(false);	
		occupationField.setEditable(false);
		workStatusField.setEditable(false);
		educationField.setEditable(false);
		tOnsetField.setEditable(false);
		tEtioField.setEditable(false);
		hOnsetField.setEditable(false);
		hEtioField.setEditable(false);
		commentField.setEditable(false);
		
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
	
	private void search() throws SQLException
	{
		if(searchBox.getText().equals(""))
		{
			return;
		}
		
		Statement stmt = conn.createStatement();
		ResultSet rset = null;
		myPatient = null;
		
		switch((String)searchCriteria.getSelectedItem())
		{
			// Default will be for THC and select search
			default:
				rset = stmt.executeQuery("SELECT * FROM PATIENTS WHERE THCNumber ='" + searchBox.getText() + "';");
				break;
				
			case "Name":
				String[] name = searchBox.getText().split(" ");
				rset = stmt.executeQuery("SELECT * FROM PATIENTS WHERE FirstName = '" + name[0] + "' AND LastName = '" + name[1] + "';");
				break;
			
			case "SSN":
				rset = stmt.executeQuery("SELECT * FROM PATIENTS WHERE SSID = '" + searchBox.getText() + "';");
				break;
		}
		while(rset.next())
		{
			myPatient = new Patient(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), 
					rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), 
					rset.getString(9), rset.getString(10), rset.getString(11), rset.getString(12), 
				 	rset.getString(13), rset.getString(14), rset.getString(15), rset.getString(16), 
				 	rset.getString(17), rset.getString(18), rset.getString(19), rset.getString(20), 
				 	rset.getString(21), rset.getString(22), rset.getString(23), rset.getString(24), 
				 	rset.getString(25));
		}
		if(myPatient != null)
			assignPatient();
	}
	
	private void assignPatient()
	{
		// Set the picture to the patient's stored picture and scale it.
		ImageIcon ogUnknownPicture = new ImageIcon(myPatient.getPhoto());
		ImageIcon unknownPicture = new ImageIcon(ogUnknownPicture.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		
		// Set the data to all of the text fields.
		THCNumberLabel.setText("THC#: " + myPatient.getTHCNumber());
		currentDateField.setText(myPatient.getDate());
		firstNameLabel.setText(myPatient.getFirstName());
		lastNameLabel.setText(myPatient.getLastName());
		monthField.setText(myPatient.getDob().substring(5,7));
		dayField.setText(myPatient.getDob().substring(8,10));
		yearField.setText(myPatient.getDob().substring(0,4));
		genderField.setText(myPatient.getGender());
		phoneField.setText(myPatient.getPhone());
		emailField.setText(myPatient.getEmail());
		streetField.setText(myPatient.getStreetAddress());
		cityField.setText(myPatient.getCity());
		stateField.setText(myPatient.getState());
		zipField.setText(myPatient.getZip());
		countryField.setText(myPatient.getCountry());
		photoLabel.setIcon(unknownPicture);
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
	}
	
	private void edit()
	{
		if(myPatient == null)
			return;
		
		// Build a edit patient screen.
		JFrame frame = new JFrame("eTRT - Edit Patient");
		frame.add(new EditPatientScreen(conn, myPatient));
		frame.setSize(new Dimension(600, 450));
		frame.setResizable(false);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(d.width/2-frame.getSize().width/2, d.height/2-frame.getSize().height/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
