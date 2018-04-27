package main;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.event.*;

/**
 * GUI for making min-max based range segmentations.
 * 
 * @author Karl-Ingo Friese
 *
 */
@SuppressWarnings("serial")
public class ToolRangeSelector extends JPanel  {
	private int _min, _max;
	private Segment _seg;
	private JList<String> _seg_list;
	private JSlider _min_slider, _max_slider;
	private JLabel _range_sel_title, _min_label, _max_label;

	/**
	 * Default Constructor. Creates the GUI element and connects it to a
	 * segmentation.
	 * 
	 * @param slices	the global image stack
	 * @param seg		the segmentation to be modified
	 */
	public ToolRangeSelector(Segment seg) {
		_seg = seg;

		final ImageStack slices = ImageStack.getInstance();		
		JLabel seg_sel_title = new JLabel ("Edit Segmentation");
		
		_seg_list = new JList<String>(slices.getSegNames());
		_seg_list.setSelectedIndex(slices.getSegNames().indexOf(seg.getName()));
		_seg_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		_seg_list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int seg_index = _seg_list.getSelectedIndex();
				String name = (String)(slices.getSegNames().getElementAt(seg_index));
				if (!_seg.getName().equals(name)) {
					_seg = slices.getSegment(name);
					_range_sel_title.setText("Range Selector - "+_seg.getName());
					// ...
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(_seg_list);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		_range_sel_title = new JLabel("Range Selector - "+_seg.getName());

		// range_max needs to be calculated from the bits_stored value
		// in the current dicom series
		int range_max = 100;
		_min = 50;
		_max = 50;
		
		_min_label = new JLabel("Min:");
		_max_label = new JLabel("Max:");
		
		_min_slider = new JSlider(0, range_max, _min);
		_min_slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if (source.getValueIsAdjusting()) {
					_min = (int)source.getValue();
					System.out.println("_min_slider stateChanged: "+_min);
				}
			}
		});		
		
		_max_slider = new JSlider(0, range_max, _max);
		_max_slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if (source.getValueIsAdjusting()) {
					_max = (int)source.getValue();
					System.out.println("_max_slider stateChanged: "+_max);
				}
			}
		});
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weighty = 0.3;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(2,2,2,2); // top,left,bottom,right
		c.weightx = 0.1;
		c.gridx = 0; c.gridy = 0; this.add(seg_sel_title, c);

		c.gridheight = 2;
		c.gridx = 0; c.gridy = 1; this.add(scrollPane, c);
		c.gridheight = 1;

		c.weightx = 0.9;
		c.gridwidth=2;
		c.gridx = 1; c.gridy = 0; this.add(_range_sel_title, c);
		c.gridwidth=1;

		c.weightx = 0;
		c.gridx = 1; c.gridy = 1; this.add(_min_label, c);
		c.gridx = 1; c.gridy = 2; this.add(_max_label, c);
		c.gridx = 2; c.gridy = 1; this.add(_min_slider, c);
		c.gridx = 2; c.gridy = 2; this.add(_max_slider, c);
		
		// setBackground(Color.blue);
	}	
}
