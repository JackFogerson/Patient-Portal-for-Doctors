package com.patientlogger;

import java.awt.GridBagLayout;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LookupPatientPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	Connection conn;
	
	String[] searchOptions = {"Choose One", "Name", "THC Number", "SSN"};
	JComboBox<String> searchCriteria;
	JTextField searchBox;
	JButton searchButton;
	JLabel selectedLabel;
	Patient selectedPatient;
	JPanel selectedPatientPanel;
	JLabel picture, name, thc, ssn;
	
	public LookupPatientPanel(Connection c)
	{
		conn = c;
		setLayout(new GridBagLayout());
		buildPanel();
	}
	
	private void buildPanel()
	{
		searchCriteria = new JComboBox<String>(searchOptions);
		searchBox = new JTextField(10);
		searchButton = new JButton();
		
		selectedPatientPanel = new JPanel();
		selectedLabel = new JLabel("Selected Patient");
		
		getPatient();
		
		
	}
	
	private void getPatient()
	{
		
	}
}
