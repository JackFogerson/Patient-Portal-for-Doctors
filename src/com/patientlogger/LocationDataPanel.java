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
//use since it is blank

/**
* @title	LocationDataPanel
* @author	Nick Fulton, Jack Fogerson
* @desc		This class is an extension of JPanel
*/
public class LocationDataPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	Connection conn;
	
	JScrollPane zipcodeScroll, cityScroll, stateScroll, countryScroll;
	JTable zipcodeTable, cityTable, stateTable, countryTable;
	
	JButton addZipButton, editZip, addCityButton, editCity, addStateButton, editState, addCountryButton, editCountry;
	
	/**
	 *	@title	LocationDataPanel
	 *  @desc	constructor, builds panel
	 *  @param 	c - Is the connection to the database.
	 */
	public LocationDataPanel(Connection c)
	{
		conn = c;
		buildPanel();
	}
	
	private void buildPanel()
	{
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		zipcodeTable = new JTable();
		cityTable = new JTable();
		stateTable = new JTable();
		countryTable = new JTable();
		
		zipcodeScroll = new JScrollPane(zipcodeTable);
		cityScroll = new JScrollPane(cityTable);
		stateScroll = new JScrollPane(stateTable);
		countryScroll = new JScrollPane(countryTable);
		
		addZipButton = new JButton("Add Zipcode");
		editZip = new JButton("Edit Zipcode");
		addCityButton = new JButton("Add City");
		editCity = new JButton("Edit City");
		addStateButton = new JButton("Add State");
		editState = new JButton("Edit State");
		addCountryButton = new JButton("Add Country");
		editCountry = new JButton("Edit Country");

		addZipButton.addActionListener(e -> {
			try {
				addZip();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		addCityButton.addActionListener(e -> {
			try {
				addCity();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		addStateButton.addActionListener(e -> {
			try {
				addState();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		addCountryButton.addActionListener(e -> {
			try {
				addCountry();
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
		c.ipadx = 50;
		c.ipady = 250;
		add(zipcodeScroll, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 120;
		c.ipady = 250;
		add(cityScroll, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 120;
		c.ipady = 250;
		add(stateScroll, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 175;
		c.ipady = 250;
		add(countryScroll, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 0;
		c.ipady = 0;
		add(addZipButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 0;
		c.ipady = 0;
		add(editZip, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 0;
		c.ipady = 0;
		add(addCityButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 0;
		c.ipady = 0;
		add(editCity, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 0;
		c.ipady = 0;
		add(addStateButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 0;
		c.ipady = 0;
		add(editState, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 0;
		c.ipady = 0;
		add(addCountryButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 0;
		c.ipady = 0;
		add(editCountry, c);

		try 
		{
			buildTables();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	private void buildTables() throws SQLException
	{
		String query = "";
		String[] columnNames = {};
		Statement stmt;
		ResultSet rset;
		DefaultTableModel dtm;
		
		// First zipcodes
		query = "SELECT zipcode FROM zipcodes ORDER BY zipcode;";
		columnNames = new String[] {"Zipcodes"};
		dtm = new DefaultTableModel(columnNames, 0);
		stmt = conn.createStatement();
		rset = stmt.executeQuery(query);
		
		while(rset.next())
		{
			String[] data = {rset.getString(1)};
			dtm.addRow(data);
		}
		
		zipcodeTable.setModel(dtm);
		zipcodeTable.setAutoCreateRowSorter(true);
		
		// Second  cities
		query = "SELECT name FROM cities ORDER BY name;";
		columnNames = new String[] {"Cities"};
		dtm = new DefaultTableModel(columnNames, 0);
		stmt = conn.createStatement();
		rset = stmt.executeQuery(query);
		
		while(rset.next())
		{
			String[] data = {rset.getString(1)};
			dtm.addRow(data);
		}
		
		cityTable.setModel(dtm);
		cityTable.setAutoCreateRowSorter(true);
		
		// Third states
		query = "SELECT CONCAT(abbreviation, '-', name) FROM states ORDER BY name;";
		columnNames = new String[] {"States"};
		dtm = new DefaultTableModel(columnNames, 0);
		stmt = conn.createStatement();
		rset = stmt.executeQuery(query);
		
		while(rset.next())
		{
			String[] data = {rset.getString(1)};
			dtm.addRow(data);
		}
		
		stateTable.setModel(dtm);
		stateTable.setAutoCreateRowSorter(true);
		
		// Fourth countries
		query = "SELECT CONCAT(abbreviation, '-', name) FROM countries ORDER BY name;";
		columnNames = new String[] {"Countries"};
		dtm = new DefaultTableModel(columnNames, 0);
		stmt = conn.createStatement();
		rset = stmt.executeQuery(query);
		
		while(rset.next())
		{
			String[] data = {rset.getString(1)};
			dtm.addRow(data);
		}
		
		countryTable.setModel(dtm);
		countryTable.setAutoCreateRowSorter(true);
	}
	
	private void addZip() throws SQLException
	{
		JPanel panel = new JPanel();
		JLabel zipField = new JLabel("Zipcode: ");
		JLabel citField = new JLabel("Cities: ");
		JTextField zip = new JTextField(10);
		String[] cities = new String[cityTable.getModel().getRowCount()];
		
		String zipcode, city;
		
		for(int x = 0; x < cityTable.getModel().getRowCount(); x++)
		{
			cities[x] = (String)cityTable.getModel().getValueAt(x, 0);
		}

		JComboBox<String> cit = new JComboBox<String>(cities);
		
		panel.setLayout(new GridLayout(2,2));
		panel.add(zipField);
		panel.add(zip);
		panel.add(citField);
		panel.add(cit);
		
		int result = JOptionPane.showConfirmDialog(null, panel, "Please Enter Zipcode and City", JOptionPane.OK_CANCEL_OPTION);
		
		if(result == JOptionPane.OK_OPTION)
		{
			zipcode = zip.getText();
			city = (String) cit.getSelectedItem();
			
		}
		else
		{
			return;
		}
		
		boolean isThere = false;
		
		String query = "SELECT * FROM Zipcodes WHERE Zipcode = '" + zipField.getText() + "';";
		
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
				if(rset.getString(2) == null)
				{
					// This means we can input the city.
					isThere = true;
				}
				else
				{
					// City and zip are both there so return.
					return;
				}
			}
		}
		
		// This means we need to just put in the city.
		if(isThere)
		{
			query = "UPDATE Zipcodes SET City = (SELECT id FROM Cities WHERE Name = '" + cit.getSelectedItem() + "') where Zipcode = '" + zip.getText() + "';";
		
		}
		// This means we need to put in the zipcode and the city.
		else
		{
			query = "INSERT INTO Zipcodes(Zipcode, City) VALUES('" + zip.getText() + "', (SELECT id FROM cities WHERE name = '" + cit.getSelectedItem() + "'));";
		}
		
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.execute();
		
		buildTables();
	}
	
	private void editZip()
	{
		
	}
	
	private void addCity() throws SQLException
	{
		JPanel panel = new JPanel();
		JLabel cityField = new JLabel("City: ");
		JLabel stateField = new JLabel("States: ");
		JTextField ct = new JTextField(10);
		String[] states = new String[stateTable.getModel().getRowCount()];
		
		String city, state;
		
		for(int x = 0; x < stateTable.getModel().getRowCount(); x++)
		{
			states[x] = (String)stateTable.getModel().getValueAt(x, 0);
		}

		JComboBox<String> st = new JComboBox<String>(states);
		
		panel.setLayout(new GridLayout(2,2));
		panel.add(cityField);
		panel.add(ct);
		panel.add(stateField);
		panel.add(st);
		
		int result = JOptionPane.showConfirmDialog(null, panel, "Please Enter City and State", JOptionPane.OK_CANCEL_OPTION);
		
		if(result == JOptionPane.OK_OPTION)
		{
			city = ct.getText();
			state = (String) st.getSelectedItem();
			
		}
		else
		{
			return;
		}
		
		boolean isThere = false;
		
		String query = "SELECT * FROM cities WHERE name = '" + city + "';";
		
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
				if(rset.getString(2) == null)
				{
					// This means we can input the city.
					isThere = true;
				}
				else
				{
					// City and zip are both there so return.
					return;
				}
			}
		}
		
		// This means we need to just put in the city.
		if(isThere)
		{
			query = "UPDATE cities SET state = (SELECT id FROM states WHERE Name = '" + state + "') where name = '" + city + "';";
		
		}
		// This means we need to put in the zipcode and the city.
		else
		{
			query = "INSERT INTO cities(id, name, state) VALUES('" + (cityTable.getModel().getRowCount() + 1) + "', '" + city + "', (SELECT id FROM states WHERE name = '" + state + "'));";
		}
		
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.execute();
		
		buildTables();
	}
	
	private void editCity()
	{
		
	}
	
	private void addState() throws SQLException
	{
		JPanel panel = new JPanel();
		JLabel nameLabel = new JLabel("State: ");
		JLabel abbLabel = new JLabel("Abbreviation: ");
		JTextField nameField = new JTextField(10);
		JTextField abbField = new JTextField(10);
		String name, abb;
		
		panel.setLayout(new GridLayout(2,2));
		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(abbLabel);
		panel.add(abbField);
		
		int result = JOptionPane.showConfirmDialog(null, panel, "Please Enter State and Abbreviation", JOptionPane.OK_CANCEL_OPTION);
		
		if(result == JOptionPane.OK_OPTION)
		{
			name = nameField.getText();
			abb = (String) abbField.getText();
		}
		else
		{
			return;
		}
		
		String query = "SELECT * FROM states WHERE name = '" + name + "';";
		
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
		

		query = "INSERT INTO states(id, name, abbreviation, country) VALUES('" + (stateTable.getModel().getRowCount() + 1) + "', '" + name + "', '" + abb + "', 1);";

		
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.execute();
		
		buildTables();
	}
	
	private void editState()
	{
		
	}
	
	private void addCountry() throws SQLException
	{
		JPanel panel = new JPanel();
		JLabel nameLabel = new JLabel("Country: ");
		JLabel abbLabel = new JLabel("Abbreviation: ");
		JTextField nameField = new JTextField(10);
		JTextField abbField = new JTextField(10);
		String name, abb;
		
		panel.setLayout(new GridLayout(2,2));
		panel.add(nameLabel);
		panel.add(nameField);
		panel.add(abbLabel);
		panel.add(abbField);
		
		int result = JOptionPane.showConfirmDialog(null, panel, "Please Enter Country and Abbreviation", JOptionPane.OK_CANCEL_OPTION);
		
		if(result == JOptionPane.OK_OPTION)
		{
			name = nameField.getText();
			abb = (String) abbField.getText();
		}
		else
		{
			return;
		}
		
		String query = "SELECT * FROM countries WHERE name = '" + name + "';";
		
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
		

		query = "INSERT INTO countries(id, name, abbreviation) VALUES('" + (countryTable.getModel().getRowCount() + 1) + "', '" + name + "', '" + abb + "');";

		
		PreparedStatement preparedStmt = conn.prepareStatement(query);
		preparedStmt.execute();
		
		buildTables();
	}
	
	private void editCountry()
	{
		
	}
}
