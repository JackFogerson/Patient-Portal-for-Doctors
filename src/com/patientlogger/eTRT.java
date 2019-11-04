package com.patientlogger;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class eTRT 
{
	JFrame mainFrame;
	
	public eTRT()
	{
		
	}
	
	public void launch()
	{
		buildFrames();
	}
	
	private void buildFrames()
	{
		mainFrame = new JFrame("eTRT - Decision Support System for Tinnitus Restraining Therapy");
		mainFrame.setLayout(new CardLayout());
		
		try
		{
			mainFrame.setIconImage(ImageIO.read(getClass().getResourceAsStream("/eTRT_icon.png")));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		JTabbedPane mainPane = new JTabbedPane();
		
		HomePanel homePanel = new HomePanel();
		PatientsPanel patientsPanel = new PatientsPanel();
		VisitsPanel visitsPanel = new VisitsPanel();
		OtherPanel otherPanel = new OtherPanel();
		
		mainPane.addTab("Home", null, homePanel, "Spash Screen");
		mainPane.addTab("Patients", null, patientsPanel, "Information for Patients");
		mainPane.addTab("Visits", null, visitsPanel, "Information for Visits");
		mainPane.addTab("Other", null, otherPanel, "Other");
		
		mainFrame.add(mainPane);
		
		mainFrame.setMinimumSize(new Dimension(600, 550));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setVisible(true);
	} 
}
