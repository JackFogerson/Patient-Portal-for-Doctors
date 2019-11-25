package com.patientlogger;

import javax.swing.table.AbstractTableModel;

/**
 * @title	PatientTableModel
 * @author 	Nick Fulton, Jack Fogerson
 * @desc	Provides table model for displaying patients in a JTable.
 */
public class PatientTableModel extends AbstractTableModel
{
	// Create the variables called in the class.
	private static final long serialVersionUID = 1L;
	final String[] columnNames = {"THC#", "Name", "Age", "Gender", "City", "State", "Date Added"};
	private String[][] myData;
	
	/**
	 * @title	PatientTableModel
	 * @desc 	Constructor
	 * @param 	data - The data to be put into table.
	 */
	public PatientTableModel(String[][] data)
	{
		myData = data;
	}
	
	/**
	 * @title	setData
	 * @desc	sets given data
	 * @param 	data - The data to be put into the table.
	 */
	public void setData(String[][] data)
	{
		myData = data;
	}
	
	/**
	 * @title	getColumnName
	 * @param	col - Which column to get the name.
	 * @return	The name of the given column
	 */
	public String getColumnName(int col) 
	{
		return columnNames[col];
	}
	
	/**
	 * @title	getData
	 * @desc	Returns data of the table.
	 */
	public String[][] getData()
	{
		return myData;
	}
	
	/**
	 * @title 	getRowCount
	 * @return	How many rows the table has.
	 */
	@Override
	public int getRowCount() 
	{
		return myData.length;
	}

	/**
	 * @title	getColumnCount
	 * @desc	Returns # of columns the table has
	 */
	@Override
	public int getColumnCount() 
	{
		return columnNames.length;
	}

	/**
	 * @title	getValueAt
	 * @param	rowIndex - The row of the value
	 * @param	columnIndex - The column of the value
	 * @return	The data at the given position of the table.
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) 
	{
		return myData[rowIndex][columnIndex];
	}
}
