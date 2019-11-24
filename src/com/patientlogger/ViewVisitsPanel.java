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
 * @title 	ViewVisitsPanel Class
 * @author 	Nick Fulton
 * @desc	Builds the panel to view patients.
 */
public class ViewVisitsPanel extends JPanel
{
	// Create all of the needed variables.
	private static final long serialVersionUID = 1L;

	Connection conn;
	
	JComboBox<String> searchCriteria;
	
	JButton viewVisitButton, editVisitButton, deleteVisitButton, analyzeVisitButton,
			searchButton, refreshButton;
	
	JPanel visitsPaneButtons, visitsPanel;
	
	JScrollPane visitsScrollPane;
	
	JTextField searchBox;
	
	JTable visitTable;
	
	ArrayList<Visit> visits;
	
	final String[] searchOptions = {"Filter Options", "THC Number", "Name", "City"};
	
	/**
	 * @title	ViewVisitsPanel Constructor
	 * @param 	c - Connection to the database.
	 */
	public ViewVisitsPanel(Connection c)
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
		viewVisitButton = new JButton("View");
		editVisitButton = new JButton("Edit");
		deleteVisitButton = new JButton("Delete");
		analyzeVisitButton = new JButton("Analyze Visit");
		searchButton = new JButton("Search");
		refreshButton = new JButton("Refresh");
		visitTable = new JTable();
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
		deleteVisitButton.addActionListener(e -> {
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
		editVisitButton.addActionListener(e -> edit());
		
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
		visitsScrollPane = new JScrollPane(visitTable);
		
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
		add(visitsScrollPane, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 21;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		add(viewVisitButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 21;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		add(editVisitButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 21;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		add(deleteVisitButton, c);
		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 21;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		add(analyzeVisitButton, c);
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
		visits = pullSomeVisits();
		String[][] visitData = new String[visits.size()][11];
		
		// Assign the patient's data to the table's data.
		for(int x = 0; x < visits.size(); x++)
		{
			visitData[x] = visits.get(x).getVisitInfo();
		}
		
		// Set the table correctly.
		visitTable.setModel(new PatientTableModel(visitData));
		visitTable.setAutoCreateRowSorter(true);
		//visitTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		//visitTable.getColumnModel().getColumn(2).setPreferredWidth(30);
		//visitTable.getColumnModel().getColumn(3).setPreferredWidth(50);
	}
	
	/**
	 * @title	delete Method
	 * @throws 	SQLException - If the database has problems deleting information.
	 */
	private void delete() throws SQLException
	{
		// Pull the THC number to be deleted.
		String IDNumber = (String)visitTable.getValueAt(visitTable.getSelectedRow(), 0);
		
		// Delete the patient from the table.
		PreparedStatement preparedStmt = conn.prepareStatement("DELETE FROM Visits WHERE ID ='" + IDNumber + "';");
		preparedStmt.execute();
		
		// Pull the new patient information.
		visits = pullAllVisits();
		String[][] visitData = new String[visits.size()][7];
		
		// Get the new patientdata after deleting.
		for(int x = 0; x < visits.size(); x++)
		{
			visitData[x] = visits.get(x).getVisitInfo();
		}
		
		// Redo the table after deletion.
		visitTable.setModel(new PatientTableModel(visitData));
		visitTable.setAutoCreateRowSorter(true);
		//visitTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		//visitTable.getColumnModel().getColumn(2).setPreferredWidth(30);
		//visitTable.getColumnModel().getColumn(3).setPreferredWidth(50);
	}
	
	/**
	 * @title	edit Method
	 * @desc	Allows functionality to edit a patient through an external frame.
	 */
	private void edit()
	{
		// Declare the patient.
		Visit visit = null;
		
		// Get the patient that was selected.
		for(int x = 0; x < visits.size(); x++)
		{
			if((String)visitTable.getValueAt(visitTable.getSelectedRow(), 0) == visits.get(x).getVisitID())
			{
				visit = visits.get(x);
				break;
			}
		}
		
		// Build a edit patient screen.
		JFrame frame = new JFrame("eTRT - Edit Patient");
		frame.add(new EditVisitScreen(conn, visit));
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
		visits = pullAllVisits();
		String[][] visitData = new String[visits.size()][11];
		
		// Assign all of the patient data to patientData
		for(int x = 0; x < visits.size(); x++)
		{
			visitData[x] = visits.get(x).getVisitInfo();
		}
		
		// Build the table.
		visitTable.setModel(new VisitDataModel(visitData));
		visitTable.setAutoCreateRowSorter(true);
		//visitTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		//visitTable.getColumnModel().getColumn(2).setPreferredWidth(30);
		//visitTable.getColumnModel().getColumn(3).setPreferredWidth(50);
	}
	
	/**
	 * @title	pullAllPatients Method
	 * @return	Arraylist of patients from database
	 * @throws 	SQLException - If the database cant get the information
	 * @desc	Find all of the patients in the database.
	 */
	private ArrayList<Visit> pullAllVisits() throws SQLException
	{
		// Tell the database what you want.
	    Statement stmt = conn.createStatement ();
	    ResultSet rset = stmt.executeQuery ("SELECT * FROM Visits;");
	    ArrayList<Visit> visits = new ArrayList<Visit>();

	    // Iterate through the result and print out the table names
	    while (rset.next())
	    {
	    	visits.add(new Visit(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4),
	    						 rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), 
	    						 rset.getString(9), rset.getString(10), rset.getString(11), rset.getString(12)));
	    }
		return visits;
	}
	
	/**
	 * @title	pullSomePatients Method
	 * @return	The patients requested
	 * @throws 	SQLException - If the database can not return information.
	 * @desc	Pulls patients as needed for the search query.
	 */
	private ArrayList<Visit> pullSomeVisits() throws SQLException
	{
		// Create the needed variables.
		Statement stmt = conn.createStatement();
		ArrayList<Visit> visits = new ArrayList<Visit>();
		ResultSet rset = null;
		
		// Figure out what information to pull, and go off of that (from the searchbox)
		switch((String)searchCriteria.getSelectedItem())
		{
			// Name and default
			default:
				String[] name = searchBox.getText().split(" ");
				rset = stmt.executeQuery("SELECT * FROM VISITS WHERE FirstName = '" + name[0] + "' AND LastName = '" + name[1] + "';");
				break;
			
			case "Date":
				//rset = stmt.executeQuery("SELECT * FROM PATIENTS WHERE City = '" + searchBox.getText() + "';");
				// TODO ADD A DATE SEARCH
				break;
		}
		
		// Pull the information.
		while (rset.next())
		{
	    	visits.add(new Visit(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4),
	    						 rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), 
	    						 rset.getString(9), rset.getString(10), rset.getString(11), rset.getString(12)));
	    }
		
		// Return the patients.
		return visits;
	}
}
