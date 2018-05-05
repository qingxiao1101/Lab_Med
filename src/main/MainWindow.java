package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import misc.Viewport3d;


/**
 * The YaDiV main window class, containing most of the GUI elements.
 * 
 * @author  Karl-Ingo Friese
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame implements ComponentListener {
	/**
	 * Constructor; opens the main window and places the two viewports.
	 * 
	 * @param	title	the window title
	 * @param	w		the window width
	 * @param	h		the window height
	 * @param	v2d		a viewport2d reference
	 * @param	v3d		a viewport3d reference
	 * @param	slices	a reference to the global image stack
	 */
	public MainWindow(String title, Viewport2d v2d, Viewport3d v3d) {
		super(title);
		// if someone closes the main window, the program shall end.
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		setLayout(new GridBagLayout());
		
		JLabel label2dview = new JLabel("2D View");
		JLabel label3dview = new JLabel("3D View");

		ToolPane tools = new ToolPane();
		
		GridBagConstraints c = new GridBagConstraints();	//显示组件	

		// Label
		c.insets = new Insets(2,10,2,2); //top,left,bottom,right
		c.gridx = 0; c.gridy = 0; add(label2dview, c);
		c.gridx = 1; c.gridy = 0; add(label3dview, c);
		
		// Views + Buttons
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.insets = new Insets(2,10,10,2); /// top,left,bottom,right
		c.gridx = 0; c.gridy = 1; add(v2d, c);
		c.insets = new Insets(2,2,10,10); /// top,left,bottom,right
		c.gridx = 1; c.gridy = 1; add(v3d, c);
		c.insets = new Insets(2,10,10,10); // top,left,bottom,right
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0; c.gridy = 2; add(tools, c);

		// now the menu ...
		MenuBar menu_bar = new MenuBar(v2d, v3d, tools);
		setJMenuBar(menu_bar);
		pack();
		
		// setResizable(false);
	}

	/**
	 * EventListener
	 */
	public void componentHidden(ComponentEvent e) {		
//		System.out.println("componentHidden event from "
//			       + e.getComponent().getClass().getName());
	}

	/**
	 * EventListener
	 */
    public void componentMoved(ComponentEvent e) {
//        Component c = e.getComponent();
//        System.out.println("componentMoved event from "
//                       + c.getClass().getName()
//                       + "; new location: "
//                       + c.getLocation().x
//                       + ", "
//                       + c.getLocation().y);
    }

    /**
	 * EventListener
     */
    public void componentResized(ComponentEvent e) {
//        Component c = e.getComponent();
//        System.out.println("componentResized event from "
//                       + c.getClass().getName()
//                       + "; new size: "
//                       + c.getSize().width
//                       + ", "
//                       + c.getSize().height);
    }

    /**
	 * EventListener
     */
    public void componentShown(ComponentEvent e) {
//    	System.out.println("componentShown event from "
//		       + e.getComponent().getClass().getName());
    }
}


