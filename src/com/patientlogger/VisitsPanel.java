package com.patientlogger;

import java.awt.CardLayout;
import java.sql.Connection;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class VisitsPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	Connection conn;
	JTabbedPane mainPane;
	
	public VisitsPanel(Connection c)
	{
		conn = c;
		setLayout(new CardLayout());
		buildPanel();
	}
	
	private void buildPanel()
	{
		mainPane = new JTabbedPane();
		
		AddNewVisitPanel addNewVisitPanel = new AddNewVisitPanel(conn);
		ViewVisitsPanel viewVisitsPanel = new ViewVisitsPanel(conn);
		
		mainPane.addTab("Add New Visit", null, addNewVisitPanel, "Add A New Visit");
		mainPane.addTab("View/Edit Visits", null, viewVisitsPanel, "View/Edit Visits");
		
		add(mainPane);
	}

}
