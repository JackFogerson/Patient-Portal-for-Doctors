package com.patientlogger;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 * @title	eTRT Class
 * @author	Nick Fulton
 * @desc	This is the main application. It is responsible for holding all tabbed panes.
 */
public class eTRT 
{
	// Create the frame and connection.
	JFrame mainFrame;
	Connection conn;
	
	public eTRT()
	{
		try
		{
			// Establish the connection to the database.
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			conn = DriverManager.getConnection("jdbc:mysql://localhost/eTRTSchema?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","User1", "SJSUcs157");
		}
		catch(SQLException e)
		{
			// Error out if the connection can not be established.
			e.printStackTrace();
		}
	}
	
	/**
	 * @title	launch method
	 * @desc	Creates the program.
	 */
	public void launch()
	{
		buildFrames();
	}
	
	/**
	 * @title	buildFrames method
	 * @desc	Builds everything inside of the application.
	 */
	private void buildFrames()
	{
		// Create the frame and set the layout.
		mainFrame = new JFrame("eTRT - Decision Support System for Tinnitus Restraining Therapy");
		mainFrame.setLayout(new CardLayout());
		
		try
		{
			// Set the icon of the application to the eTRT logo.
			mainFrame.setIconImage(ImageIO.read(getClass().getResourceAsStream("/images/eTRT_icon.png")));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		// Create the tabbed pane of the application.
		JTabbedPane mainPane = new JTabbedPane();
		
		// Create all of the panels to be put into the tabbed pane.
		HomePanel homePanel = new HomePanel();
		PatientsPanel patientsPanel = new PatientsPanel(conn);
		VisitsPanel visitsPanel = new VisitsPanel(conn);
		
		// Add the tabs to the tabbed pane.
		mainPane.addTab("Home", null, homePanel, "Spash Screen");
		mainPane.addTab("Patients", null, patientsPanel, "Information for Patients");
		mainPane.addTab("Visits", null, visitsPanel, "Information for Visits");
		
		// Add the tabbed pane to the main frame.
		mainFrame.add(mainPane);
		
		// Finish off the JFrame stuff.
		mainFrame.setSize(new Dimension(600, 450));
		mainFrame.setResizable(false);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		mainFrame.setLocation(d.width/2-mainFrame.getSize().width/2, d.height/2-mainFrame.getSize().height/2);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	} 
}
