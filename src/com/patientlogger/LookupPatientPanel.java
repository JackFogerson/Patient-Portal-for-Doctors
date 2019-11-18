package com.patientlogger;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.sql.Connection;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LookupPatientPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	Connection conn;
	
	String[] searchOptions = {"Choose One", "Name", "THC Number", "SSN"};
	JComboBox<String> searchCriteria;
	JTextField searchBox;
	JButton searchButton;
	JLabel selectedLabel;
	Patient selectedPatient;
	JPanel selectedPatientPanel;
	JLabel picture, name, thc, ssn;
	
	public LookupPatientPanel(Connection c)
	{
		conn = c;
		setLayout(new GridBagLayout());
		buildPanel();
	}
	
	private void buildPanel()
	{
		ImageIcon ogUnknownPicture = new ImageIcon("src/images/unknownPicture.png");
		ImageIcon unknownPicture = new ImageIcon(ogUnknownPicture.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		
		searchCriteria = new JComboBox<String>(searchOptions);
		searchBox = new JTextField(10);
		searchButton = new JButton("Search");
		
		selectedPatientPanel = new JPanel();
		selectedLabel = new JLabel("Selected Patient", SwingConstants.CENTER);
		selectedPatient = new Patient();
		picture = new JLabel(unknownPicture);
		name = new JLabel("Patient Name", SwingConstants.CENTER);
		thc = new JLabel("THC Number", SwingConstants.CENTER);
		ssn = new JLabel("SSN", SwingConstants.CENTER);
		
		searchButton.addActionListener(e -> search());
		
		selectedPatientPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.ipady = 0;
		c.ipadx = 50;
		selectedPatientPanel.add(selectedLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		c.ipadx = 50;
		selectedPatientPanel.add(picture, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		c.ipadx = 50;
		selectedPatientPanel.add(thc, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weighty = 1;
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		c.ipadx = 50;
		selectedPatientPanel.add(name, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weighty = 1;
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		c.ipadx = 50;
		selectedPatientPanel.add(ssn, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		c.ipadx = 0;
		add(searchCriteria, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weighty = 1;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.ipady = 0;
		c.ipadx = 50;
		add(searchBox, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weighty = 1;
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		c.ipadx = 0;
		add(searchButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 4;
		c.gridheight = 1;
		c.ipady = 0;
		c.ipadx = 0;
		add(selectedPatientPanel, c);
	}
	
	private void search()
	{
		
	}
}
