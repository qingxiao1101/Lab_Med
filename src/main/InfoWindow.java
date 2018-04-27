package main;

import java.util.*;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import misc.*;

/**
 * A simple window for displaying the data values of a DICOM file in a
 * human readable way.
 * 
 * @author Karl-Ingo Friese
 *
 */
@SuppressWarnings("serial")
public class InfoWindow extends JFrame implements Observer {
	private JTable _table;
	private JScrollPane _scroll_pane;
	private DefaultTableModel _tm;
	
	
	/**
	 * Constructor; opens the main window and initializes the GUI elements.
	 * 
	 * @param slices	the global image stack
	 */
	public InfoWindow() {
		setSize(new Dimension(800, 800));
		
		_table = null;
		_scroll_pane = null;

		_tm = new DefaultTableModel();
    	_table = new JTable(_tm);
		_scroll_pane = new JScrollPane(_table);		
		add(_scroll_pane);
		
		setResizable(true);
		setVisible(true);
	}
	
	
	/**
	 * Sets the info windows content to the data of the given DiFile.
	 * 
	 * @param di	the DiFile to show
	 */
	public void showInfo(DiFile di) {		
		setTitle("DICOM File Info: "+di.getFileName());
		Hashtable<Integer, DiDataElement> data_elements = di.getDataElements();
		
		Enumeration<Integer> e = data_elements.keys();
   	  	List<Integer> l = new ArrayList<Integer>();

   	  	while(e.hasMoreElements()) {
		  int key  = ((Integer)(e.nextElement())).intValue();
		  DiDataElement el = (DiDataElement)(data_elements.get(key));
		  l.add(new Integer(el.getTag()));
		}
        Collections.sort(l);
        
        String[][] table_data = new String[l.size()][5];
        String[] column_names = {"Tag", "VR", "VL", "Value", "Description"};
        
        int i=0;
        Iterator<Integer> it = l.iterator();
        while (it.hasNext()) {
        	int tag = (it.next()).intValue();
        	DiDataElement de = (DiDataElement)(data_elements.get(tag));
        	table_data[i][0] = " "+de.getTagString()+" ";
        	table_data[i][1] = " "+de.getVRString()+" "; 
        	table_data[i][2] = " "+de.getVL()+" "; 
        	table_data[i][3] = de.getValueAsString(); 
        	table_data[i][4] = DiDi.getTagDescr(de.getTag());
        	i++;
        }
        
        _tm.setDataVector(table_data, column_names);
        _table.getColumnModel().getColumn(0).setPreferredWidth(80);
        _table.getColumnModel().getColumn(1).setPreferredWidth(40);
        _table.getColumnModel().getColumn(2).setPreferredWidth(40);
        _table.getColumnModel().getColumn(3).setPreferredWidth(300);
        _table.getColumnModel().getColumn(4).setPreferredWidth(300);
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(final Observable o, final Object obj ) {
		if (!EventQueue.isDispatchThread()) {
			// all swing thingies must be done in the AWT-EventQueue 
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					update(o,obj);
				}
			});
			return;
		}

		// boolean update_needed = false;
		Message m = (Message)obj;
		if (m._type == Message.M_NEW_ACTIVE_IMAGE) {
			if (this.isVisible()) {
				int image_id = ((Integer)m._obj).intValue();
				showInfo(ImageStack.getInstance().getDiFile(image_id));
			}
		}		
	}
}
