package com.patientlogger;

import java.awt.CardLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PatientsPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	JTabbedPane mainPane;
	
	public PatientsPanel()
	{
		setLayout(new CardLayout());
		buildPanel();
	}
	
	private void buildPanel()
	{
		mainPane = new JTabbedPane();
		
		AddNewPatientsPanel addNewPatientsPanel = new AddNewPatientsPanel();
		ViewPatientsPanel viewPatientsPanel = new ViewPatientsPanel();
		
		mainPane.addTab("Add New Patient", null, addNewPatientsPanel, "Add A New Patient");
		mainPane.addTab("View/Edit Patients", null, viewPatientsPanel, "View/Edit Patients");
		
		add(mainPane);
	}
}
