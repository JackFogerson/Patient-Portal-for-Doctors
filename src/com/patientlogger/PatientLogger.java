package com.patientlogger;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
		mainFrame.setLayout(new BorderLayout());
		
		JLabel topButtons = new JLabel();
		topButtons.setLayout(new FlowLayout());
		
		JButton initialButton = new JButton("Initial Appointment");
		JButton	followUpButton = new JButton("Follow Up Appointment");
		
		topButtons.add(initialButton);
		topButtons.add(followUpButton);
		
		mainFrame.add(topButtons);
		
		mainFrame.setMinimumSize(new Dimension(600, 550));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		mainFrame.setVisible(true);
	} 
}
