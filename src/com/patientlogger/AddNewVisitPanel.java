package com.patientlogger;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
			   problemRankField, categoryField, protocolField, instrumentField,
			   fuField, nextVisitField;
	
	JCheckBox remField;
	
	JTextArea commentField;
	
	JButton thcLookupButton, addInterviewButton, addAudioButton, addPharmaButton,
			diagnoseButton, addInstrumentButton, addREMDetailsButton, addCounselingButton,
			recommendTreatmentButton, saveButton, cancelButton;
	
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
		problemRankField = new JTextField();
		categoryField = new JTextField();
		protocolField = new JTextField();
		fuField = new JTextField();
		instrumentField = new JTextField();
		nextVisitField = new JTextField();
		
		remField = new JCheckBox();
		commentField = new JTextArea(4, 30);
		
		addInterviewButton = new JButton("Interview");
		addAudioButton = new JButton("Audiology");
		addPharmaButton = new JButton("Medical Other");
		diagnoseButton = new JButton("Diagnose");
		
		addInstrumentButton = new JButton("Instrument Details");
		addREMDetailsButton = new JButton("REM Details");
		addCounselingButton = new JButton("Counseling Details");
		recommendTreatmentButton = new JButton("Recommend Treatment");
		
		saveButton = new JButton("Save");
		cancelButton = new JButton("Cancel");
		
		GridBagConstraints c = new GridBagConstraints();
		
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
		
		
	}
}