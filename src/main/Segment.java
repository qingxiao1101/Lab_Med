package main;


import javax.swing.DefaultListModel;

import misc.BitMask;
import misc.DiFile;

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
	
	private int _max_slider,_min_slider;
	public void setMaxSlider(int max) {
		_max_slider = max;
	}
	public void setMinSlider(int min) {
		_min_slider = min;
	}
	public int getMaxSlider() {
		return _max_slider;
	}
	public int getMinSlider() {
		return _min_slider;
	}
	

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
	public Segment(Segment seg) {
		this._name = seg.getName();
		this._w = seg._w;
		this._h = seg._h;
		this._min_slider = seg.getMinSlider();
		this._max_slider = seg.getMaxSlider();
		this._layers = seg._layers;
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
	
	/**
	 * @author Xiao; Tang --exercise 3 
	 * @param max
	 * @param min
	 * @param slices
	 */
	public void create_range_seg(int max, int min,ImageStack slices) {		
		int active_img_id = slices.getActiveImageID();
		DiFile active_file = slices.getDiFile(active_img_id);
		int h_bild = active_file.getImageWidth();
		int w_bild = active_file.getImageHeight();
		int bitalloc = active_file.getBitsAllocated()/8;
		int max_original = (1 << active_file.getBitsStored())-1;		
		this._max_slider = max;
		this._min_slider = min;
		int grenz_min = (max_original/100)*min;
		int grenz_max = (max_original/100)*max;
		
				
		byte[] prime_data = new byte[bitalloc*h_bild*w_bild];
		prime_data = active_file.getElement(0x7FE00010).getValues();		
		int[] prime_pixel = new int[w_bild*h_bild];		
		int it = 0;		
		for(int i=0;i<prime_pixel.length;i++) {
			prime_pixel[i] = (int)((prime_data[it+1] << 8)) + (int)((prime_data[it]));
			it += 2;
			if((prime_pixel[i]>=grenz_min) && (prime_pixel[i]<=grenz_max)) {
				this.getMask(active_img_id).set(i/w_bild, i%w_bild, true);
				//_layers[sequence].set(i/w_bild, i%w_bild, true);
			}
			else {
				this.getMask(active_img_id).set(i/w_bild, i%w_bild, false);
				//_layers[sequence].set(i/w_bild, i%w_bild, false);
			}			
		}
		
		/*
		for(int sequence=0;sequence<slices.getNumberOfImages();sequence++) {
			DiFile di = slices.getDiFile(sequence);
			byte[] prime_data = new byte[bitalloc*h_bild*w_bild];
			prime_data = di.getElement(0x7FE00010).getValues();
			
			int[] prime_pixel = new int[w_bild*h_bild];
			int it = 0;
			for(int i=0;i<prime_pixel.length;i++) {
				prime_pixel[i] = (int)((prime_data[it+1] << 8)) + (int)((prime_data[it]));
				it += 2;
				if(prime_pixel[i]>=grenz_min&&prime_pixel[i]<=grenz_max) {
					this.getMask(sequence).set(i/w_bild, i%w_bild, true);
					//_layers[sequence].set(i/w_bild, i%w_bild, true);
				}
				else {
					this.getMask(sequence).set(i/w_bild, i%w_bild, false);
					//_layers[sequence].set(i/w_bild, i%w_bild, false);
				}
			}			
		}
		*/
	 
	}
}
