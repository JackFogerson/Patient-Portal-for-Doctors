package com.patientlogger;

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
	
	JComboBox<String> searchCriteria;
	
	JButton viewPatientButton, editPatientButton, deletePatientButton, addNewVisitButton, showCurrentVisitButton, searchButton;
	
	JPanel patientsPaneButtons, patientsPanel;
	
	JScrollPane patientsScrollPane;
	
	JTextField searchBox;
	
	JTable patientTable;
	
	final String[] searchOptions = {"Filter Options", "THC Number", "Name", "City"};
	
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
		viewPatientButton = new JButton("View");
		editPatientButton = new JButton("Edit");
		deletePatientButton = new JButton("Delete");
		addNewVisitButton = new JButton("Add Visit");
		showCurrentVisitButton = new JButton("Current Visit");
		searchButton = new JButton("Search");
		
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
		
		searchBox = new JTextField(10);
		
		try 
		{
			populate((String)searchCriteria.getSelectedItem());
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		patientsScrollPane = new JScrollPane(patientTable);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		add(searchCriteria, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		add(searchBox, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		add(searchButton, c);
		
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
	
	private void search() throws SQLException
	{
		if(searchBox.getText().equals(""))
		{
			return;
		}
		
		ArrayList<Patient> patients = pullSomePatients();
		String[][] patientData = new String[patients.size()][7];
		
		for(int x = 0; x < patients.size(); x++)
		{
			patientData[x] = patients.get(x).getPatientInfo();
		}
		
		patientTable.setModel(new PatientTableModel(patientData));
	}
	
	private void populate(String sortOption) throws SQLException
	{
		ArrayList<Patient> patients = pullAllPatients();
		String[][] patientData = new String[patients.size()][7];
		
		for(int x = 1; x < patients.size(); x++)
		{
			patientData[x-1] = patients.get(x).getPatientInfo();
		}
		
		patientTable = new JTable(new PatientTableModel(patientData));
		patientTable.setAutoCreateRowSorter(true);
		patientTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		patientTable.getColumnModel().getColumn(2).setPreferredWidth(30);
		patientTable.getColumnModel().getColumn(3).setPreferredWidth(50);
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
	
	private ArrayList<Patient> pullSomePatients() throws SQLException
	{
		Statement stmt = conn.createStatement();
		ArrayList<Patient> patients = new ArrayList<Patient>();
		ResultSet rset = null;
		
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
			
			case "City":
				rset = stmt.executeQuery("SELECT * FROM PATIENTS WHERE City = '" + searchBox.getText() + "';");
				break;
		}
		
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













