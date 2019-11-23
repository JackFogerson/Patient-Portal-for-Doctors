package com.patientlogger;

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
			reccomentTreatmentButton, saveButton, cancelButton;
	
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
		visitSequenceLabel = new JLabel("Visit Number");
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
	}
}
