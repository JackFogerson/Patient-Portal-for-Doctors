package com.patientlogger;

import javax.swing.table.AbstractTableModel;

public class VisitDataModel extends AbstractTableModel
{
	// Create the needed variables.
	private static final long serialVersionUID = 1L;
	final String[] columnNames = {"ID", "Date", "Patient", "Visit", "Prob", "C", "CC", "Instr", "REM", "FU", "Comments"};
	private String[][] myData;
	
	/**
	 * @title	VisitDataModel Constructor
	 * @param 	data - The data to be put into the table.
	 */
	public VisitDataModel(String[][] data)
	{
		myData = data;
	}
	
	/**
	 * @title	setData Method
	 * @param 	data - The data to be put into the table.
	 */
	public void setData(String[][] data)
	{
		myData = data;
	}
	
	/**
	 * @title	getColumnName Method
	 * @param	col - Which column to get the name.
	 * @return	The name of the given column
	 */
	public String getColumnName(int col) 
	{
		return columnNames[col];
	}
	
	/**
	 * @title	getData Method
	 * @return	myData - The data of the table.
	 */
	public String[][] getData()
	{
		return myData;
	}
	
	/**
	 * @title 	getRowCount Method
	 * @return	How many rows the table has.
	 */
	@Override
	public int getRowCount() 
	{
		return myData.length;
	}

	/**
	 * @title	getColumnCount
	 * @return	How many columns the table has.
	 */
	@Override
	public int getColumnCount() 
	{
		return columnNames.length;
	}

	/**
	 * @title	getValueAt Method
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