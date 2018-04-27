package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Container class for all tools in the lower section of the main window.
 *
 * @author Karl-Ingo Friese
 */
@SuppressWarnings("serial")
public class ToolPane extends JPanel {
	private JPanel _active_panel;
	
	/**
	 * Default Constructor. Creates an empty ToolPane with no active panel.
	 *
	 */
	public ToolPane() {
		this.setPreferredSize(new Dimension(800,200));
		setBorder( new LineBorder(Color.black, 1));
		_active_panel = null;
		setLayout(new BorderLayout(0,0));
	}

	/**
	 * Shows a new tool in the ToolPane.
	 * 
	 * @param panel the new panel to show
	 */
	public void showTool(JPanel panel) {
		if(_active_panel!=null) {
			this.remove(_active_panel);
		}

		this.add(panel, BorderLayout.CENTER);
		_active_panel = panel;
		this.validate();
		this.repaint();
	}

}
