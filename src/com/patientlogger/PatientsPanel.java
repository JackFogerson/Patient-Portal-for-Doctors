package com.patientlogger;

import java.awt.CardLayout;
import java.sql.Connection;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PatientsPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	JTabbedPane mainPane;
	Connection conn;
	
	public PatientsPanel(Connection c)
	{
		this.conn = c;
		setLayout(new CardLayout());
		buildPanel();
	}
	
	private void buildPanel()
	{
		mainPane = new JTabbedPane();
		
		AddNewPatientsPanel addNewPatientsPanel = new AddNewPatientsPanel(conn);
		ViewPatientsPanel viewPatientsPanel = new ViewPatientsPanel(conn);
		
		mainPane.addTab("Add New Patient", null, addNewPatientsPanel, "Add A New Patient");
		mainPane.addTab("View/Edit Patients", null, viewPatientsPanel, "View/Edit Patients");
		
		add(mainPane);
	}
}
