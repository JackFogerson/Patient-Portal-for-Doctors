package com.patientlogger;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @title	HomePanel
 * @author	Nick Fulton, Jack Fogerson
 * @desc	This class is an extension of JPanel. Serves as starting page on launch
 */
public class HomePanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	GroupLayout layout;

	JLabel welcome;
	
	/**
	 * @title	AddNewPatientsPanel
	 * @desc	constructor, builds panel
	 */
	public HomePanel()
	{
		buildPanel();
	}
	
	/**
	 *	@title	buildPanel
	 *	@desc	Local method used to build this panel, puts the components in the correct place.
	 */
	private void buildPanel()
	{	
	}
}
