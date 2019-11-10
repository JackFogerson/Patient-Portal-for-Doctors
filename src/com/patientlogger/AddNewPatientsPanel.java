package com.patientlogger;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
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
	
	final String[] countryList = {"Select One"};
			
	public AddNewPatientsPanel()
	{
		setLayout(new GridLayout(10, 4));
		buildPanel();
	}
	
	private void buildPanel()
	{
		ImageIcon ogUnknownPicture = new ImageIcon("src/images/unknownPicture.png");
		ImageIcon unknownPicture = new ImageIcon(ogUnknownPicture.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		
		JTextField THCNumberField = new JTextField();
		JTextField currentDateField = new JTextField();
		JTextField firstNameField = new JTextField();
		JTextField middleNameField = new JTextField();
		JTextField lastNameField = new JTextField();
		JTextField dobField = new JTextField();
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
		
		photoField.setBorderPainted(false);
		photoField.addActionListener(event -> changePicture(photoField));
		
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
				ImageIcon inPic = new ImageIcon(ogInPic.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
				
				photoField.setIcon(inPic);
				photoField.repaint();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
