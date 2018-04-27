package misc;

/**
 * A two dimensional BitMask class (used for segmentations), optimized
 * for speed & low mem usage.
 * 
 * @author Karl-Ingo Friese
 */
public class BitMask {
	private long[] _data;
	private int _w;
	private int _h;

	/**
	 * Default Constructor with width and height.
	 * 
	 * @param w	width
	 * @param h	heigth
	 */
	public BitMask(int w, int h) {
		_w = w;
		_h = h;
		_data = new long[((w*h)/64)+1];
		clear();
	}

	/**
	 * Sets or unsets a bit pixel.
	 *  
	 * @param x		the horizontal position
	 * @param y		the vertical position
	 * @param value	the bit pixel value (true or false)
	 */
	public void set(int x, int y, boolean value) {
		int longnum = (x+y*_w)>>6;
		int bitnum = (x+y*_w)%64;

		if (value) {
			_data[longnum] |= ((long)(1))<<bitnum;
		} else {
			_data[longnum] &= ~(((long)(1))<<bitnum);
		}
	}


	/**
	 * Returns true if a bit pixel is set. 
	 * 
	 * @param x		the horizontal position
	 * @param y		the vertical position
	 * @return		true if the bit pixel is set, false if it isnt.
	 */
	public boolean get(int x, int y) {
		int longnum = (x+y*_w)>>6;
		int bitnum = (x+y*_w)%64;

		return (_data[longnum] & ((long)(1))<<bitnum) != 0;
	}


	/**
	 * Converts the bitmask into a string (useful for debugging). 
	 * @return		a human readable string representation. might not be human readable
	 *              for very large bitmasks.
	 */
	public String toString() {
		StringBuffer str = new StringBuffer(_data.length+_h);

		for (int i=0; i<_h; i++) {
			for (int j=0; j<_w; j++) {
				str.append(get(j,i) ? "1" : "0");
			}
			str.append("\n");
		}
		
		return str.toString();
	}


	/**
	 * Unsets every bit in the BitMask.
	 */
	public void clear() {
		for (int i=0; i<_data.length; i++) {
			_data[i] = 0;
		}
	}
}
