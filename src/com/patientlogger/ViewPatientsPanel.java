package com.patientlogger;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ViewPatientsPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	Connection conn;
	
	JComboBox<String> searchCriteria, sortCriteria;
	
	JButton viewPatientButton, editPatientButton, deletePatientButton, addNewVisitButton, showCurrentVisitButton;
	
	JPanel patientsPaneButtons, patientsPanel;
	
	JScrollPane patientsScrollPane;
	
	JTextField searchBox;
	
	JTable patientTable;
	
	final String[] searchOptions = {"Filter Options", "THC Number", "Name", "City"};
	final String[] sortOptions = {"Sort Options", "THC Number", "Name", "Age", "Gender", "City", "State", "Date Added"};
	final String[] columnNames = {"THC#", "Name", "Age", "Gender", "City", "State", "Date Added"};
	
	public ViewPatientsPanel(Connection c)
	{
		this.conn = c;
		buildPanel();
	}
	
	private void buildPanel()
	{
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		searchCriteria = new JComboBox<String>(searchOptions);
		sortCriteria = new JComboBox<String>(sortOptions);
		viewPatientButton = new JButton("View");
		editPatientButton = new JButton("Edit");
		deletePatientButton = new JButton("Delete");
		addNewVisitButton = new JButton("Add Visit");
		showCurrentVisitButton = new JButton("Current Visit");
		
		searchBox = new JTextField(10);
		
		try 
		{
			populate((String)sortCriteria.getSelectedItem());
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		patientsScrollPane = new JScrollPane(patientTable);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.ipady = 0;
		add(sortCriteria, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		add(searchCriteria, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		add(searchBox, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 5;
		c.gridheight = 20;
		c.ipady = 250;
		add(patientsScrollPane, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 21;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		add(viewPatientButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 21;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		add(editPatientButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 21;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		add(deletePatientButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 21;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		add(addNewVisitButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 21;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		add(showCurrentVisitButton ,c);
	}
	
	private void populate(String sortOption) throws SQLException
	{
		ArrayList<Patient> patients = pullAllPatients();
		String[][] patientData = new String[patients.size()][7];
		patientTable = new JTable(patientData, columnNames);
		
		for(int x = 1; x < patients.size(); x++)
		{
			patientData[x] = patients.get(x).getPatientInfo();
		}
		
		patientTable = new JTable(patientData, columnNames);
	}
	
	private ArrayList<Patient> pullAllPatients() throws SQLException
	{
	    Statement stmt = conn.createStatement ();
	    ResultSet rset = stmt.executeQuery ("SELECT * FROM PATIENTS;");
	    ArrayList<Patient> patients = new ArrayList<Patient>();

	    // Iterate through the result and print out the table names
	    while (rset.next())
	    {
	    	patients.add(new Patient(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), 
	    									 rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), 
	    									 rset.getString(9), rset.getString(10), rset.getString(11), rset.getString(12), 
	    									 rset.getString(13), rset.getString(14), rset.getString(15), rset.getString(16), 
	    									 rset.getString(17), rset.getString(18), rset.getString(19), rset.getString(20), 
	    									 rset.getString(21), rset.getString(22), rset.getString(23), rset.getString(24), 
	    									 rset.getString(25)));
	    }
		return patients;
	}
}













