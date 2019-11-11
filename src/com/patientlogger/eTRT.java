package com.patientlogger;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class eTRT 
{
	JFrame mainFrame;
	Connection conn;
	
	public eTRT()
	{
		try
		{
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			conn = DriverManager.getConnection("jdbc:mysql://localhost/eTRTSchema?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","User1", "SJSUcs157");
		
			/*
			String query = "INSERT INTO Patients(THCNumber, Date, FirstName, LastName, DOB, Gender, Phone, StreetAddress, City, Zip, Country) "
											  + "VALUES(0, '2019-11-10', 'Test', 'Test', '2000-01-01', 'Male', '5555555555', '123 Test', 'Austin', '95112', 'United States of America')";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.execute();
			*/
		}
		catch(SQLException e)
		{
			System.out.println("Error connecting to database.");
			e.printStackTrace();
		}
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
			mainFrame.setIconImage(ImageIO.read(getClass().getResourceAsStream("/images/eTRT_icon.png")));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		JTabbedPane mainPane = new JTabbedPane();
		
		HomePanel homePanel = new HomePanel();
		PatientsPanel patientsPanel = new PatientsPanel(conn);
		VisitsPanel visitsPanel = new VisitsPanel();
		
		mainPane.addTab("Home", null, homePanel, "Spash Screen");
		mainPane.addTab("Patients", null, patientsPanel, "Information for Patients");
		mainPane.addTab("Visits", null, visitsPanel, "Information for Visits");
		
		mainFrame.add(mainPane);
		
		mainFrame.setSize(new Dimension(600, 550));
		mainFrame.setResizable(false);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		mainFrame.setLocation(d.width/2-mainFrame.getSize().width/2, d.height/2-mainFrame.getSize().height/2);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	} 
}
