package com.patientlogger;

import java.awt.CardLayout;
import java.sql.Connection;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * @title	VisitsPanel Class
 * @author	Nick Fulton
 * @desc	Builds the VisitsPanel
 */
public class VisitsPanel extends JPanel
{
	// Declare the initial variables.
	private static final long serialVersionUID = 1L;
	Connection conn;
	JTabbedPane mainPane;
	
	/**
	 * @title	VisitsPanel Constructor
	 * @param 	c - The connection to the database.
	 */
	public VisitsPanel(Connection c)
	{
		conn = c;
		setLayout(new CardLayout());
		buildPanel();
	}
	
	/**
	 * @title	buildPanel Method
	 * @desc	Builds the VisitsPanel
	 */
	private void buildPanel()
	{
		// Create the main pane.
		mainPane = new JTabbedPane();
		
		// Create the needed tabs.
		AddNewVisitPanel addNewVisitPanel = new AddNewVisitPanel(conn);
		ViewVisitsPanel viewVisitsPanel = new ViewVisitsPanel(conn);
		
		// Add the tabs to the pane.
		mainPane.addTab("Add New Visit", null, addNewVisitPanel, "Add A New Visit");
		mainPane.addTab("View/Edit Visits", null, viewVisitsPanel, "View/Edit Visits");
		
		//Add the pane to the viists panel.
		add(mainPane);
	}

}
