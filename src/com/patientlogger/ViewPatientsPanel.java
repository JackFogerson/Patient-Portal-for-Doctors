package com.patientlogger;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * @title 	ViewPatientsPanel Class
 * @author 	Nick Fulton
 * @desc	Builds the panel to view patients.
 */
public class ViewPatientsPanel extends JPanel
{
	// Create all of the needed variables.
	private static final long serialVersionUID = 1L;

	Connection conn;
	
	JComboBox<String> searchCriteria;
	
	JButton viewPatientButton, editPatientButton, deletePatientButton, addNewVisitButton, showCurrentVisitButton,
			searchButton, refreshButton;
	
	JPanel patientsPaneButtons, patientsPanel;
	
	JScrollPane patientsScrollPane;
	
	JTextField searchBox;
	
	JTable patientTable;
	
	ArrayList<Patient> patients;
	
	final String[] searchOptions = {"Filter Options", "THC Number", "Name", "City"};
	
	/**
	 * @title	ViewPatientsPanel Constructor
	 * @param 	c - Connection to the database.
	 */
	public ViewPatientsPanel(Connection c)
	{
		this.conn = c;
		buildPanel();
	}

	/**
	 * @title	buildPanel Method
	 * @desc	Builds the ViewPatientsPanel
	 */
	private void buildPanel()
	{
		// Set the layout and create the constraints of the panel.
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		// Create all of the needed components of the panel.
		searchCriteria = new JComboBox<String>(searchOptions);
		viewPatientButton = new JButton("View");
		editPatientButton = new JButton("Edit");
		deletePatientButton = new JButton("Delete");
		addNewVisitButton = new JButton("Add Visit");
		showCurrentVisitButton = new JButton("Current Visit");
		searchButton = new JButton("Search");
		refreshButton = new JButton("Refresh");
		patientTable = new JTable();
		searchBox = new JTextField(10);
		
		// Add the ability to refresh the table.
		refreshButton.addActionListener(e -> {
			try 
			{
				populate();
			} 
			catch (SQLException e2) 
			{
				e2.printStackTrace();
			}
		});
		
		// Add the ability to search for a patient.
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
		
		// Add the ability to delete a patient.
		deletePatientButton.addActionListener(e -> {
			try 
			{
				delete();
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		});
		
		// Add the ability to edit a patient.
		editPatientButton.addActionListener(e -> edit());
		
		// Populate the patient table.
		try 
		{
			populate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		// Create the scroll pane.
		patientsScrollPane = new JScrollPane(patientTable);
		
		// The following is adding all of the components to the panel.
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		add(refreshButton, c);
		
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
	
	/**
	 * @title	search Method
	 * @throws 	SQLException - If the database has problems pulling the patients.
	 */
	private void search() throws SQLException
	{
		// If there isnt a search query just refresh the table.
		if(searchBox.getText().equals(""))
		{
			populate();
			return;
		}
		
		// Find the patients from the database from pullSomePatients (this method handles the search)
		patients = pullSomePatients();
		String[][] patientData = new String[patients.size()][7];
		
		// Assign the patient's data to the table's data.
		for(int x = 0; x < patients.size(); x++)
		{
			patientData[x] = patients.get(x).getPatientInfo();
		}
		
		// Set the table correctly.
		patientTable.setModel(new PatientTableModel(patientData));
		patientTable.setAutoCreateRowSorter(true);
		patientTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		patientTable.getColumnModel().getColumn(2).setPreferredWidth(30);
		patientTable.getColumnModel().getColumn(3).setPreferredWidth(50);
	}
	
	/**
	 * @title	delete Method
	 * @throws 	SQLException - If the database has problems deleting information.
	 */
	private void delete() throws SQLException
	{
		// Pull the THC number to be deleted.
		String THCNumber = (String)patientTable.getValueAt(patientTable.getSelectedRow(), 0);
		
		// Delete the patient from the table.
		PreparedStatement preparedStmt = conn.prepareStatement("DELETE FROM PATIENTS WHERE THCNumber ='" + THCNumber + "';");
		preparedStmt.execute();
		
		// Pull the new patient information.
		patients = pullAllPatients();
		String[][] patientData = new String[patients.size()][7];
		
		// Get the new patientdata after deleting.
		for(int x = 0; x < patients.size(); x++)
		{
			patientData[x] = patients.get(x).getPatientInfo();
		}
		
		// Redo the table after deletion.
		patientTable.setModel(new PatientTableModel(patientData));
		patientTable.setAutoCreateRowSorter(true);
		patientTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		patientTable.getColumnModel().getColumn(2).setPreferredWidth(30);
		patientTable.getColumnModel().getColumn(3).setPreferredWidth(50);
	}
	
	/**
	 * @title	edit Method
	 * @desc	Allows functionality to edit a patient through an external frame.
	 */
	private void edit()
	{
		// Declare the patient.
		Patient patient = null;
		
		// Get the patient that was selected.
		for(int x = 0; x < patients.size(); x++)
		{
			if((String)patientTable.getValueAt(patientTable.getSelectedRow(), 0) == patients.get(x).getTHCNumber())
			{
				patient = patients.get(x);
				break;
			}
		}
		
		// Build a edit patient screen.
		JFrame frame = new JFrame("eTRT - Edit Patient");
		frame.add(new EditPatientScreen(conn, patient));
		frame.setSize(new Dimension(600, 450));
		frame.setResizable(false);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(d.width/2-frame.getSize().width/2, d.height/2-frame.getSize().height/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	/**
	 * @title	populate Method
	 * @throws 	SQLException - If the database can't retreive information.
	 */
	private void populate() throws SQLException
	{
		// Get the patients from the database.
		patients = pullAllPatients();
		String[][] patientData = new String[patients.size()][7];
		
		// Assign all of the patient data to patientData
		for(int x = 0; x < patients.size(); x++)
		{
			patientData[x] = patients.get(x).getPatientInfo();
		}
		
		// Build the table.
		patientTable.setModel(new PatientTableModel(patientData));
		patientTable.setAutoCreateRowSorter(true);
		patientTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		patientTable.getColumnModel().getColumn(2).setPreferredWidth(30);
		patientTable.getColumnModel().getColumn(3).setPreferredWidth(50);
	}
	
	/**
	 * @title	pullAllPatients Method
	 * @return	Arraylist of patients from database
	 * @throws 	SQLException - If the database cant get the information
	 * @desc	Find all of the patients in the database.
	 */
	private ArrayList<Patient> pullAllPatients() throws SQLException
	{
		// Tell the database what you want.
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
	
	/**
	 * @title	pullSomePatients Method
	 * @return	The patients requested
	 * @throws 	SQLException - If the database can not return information.
	 * @desc	Pulls patients as needed for the search query.
	 */
	private ArrayList<Patient> pullSomePatients() throws SQLException
	{
		// Create the needed variables.
		Statement stmt = conn.createStatement();
		ArrayList<Patient> patients = new ArrayList<Patient>();
		ResultSet rset = null;
		
		// Figure out what information to pull, and go off of that (from the searchbox)
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
		
		// Pull the information.
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
		
		// Return the patients.
		return patients;
	}
}
