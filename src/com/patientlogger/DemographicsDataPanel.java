package com.patientlogger;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//TODO: Should we add to this panel? Unsure of
//		use since it is blank

/**
 * @title	AddNewVisitPanel
 * @author	Nick Fulton, Jack Fogerson
 * @desc	This class is an extension of JPanel
 */
public class DemographicsDataPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	Connection conn;
	
	JScrollPane occupationScroll, educationScroll, workScroll;
	JTable occupationTable, educationTable, workTable;
	JButton addOccupationButton, addEducationButton, addWorkButton,
			editOccupationButton, editEducationButton, editWorkButton;
	
	/**
	 *	@title	DemographicsDataPanel
	 *  @desc	constructor, builds panel
	 *  @param 	c - Is the connection to the database.
	 */
	public DemographicsDataPanel(Connection c)
	{
		conn = c;
		buildPanel();
	}
	
	private void buildPanel()
	{
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		occupationTable = new JTable();
		educationTable = new JTable();
		workTable = new JTable();
		
		occupationScroll = new JScrollPane(occupationTable);
		educationScroll = new JScrollPane(educationTable);
		workScroll = new JScrollPane(workTable);
		
		addOccupationButton = new JButton("Add Occupation");
		editOccupationButton = new JButton("Edit Occupation");
		addEducationButton = new JButton("Add Education");
		editEducationButton = new JButton("Edit Education");
		addWorkButton = new JButton("Add Work");
		editWorkButton = new JButton("Edit Work");
		
		addOccupationButton.addActionListener(e -> {
			try {
				addOccupation();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		addEducationButton.addActionListener(e -> {
			try {
				addEducation();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		addWorkButton.addActionListener(e -> {
			try {
				addWork();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		editOccupationButton.addActionListener(e -> {
			try {
				editOccupation();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		editEducationButton.addActionListener(e -> {
			try {
				editEducation();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		editWorkButton.addActionListener(e -> {
			try {
				editWork();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 175;
		c.ipady = 250;
		add(occupationScroll, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 175;
		c.ipady = 250;
		add(educationScroll, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 175;
		c.ipady = 250;
		add(workScroll, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 0;
		c.ipady = 0;
		add(addOccupationButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 0;
		c.ipady = 0;
		add(editOccupationButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 0;
		c.ipady = 0;
		add(addEducationButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 0;
		c.ipady = 0;
		add(editEducationButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 0;
		c.ipady = 0;
		add(addWorkButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 0;
		c.ipady = 0;
		add(editWorkButton, c);
		
		try 
		{
			buildTables();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void buildTables() throws SQLException
	{
		String query = "";
		String[] columnNames = {};
		Statement stmt;
		ResultSet rset;
		DefaultTableModel dtm;
		
		// First zipcodes
		query = "SELECT name FROM occupations WHERE id > 0 ORDER BY name;";
		columnNames = new String[] {"Occupations"};
		dtm = new DefaultTableModel(columnNames, 0);
		stmt = conn.createStatement();
		rset = stmt.executeQuery(query);
		
		while(rset.next())
		{
			String[] data = {rset.getString(1)};
			dtm.addRow(data);
		}
		
		occupationTable.setModel(dtm);
		occupationTable.setAutoCreateRowSorter(true);
		
		// Second  cities
		query = "SELECT name FROM educational_degrees WHERE id > 0  ORDER BY name;";
		columnNames = new String[] {"Educational Degrees"};
		dtm = new DefaultTableModel(columnNames, 0);
		stmt = conn.createStatement();
		rset = stmt.executeQuery(query);
		
		while(rset.next())
		{
			String[] data = {rset.getString(1)};
			dtm.addRow(data);
		}
		
		educationTable.setModel(dtm);
		educationTable.setAutoCreateRowSorter(true);
		
		// Third states
		query = "SELECT CONCAT(abbreviation, '-', name) FROM work_statuses WHERE id > 0  ORDER BY name;";
		columnNames = new String[] {"Work Status"};
		dtm = new DefaultTableModel(columnNames, 0);
		stmt = conn.createStatement();
		rset = stmt.executeQuery(query);
		
		while(rset.next())
		{
			String[] data = {rset.getString(1)};
			dtm.addRow(data);
		}
		
		workTable.setModel(dtm);
		workTable.setAutoCreateRowSorter(true);
	}
	
	public void addOccupation() throws SQLException
	{
		JPanel panel = new JPanel();
		JLabel nameLabel = new JLabel("Occupation: ");
		JTextField nameField = new JTextField(10);
		String name;
		
		panel.setLayout(new GridLayout(2,2));
		panel.add(nameLabel);
		panel.add(nameField);
		
		int result = JOptionPane.showConfirmDialog(null, panel, "Please Enter New Occupation", JOptionPane.OK_CANCEL_OPTION);
		
		if(result == JOptionPane.OK_OPTION)
		{
			name = nameField.getText();
		}
		else
		{
			return;
		}
		
		String query = "SELECT * FROM occupations WHERE name = '" + name + "';";
		
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery(query);
		
		while(rset.next())
		{
			if(rset.getString(1) == null)
			{
				// No zip is here, continue.
			}
			else
			{
				return;
			}
		}

		query = "INSERT INTO occupations(id, name) VALUES('" + (occupationTable.getModel().getRowCount() + 1) + "', '" + name + "');";

		
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.execute();
		
		buildTables();
	}
	
	private void addEducation() throws SQLException
	{
		JPanel panel = new JPanel();
		JLabel nameLabel = new JLabel("Education: ");
		JTextField nameField = new JTextField(10);
		String name;
		
		panel.setLayout(new GridLayout(2,2));
		panel.add(nameLabel);
		panel.add(nameField);
		
		int result = JOptionPane.showConfirmDialog(null, panel, "Please Enter New Education", JOptionPane.OK_CANCEL_OPTION);
		
		if(result == JOptionPane.OK_OPTION)
		{
			name = nameField.getText();
		}
		else
		{
			return;
		}
		
		String query = "SELECT * FROM educational_degrees WHERE name = '" + name + "';";
		
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery(query);
		
		while(rset.next())
		{
			if(rset.getString(1) == null)
			{
				// No zip is here, continue.
			}
			else
			{
				return;
			}
		}

		query = "INSERT INTO educational_degrees(id, name) VALUES('" + (educationTable.getModel().getRowCount() + 1) + "', '" + name + "');";

		
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.execute();
		
		buildTables();
	}
	
	private void addWork() throws SQLException
	{
		JPanel panel = new JPanel();
		JLabel nameLabel = new JLabel("Work Status: ");
		JLabel abbLabel = new JLabel("Abbreviation: ");
		JTextField nameField = new JTextField(10);
		JTextField abbField = new JTextField(10);
		String name, abb;
		
		panel.setLayout(new GridLayout(2,2));
		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(abbLabel);
		panel.add(abbField);
		
		int result = JOptionPane.showConfirmDialog(null, panel, "Please Enter Work Status and Abbreviation", JOptionPane.OK_CANCEL_OPTION);
		
		if(result == JOptionPane.OK_OPTION)
		{
			name = nameField.getText();
			abb = (String) abbField.getText();
		}
		else
		{
			return;
		}
		
		String query = "SELECT * FROM work_statuses WHERE name = '" + name + "';";
		
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery(query);
		
		while(rset.next())
		{
			if(rset.getString(1) == null)
			{
				// No zip is here, continue.
			}
			else
			{
				return;
			}
		}
		

		query = "INSERT INTO work_statuses(id, name, abbreviation) VALUES('" + (workTable.getModel().getRowCount() + 1) + "', '" + name + "', '" + abb + "');";

		
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.execute();
		
		buildTables();
	}
	
	private void editOccupation() throws SQLException
	{
		JPanel panel = new JPanel();
		JLabel nameLabel = new JLabel("Occupation: ");
		JLabel abbLabel = new JLabel("New Name: ");
		JComboBox<String> nameChoices;
		JTextField abb = new JTextField(10);
		String[] nameList = new String[occupationTable.getModel().getRowCount()];
		
		for(int x = 0; x < occupationTable.getModel().getRowCount(); x++)
		{
			nameList[x] = ((String)occupationTable.getModel().getValueAt(x, 0));
		}
		
		nameChoices = new JComboBox<String>(nameList);

		panel.setLayout(new GridLayout(2,2));
		panel.add(nameLabel);
		panel.add(nameChoices);
		panel.add(abbLabel);
		panel.add(abb);
		
		int result = JOptionPane.showConfirmDialog(null, panel, "Please Enter Occupation and New Name", JOptionPane.OK_CANCEL_OPTION);
		

		if(result == JOptionPane.OK_OPTION)
		{
			String query = "UPDATE occupations SET name = '" + abb.getText() + "' WHERE name = '" + nameChoices.getSelectedItem() + "';";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.execute();
		}
		else
		{
			return;
		}

		buildTables();
	}
	
	private void editEducation() throws SQLException
	{
		JPanel panel = new JPanel();
		JLabel nameLabel = new JLabel("Education: ");
		JLabel abbLabel = new JLabel("New Name: ");
		JComboBox<String> nameChoices;
		JTextField abb = new JTextField(10);
		String[] nameList = new String[educationTable.getModel().getRowCount()];
		
		for(int x = 0; x < educationTable.getModel().getRowCount(); x++)
		{
			nameList[x] = ((String)educationTable.getModel().getValueAt(x, 0));
		}
		
		nameChoices = new JComboBox<String>(nameList);

		panel.setLayout(new GridLayout(2,2));
		panel.add(nameLabel);
		panel.add(nameChoices);
		panel.add(abbLabel);
		panel.add(abb);
		
		int result = JOptionPane.showConfirmDialog(null, panel, "Please Enter Education and New Name", JOptionPane.OK_CANCEL_OPTION);
		

		if(result == JOptionPane.OK_OPTION)
		{
			String query = "UPDATE educational_degrees SET name = '" + abb.getText() + "' WHERE name = '" + nameChoices.getSelectedItem() + "';";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.execute();
		}
		else
		{
			return;
		}

		buildTables();
	}
	
	private void editWork() throws SQLException
	{
		JPanel panel = new JPanel();
		JLabel nameLabel = new JLabel("Work Status: ");
		JLabel abbLabel = new JLabel("New Name: ");
		JComboBox<String> nameChoices;
		JTextField abb = new JTextField(10);
		String[] nameList = new String[workTable.getModel().getRowCount()];
		
		for(int x = 0; x < workTable.getModel().getRowCount(); x++)
		{
			nameList[x] = ((String)workTable.getModel().getValueAt(x, 0)).split("-")[1];
		}
		
		nameChoices = new JComboBox<String>(nameList);

		panel.setLayout(new GridLayout(2,2));
		panel.add(nameLabel);
		panel.add(nameChoices);
		panel.add(abbLabel);
		panel.add(abb);
		
		int result = JOptionPane.showConfirmDialog(null, panel, "Please Enter Work Status and New Name", JOptionPane.OK_CANCEL_OPTION);
		

		if(result == JOptionPane.OK_OPTION)
		{
			String query = "UPDATE work_statuses SET name = '" + abb.getText() + "' WHERE name = '" + nameChoices.getSelectedItem() + "';";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.execute();
		}
		else
		{
			return;
		}

		buildTables();
	}
}
