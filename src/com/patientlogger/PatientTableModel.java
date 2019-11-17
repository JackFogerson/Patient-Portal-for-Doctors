package com.patientlogger;

import javax.swing.table.AbstractTableModel;

public class PatientTableModel extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	final String[] columnNames = {"THC#", "Name", "Age", "Gender", "City", "State", "Date Added"};
	private String[][] myData;
	
	public PatientTableModel(String[][] data)
	{
		myData = data;
	}
	
	public void setData(String[][] data)
	{
		myData = data;
	}
	
	public String getColumnName(int col) 
	{
		return columnNames[col];
	}
	
	public String[][] getData()
	{
		return myData;
	}
	
	@Override
	public int getRowCount() 
	{
		return myData.length;
	}

	@Override
	public int getColumnCount() 
	{
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) 
	{
		return myData[rowIndex][columnIndex];
	}

}
