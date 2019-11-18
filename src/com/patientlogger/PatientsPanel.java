package com.patientlogger;

import java.awt.CardLayout;
import java.sql.Connection;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * @title	PatientsPanel Class
 * @author 	Nick Fulton
 * @desc	Builds the patients panel.
 */
public class PatientsPanel extends JPanel
{
	// Declare all of the needed variables.
	private static final long serialVersionUID = 1L;
	JTabbedPane mainPane;
	Connection conn;
	
	/**
	 * @title	PatientsPanel Method
	 * @param 	c - Connection to the database.
	 * @desc	Sets the connection then builds the panel.
	 */
	public PatientsPanel(Connection c)
	{
		this.conn = c;
		setLayout(new CardLayout());
		buildPanel();
	}
	
	/**
	 * @title	buildPanel Method
	 * @desc	Builds the patients panel.
	 */
	private void buildPanel()
	{
		// Create the tabbed pane.
		mainPane = new JTabbedPane();
		
		// Create the needed panels.
		AddNewPatientsPanel addNewPatientsPanel = new AddNewPatientsPanel(conn);
		ViewPatientsPanel viewPatientsPanel = new ViewPatientsPanel(conn);
		LookupPatientPanel lookupPatientsPanel = new LookupPatientPanel(conn);
		
		// Add the panels to the pane.
		mainPane.addTab("Add New Patient", null, addNewPatientsPanel, "Add A New Patient");
		mainPane.addTab("View/Edit Patients", null, viewPatientsPanel, "View/Edit Patients");
		mainPane.addTab("Lookup Patients", null, lookupPatientsPanel, "Lookup Patients");
		
		// Add the main pane to the patients panel.
		add(mainPane);
	}
}
