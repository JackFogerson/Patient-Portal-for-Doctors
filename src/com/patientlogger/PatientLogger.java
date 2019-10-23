package com.patientlogger;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PatientLogger 
{
	JFrame mainFrame;
	
	public PatientLogger()
	{
		
	}
	
	public void launch()
	{
		buildFrames();
	}
	
	private void buildFrames()
	{
		
		mainFrame = new JFrame("Patient Logger");
		mainFrame.setLayout(new CardLayout());
		
		JTabbedPane informationPane = new JTabbedPane();
		
		JPanel newPatientPanel = new JPanel();
		JPanel returningPatientPanel = new JPanel();
		
		informationPane.addTab("New Patient", null, newPatientPanel, "Information Pane for New Patients");
		informationPane.addTab("Returning Patient", null, returningPatientPanel, "Information Pane for Returning Patients");
		
		newPatientPanel.setBackground(Color.RED);
		returningPatientPanel.setBackground(Color.GREEN);
		
		mainFrame.add(informationPane);
		
		mainFrame.setMinimumSize(new Dimension(600, 550));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setVisible(true);
	} 
}
