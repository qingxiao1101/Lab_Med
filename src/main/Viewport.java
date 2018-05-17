package main;

import java.util.*;
import javax.swing.*;

/**
 * Abstract viewport class. Implements several things that Viewport2d
 * and Viewport3d have in common.
 * 
 * @author Karl-Ingo Friese
 *
 */
@SuppressWarnings("serial")
public class Viewport extends JPanel {
	public int DEF_WIDTH = 500;
	public int DEF_HEIGHT = 500;
	protected ImageStack _slices;
	protected Hashtable<String, Segment> _map_name_to_seg;
	protected boolean _show_bg;

	/**
	 * The only valid constructor takes the global image stack as argument.
	 * 
	 * @param slices the global image stack
	 */
	public Viewport() {
		_show_bg = true; 
		_map_name_to_seg = new Hashtable<String, Segment>();
		_slices = ImageStack.getInstance();
	}
		
	/**
	 * This function has to be overloaded with something useful in Viewport2d & 3d.
	 *
	 */
	public void update_view() {
		System.out.println("Viewport::update_view - this should never be seen!");
	}

	/**
	 * Toggles drawing of the background (non-segmented visualization)
	 * and updates the view.
	 * 
	 * @return true if the background will be shown, false if not
	 */
	public boolean toggleBG() {
		_show_bg = !_show_bg;
		update_view();
		
		return _show_bg;		
	}

	/**
	 * Toggles drawing of a segmentation in the viewport.
	 * 
	 * @param seg the segmentation to show
	 * @return true if the segmentation will be shown, false if not
	 */
	public boolean toggleSeg(Segment seg) {
		String name = seg.getName();
		if (_map_name_to_seg.get(name)!=null) {
			// if it is not shown currently, put the name in the internal hashtable
			_map_name_to_seg.remove(name);
		} else {
			// if it is shown already, remove the name instead
			_map_name_to_seg.put(name, seg);
		}

		update_view();
		
		return _map_name_to_seg.containsKey(name);
	}
}
