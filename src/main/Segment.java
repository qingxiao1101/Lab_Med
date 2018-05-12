package main;

import misc.BitMask;

/**
 * This class represents a segment. Simply spoken, a segment has a unique name,
 * a color for displaying in the 2d/3d viewport and contains n bitmasks where n is the
 * number of images in the image stack.
 * 
 * @author  Karl-Ingo Friese
 */
public class Segment {
	private String _name;		// the segment name
	private int _color;			// the segment color
	private int _w;				// Bitmask width				
	private int _h;				// Bitmask height
	private BitMask[] _layers;	// each segment contains an array of n bitmasks
	

	/**
	 * Constructor for new segment objects.
	 * 
	 * @param name			the name of the new segment
	 * @param w				the width of the bitmasks
	 * @param h				the height of the bitmasks
	 * @param layer_num		the total number of bitmasks
	 */
	public Segment(String name, int w, int h, int layer_num) {
		this._name = name;
		this._w = w;
		this._h = h;
		
		_color = 0xff00ff;		
		_layers = new BitMask[layer_num];
		
		for (int i=0; i<layer_num; i++) {
			_layers[i] = new BitMask(_w,_h);
		}
	}

	/**
	 * Returns the number of bitmasks contained in this segment.
	 * 
	 * @return  the number of layers.
	 */
	public int getMaskNum() {
		return _layers.length;
	}

	/**
	 * Returns the Bitmask of a single layer.
	 * 
	 * @param i	the layer number
	 * @return	the coresponding bitmask
	 */
	public BitMask getMask(int i) {
		return _layers[i];
	}

	/**
	 * Returns the name of the segment.
	 * 
	 * @return  the segment name.
	 */
	public String getName() {
		return _name;
	}

	/**
	 * Sets the name of the segment.
	 * 
	 * @param name	the new segment name
	 */
	public void setName(String name) {
		_name = name;
	}

	/**
	 * Returns the segment color as the usual rgb int value.
	 * 
	 * @return the color
	 */
	public int getColor() {
		return _color;
	}

	/**
	 * Sets the segment color.
	 * 
	 * @param color the segment color (used when displaying in 2d/3d viewport)
	 */
	public void setColor(int color) {
		_color = color;
	}
	public void create_range_seg(int min,int max, ImageStack slices) {
		
	}
}
