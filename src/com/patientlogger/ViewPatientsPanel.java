package com.patientlogger;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ViewPatientsPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	Connection conn;
	
	JComboBox<String> searchCriteria;
	
	JButton viewPatientButton, editPatientButton, deletePatientButton, addNewVisitButton, showCurrentVisitButton,
			searchButton, refreshButton;
	
	JPanel patientsPaneButtons, patientsPanel;
	
	JScrollPane patientsScrollPane;
	
	JTextField searchBox;
	
	JTable patientTable;
	
	ArrayList<Patient> patients;
	
	final String[] searchOptions = {"Filter Options", "THC Number", "Name", "City"};
	
	public ViewPatientsPanel(Connection c)
	{
		this.conn = c;
		buildPanel();
	}

	private void buildPanel()
	{
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		searchCriteria = new JComboBox<String>(searchOptions);
		viewPatientButton = new JButton("View");
		editPatientButton = new JButton("Edit");
		deletePatientButton = new JButton("Delete");
		addNewVisitButton = new JButton("Add Visit");
		showCurrentVisitButton = new JButton("Current Visit");
		searchButton = new JButton("Search");
		refreshButton = new JButton("Refresh");

		patientTable = new JTable();
		
		
		refreshButton.addActionListener(e -> {
			try 
			{
				populate();
			} 
			catch (SQLException e2) 
			{
				e2.printStackTrace();
			}
		});
		
		searchButton.addActionListener(e -> {
			try 
			{
				search();
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		});
		
		deletePatientButton.addActionListener(e -> {
			try 
			{
				delete();
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		});
		
		editPatientButton.addActionListener(e -> edit());
		
		searchBox = new JTextField(10);
		
		try 
		{
			populate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		patientsScrollPane = new JScrollPane(patientTable);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		add(refreshButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		add(searchCriteria, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		add(searchBox, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		add(searchButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 5;
		c.gridheight = 20;
		c.ipady = 250;
		add(patientsScrollPane, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 21;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		add(viewPatientButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 21;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		add(editPatientButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 21;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		add(deletePatientButton, c);
		
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 21;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		add(addNewVisitButton, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 21;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipady = 0;
		add(showCurrentVisitButton ,c);
	}
	
	private void search() throws SQLException
	{
		if(searchBox.getText().equals(""))
		{
			populate();
			return;
		}
		
		patients = pullSomePatients();
		String[][] patientData = new String[patients.size()][7];
		
		for(int x = 0; x < patients.size(); x++)
		{
			patientData[x] = patients.get(x).getPatientInfo();
		}
		
		patientTable.setModel(new PatientTableModel(patientData));
		patientTable.setAutoCreateRowSorter(true);
		patientTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		patientTable.getColumnModel().getColumn(2).setPreferredWidth(30);
		patientTable.getColumnModel().getColumn(3).setPreferredWidth(50);
	}
	
	private void delete() throws SQLException
	{
		String THCNumber = (String)patientTable.getValueAt(patientTable.getSelectedRow(), 0);
		
		PreparedStatement preparedStmt = conn.prepareStatement("DELETE FROM PATIENTS WHERE THCNumber ='" + THCNumber + "';");
		preparedStmt.execute();
		
		patients = pullAllPatients();
		String[][] patientData = new String[patients.size()][7];
		
		for(int x = 0; x < patients.size(); x++)
		{
			patientData[x] = patients.get(x).getPatientInfo();
		}
		
		patientTable.setModel(new PatientTableModel(patientData));
		patientTable.setAutoCreateRowSorter(true);
		patientTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		patientTable.getColumnModel().getColumn(2).setPreferredWidth(30);
		patientTable.getColumnModel().getColumn(3).setPreferredWidth(50);
	}
	
	private void edit()
	{
		Patient patient = null;
		
		for(int x = 0; x < patients.size(); x++)
		{
			if((String)patientTable.getValueAt(patientTable.getSelectedRow(), 0) == patients.get(x).getTHCNumber())
			{
				patient = patients.get(x);
				break;
			}
		}
		
		JFrame frame = new JFrame("eTRT - Edit Patient");
		frame.add(new EditPatientScreen(conn, patient));
		
		frame.setSize(new Dimension(600, 450));
		frame.setResizable(false);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(d.width/2-frame.getSize().width/2, d.height/2-frame.getSize().height/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private void populate() throws SQLException
	{
		patients = pullAllPatients();
		String[][] patientData = new String[patients.size()][7];
		
		for(int x = 0; x < patients.size(); x++)
		{
			patientData[x] = patients.get(x).getPatientInfo();
		}
		
		patientTable.setModel(new PatientTableModel(patientData));
		patientTable.setAutoCreateRowSorter(true);
		patientTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		patientTable.getColumnModel().getColumn(2).setPreferredWidth(30);
		patientTable.getColumnModel().getColumn(3).setPreferredWidth(50);
	}
	
	private ArrayList<Patient> pullAllPatients() throws SQLException
	{
	    Statement stmt = conn.createStatement ();
	    ResultSet rset = stmt.executeQuery ("SELECT * FROM PATIENTS;");
	    ArrayList<Patient> patients = new ArrayList<Patient>();

	    // Iterate through the result and print out the table names
	    while (rset.next())
	    {
	    	patients.add(new Patient(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), 
	    									 rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), 
	    									 rset.getString(9), rset.getString(10), rset.getString(11), rset.getString(12), 
	    									 rset.getString(13), rset.getString(14), rset.getString(15), rset.getString(16), 
	    									 rset.getString(17), rset.getString(18), rset.getString(19), rset.getString(20), 
	    									 rset.getString(21), rset.getString(22), rset.getString(23), rset.getString(24), 
	    									 rset.getString(25)));
	    }
		return patients;
	}
	
	private ArrayList<Patient> pullSomePatients() throws SQLException
	{
		Statement stmt = conn.createStatement();
		ArrayList<Patient> patients = new ArrayList<Patient>();
		ResultSet rset = null;
		
		switch((String)searchCriteria.getSelectedItem())
		{
			// Default will be for THC and select search
			default:
				rset = stmt.executeQuery("SELECT * FROM PATIENTS WHERE THCNumber ='" + searchBox.getText() + "';");
				break;
				
			case "Name":
				String[] name = searchBox.getText().split(" ");
				rset = stmt.executeQuery("SELECT * FROM PATIENTS WHERE FirstName = '" + name[0] + "' AND LastName = '" + name[1] + "';");
				break;
			
			case "City":
				rset = stmt.executeQuery("SELECT * FROM PATIENTS WHERE City = '" + searchBox.getText() + "';");
				break;
		}
		
		while (rset.next())
	    {
			patients.add(new Patient(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), 
					rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8), 
					rset.getString(9), rset.getString(10), rset.getString(11), rset.getString(12), 
				 	rset.getString(13), rset.getString(14), rset.getString(15), rset.getString(16), 
				 	rset.getString(17), rset.getString(18), rset.getString(19), rset.getString(20), 
				 	rset.getString(21), rset.getString(22), rset.getString(23), rset.getString(24), 
				 	rset.getString(25)));
	    }
		return patients;
	}
}













