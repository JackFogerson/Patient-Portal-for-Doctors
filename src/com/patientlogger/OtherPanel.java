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
public class OtherPanel extends JPanel
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
	public OtherPanel(Connection c)
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
		LocationDataPanel locationDataPanel = new LocationDataPanel(conn);
		DemographicsDataPanel demographicsDataPanel = new DemographicsDataPanel(conn);
		
		// Add the panels to the pane.
		mainPane.addTab("Location Data", null, locationDataPanel, "Add Location Data");
		mainPane.addTab("Demographics Data", null, demographicsDataPanel, "Add Demographics Data");
		
		// Add the main pane to the patients panel.
		add(mainPane);
	}
}
