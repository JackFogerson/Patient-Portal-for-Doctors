package com.patientlogger;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddNewVisitPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	Connection conn;
	
	JLabel visitIDLabel, visitDateLabel, thcLabel, nameLabel, visitSequenceLabel,
		   problemRankLabel, categoryLabel, protocolLabel, instrumentLabel, remLabel,
		   fuLabel, commentsLabel, nextVisitLabel;
	
	JTextField visitIDField, visitDateField, thcField, nameField, visitSequenceField,
			   nextVisitField;
	
	JComboBox<String> problemRankField, categoryField, protocolField, instrumentField, fuField;
	
	JCheckBox remField;
	
	JTextArea commentField;
	
	JButton thcLookupButton, addInterviewButton, addAudioButton, addPharmaButton,
			diagnoseButton, addInstrumentButton, addREMDetailsButton, addCounselingButton,
			recommendTreatmentButton, saveButton, cancelButton;
	
	final String[] problemRanks = {" ", "THL", "T", "HT"};
	final String[] categories = {" ", "1", "2", "3", "4"};
	final String[] instruments = {" ", "V", "GS", "GH", "HA"};
	final String[] fu = {" ", "A", "C", "T", "E"};
	
	public AddNewVisitPanel(Connection c)
	{
		conn = c;
		setLayout(new GridBagLayout());
		buildPanel();
	}
	
	private void buildPanel()
	{
		visitIDLabel = new JLabel("Visit ID");
		visitDateLabel = new JLabel("Date");
		thcLabel = new JLabel("THC#");
		nameLabel = new JLabel("Patient");
		visitSequenceLabel = new JLabel("Visit #");
		problemRankLabel = new JLabel("Problem");
		categoryLabel = new JLabel("Category");
		protocolLabel = new JLabel("Protocol");
		fuLabel = new JLabel("FU");
		instrumentLabel = new JLabel("Instrument");
		remLabel  = new JLabel("REM");
		commentsLabel = new JLabel("Comments");
		nextVisitLabel = new JLabel("Next Visit");
		
		visitIDField = new JTextField();
		visitDateField = new JTextField();
		nameField = new JTextField();
		thcField = new JTextField();
		visitSequenceField = new JTextField();
		problemRankField = new JComboBox<String>(problemRanks);
		categoryField = new JComboBox<String>(categories);
		protocolField = new JComboBox<String>(categories);
		fuField = new JComboBox<String>(fu);
		instrumentField = new JComboBox<String>(instruments);
		nextVisitField = new JTextField();
		
		remField = new JCheckBox();
		commentField = new JTextArea(4, 30);
		
		addInterviewButton = new JButton("Interview");
		addAudioButton = new JButton("Audiology");
		addPharmaButton = new JButton("Medical Other");
		diagnoseButton = new JButton("Diagnose");
		
		addInstrumentButton = new JButton("<html><p align =\"center\">Instrument<br>Details</p></html>");
		addInstrumentButton.setPreferredSize(new Dimension(50, 60));
		addREMDetailsButton = new JButton("<html><p align =\\\"center\\\">REM<br>Details</p><</html>");
		addREMDetailsButton.setPreferredSize(new Dimension(50, 60));
		addCounselingButton = new JButton("<html><p align =\\\"center\\\">Counseling<br>Details</p><</html>");
		addCounselingButton.setPreferredSize(new Dimension(50, 60));
		recommendTreatmentButton = new JButton("<html><p align =\\\"center\\\">Recommend<br>Treatment</p><</html>");
		recommendTreatmentButton.setPreferredSize(new Dimension(50, 60));
		
		saveButton = new JButton("Save");
		cancelButton = new JButton("Cancel");
		
		GridBagConstraints c = new GridBagConstraints();
		
		visitIDField.setText(Integer.toString(getRowCount() + 1));
		

		// Find today's date.
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		LocalDate localDate = LocalDate.now();
		
		visitIDField.setEditable(false);
		visitIDField.setBackground(Color.GRAY);
		
		nameField.setEditable(false);
		nameField.setBackground(Color.GRAY);
		
		// Make it so you can't change today's date either.
		visitDateField.setEditable(false);
		visitDateField.setText(dtf.format(localDate));
		visitDateField.setBackground(Color.GRAY);
		
		thcField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
            	nameField.setText(getName());
            	visitSequenceField.setText(Integer.toString(getVisitSequence()));
        }});
		
		cancelButton.addActionListener(e -> rebuildPanel());
		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(visitIDLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(visitIDField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(visitDateLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 5;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		add(visitDateField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		add(nameLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 3;
		add(nameField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		add(thcLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 5;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		add(thcField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 6;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		add(visitSequenceLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 7;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		add(visitSequenceField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.gridheight = 1;
		add(addInterviewButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 2;
		c.gridheight = 1;
		add(addAudioButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 2;
		c.gridwidth = 2;
		c.gridheight = 1;
		add(addPharmaButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 6;
		c.gridy = 2;
		c.gridwidth = 2;
		c.gridheight = 1;
		add(diagnoseButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(problemRankLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(problemRankField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(categoryLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(categoryField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 5;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(protocolLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 6;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(protocolField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(fuLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(fuField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(instrumentLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(instrumentField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 5;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(remLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 6;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(remField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		c.gridheight = 1;
		add(commentsLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 7;
		c.gridheight = 2;
		add(commentField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 7;
		c.gridheight = 1;
		c.gridwidth = 1;
		add(nextVisitLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 7;
		c.gridheight = 1;
		c.gridwidth = 1;
		add(nextVisitField, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 8;
		c.gridheight = 2;
		c.gridwidth = 2;
		add(addInstrumentButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 8;
		c.gridheight = 2;
		c.gridwidth = 2;
		add(addREMDetailsButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 8;
		c.gridheight = 2;
		c.gridwidth = 2;
		add(addCounselingButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 6;
		c.gridy = 8;
		c.gridheight = 2;
		c.gridwidth = 2;
		add(recommendTreatmentButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 6;
		c.gridy = 10;
		c.gridheight = 1;
		c.gridwidth = 1;
		add(saveButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 7;
		c.gridy = 10;
		c.gridheight = 1;
		c.gridwidth = 1;
		add(cancelButton, c);
	}
	
	public String getName()
	{
		String name = null;
		
		try 
		{
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT FirstName, LastName FROM Patients WHERE THCNumber = '" + thcField.getText() + "';");
			
			while(rset.next())
			{
				name = rset.getString(1) + " " + rset.getString(2);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return name;
	}
	
	/**
	 * @title	getRowCount Method
	 * @return	The highest thc number in the table.
	 */
	private int getRowCount()
	{
		// Start out at 0.
		int rowCount = 0;
		
		try 
		{
			// Ask the database for the highest THC number.
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery ("SELECT MAX(VisitID) FROM Visits;");
			rset.next();
			rowCount = rset.getInt(1);
		} 
		catch (SQLException e) 
		{
			System.out.println("Couldn't get row count.");
			e.printStackTrace();
		}
		
		// Return the highest THC number added to the table.
		return rowCount;
	}
	
	private int getVisitSequence()
	{
		int visitSequence = 0;
		try 
		{
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT MAX(VisitSequence) FROM Visits WHERE THCNumber = '" + thcField.getText() + "';");
		
			while(rset.next())
			{
				visitSequence = Integer.parseInt(rset.getString(1)) + 1;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		catch(NumberFormatException e)
		{
			// DO nothing because this means there are no visits for this THC number.
		}
		
		return visitSequence;
	}
	
	private void rebuildPanel()
	{
		remove(visitIDLabel);
		remove(visitDateLabel);
		remove(thcLabel);
		remove(nameLabel);
		remove(visitSequenceLabel);
		remove(problemRankLabel);
		remove(categoryLabel);
		remove(protocolLabel);
		remove(fuLabel);
		remove(instrumentLabel);
		remove(remLabel);
		remove(commentsLabel);
		remove(nextVisitLabel);
		remove(visitIDField);
		remove(visitDateField);
		remove(nameField);
		remove(thcField);
		remove(visitSequenceField);
		remove(problemRankField);
		remove(categoryField);
		remove(protocolField);
		remove(fuField);
		remove(instrumentField);
		remove(nextVisitField);
		remove(remField);
		remove(commentField);
		remove(addInterviewButton);
		remove(addAudioButton);
		remove(addPharmaButton);
		remove(diagnoseButton);
		remove(addInstrumentButton);
		remove(addREMDetailsButton);
		remove(addCounselingButton);
		remove(recommendTreatmentButton);
		remove(saveButton);
		remove(cancelButton);
		
		buildPanel();
		repaint();
		revalidate();
	}
}