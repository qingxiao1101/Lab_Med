package misc;

/**
 * Implements the internal representation of a DICOM Data Element.
 * 
 * @author Karl-Ingo Friese
 */
public class DiDataElement {
	private int _groupid;
	private int _elementid;
	private int _vl;
	private int _vr;
	private byte[] _values;

	/**
	 * Default constructor; creates an empty element.
	 */
	public DiDataElement() {
		_groupid = 0;
		_elementid = 0;
		_vl = 0;
		_vr = 0;
		_values = null;
	}

	/**
	 * Reads the next DiDataElement from a (dicom) input stream.
	 * Might throwh an IOException, for example unexpected end of file.
	 * This method will be implemented in exercise 1.
	 * 
	 * @param is			a DiInputStream - must be open and readable
	 * @throws Exception	if reading was unsuccessful
	 */
	public void readNext(DiFileInputStream is) throws Exception {
    	// exercise 1
	}

	/**
	 * Converts the DiDataElement to a human readable string.
	 * @return		a human readable string representation
	 */
	public String toString() {
		String str;
		
		str=getTagString()+" ("+DiDi.getTagDescr(getTag())+")  ";
		str+="VR: "+getVRString()+"  VL: "+_vl+"  Values: "+getValueAsString();		
		
		return str;
	}

	/**
	 * Returns the element number (second part of the tag id).
	 * 
	 * @return the element numbber as an integer.
	 */
	public int getElementID() {
		return _elementid;
	}

	/**
	 * Sets the element number.
	 * @param element_number the element number.
	 */
	public void setElementID(int element_number) {
		this._elementid = element_number;
	}

	/**
	 * Returns the group number (first part of the tag id)..
	 * 
	 * @return the group number.
	 */
	public int getGroupID() {
		return _groupid;
	}


	/**
	 * Sets the group number.
	 * @param group_number the element number.
	 */
	public void setGroupID(int group_number) {
		this._groupid = group_number;
	}

	/**
	 * Returns the value length.
	 * 
	 * @return the value length
	 */
	public int getVL() {
		return _vl;
	}

	/**
	 * Sets the value length.
	 * 
	 * @param value_length guess what
	 */
	public void setVL(int value_length) {
		this._vl = value_length;
	}

	/**
	 * Allows access to the byte value array.
	 * 
	 * @return the byte value array containing the element data
	 */
	public byte[] getValues() {
		return _values;
	}

	/**
	 * Sets the byte value array.
	 * @param values a byte array containing the element values.
	 */
	public void setValues(byte[] values) {
		this._values = values;
	}

	/**
	 * Returns the value as a double value. Does not perform a typecheck before.
	 *  
	 * @return the double value
	 */
	public double getValueAsDouble() {
		String str = getValueAsString();
		
		return Double.parseDouble(str.trim());										
	}
	
	/**
	 * Returns the value as an int value. Does not perform a typecheck before.
	 *  
	 * @return the int value
	 */
	public int getValueAsInt() {
		String str = getValueAsString();
		return Integer.parseInt(str.trim());										
	}
	
	/**
	 * Returns the value as a string value.
	 * TODO: support for OB
	 *  
	 * @return the string value
	 */
	public String getValueAsString() {
		String str = new String();
				
		if (_vl>255) {
			str = "(too long to be printed)";
		} else if (_vr==DiDi.AE || _vr==DiDi.AS || _vr==DiDi.CS || _vr==DiDi.DA || _vr==DiDi.DS ||
				_vr==DiDi.DT || _vr==DiDi.IS || _vr==DiDi.LO || _vr==DiDi.LT || _vr==DiDi.OF ||
				_vr==DiDi.PN || _vr==DiDi.SH || _vr==DiDi.ST || _vr==DiDi.TM || _vr==DiDi.UI ||
				_vr==DiDi.UN || _vr==DiDi.UT) {
			for (int i=0; i<_vl; i++) {
				if (_values[i]>0) {
					str+=((char)(_values[i]));
				}
			}
		} else if (_vr==DiDi.FL) {
			int tmp = (_values[3]<<24 | _values[2]<<16 | _values[1]<<8 | _values[0]);
			Float f = Float.valueOf(Float.intBitsToFloat(tmp));
			str = f.toString();
		} else if (_vr==DiDi.FD) {
			int tmp = (_values[7]<<56 | _values[6]<<48 | _values[5]<<40 | _values[0]<<32 |
					_values[3]<<24 | _values[2]<<16 | _values[1]<<8 | _values[0]);
			Double d = Double.valueOf(Double.longBitsToDouble(tmp));
			str = d.toString();
		} else if (_vr==DiDi.SL) {
			int tmp = (_values[3]<<24 | _values[2]<<16 | _values[1]<<8 | _values[0]);
			str = ""+tmp;
		} else if (_vr==DiDi.SQ) {
			str = "TODO";
		} else if (_vr==DiDi.SS) {
			int tmp = (_values[1]<<8 | _values[0]);
			str = ""+tmp;
		} else if (_vr==DiDi.UL) {
			long tmp = ((_values[3] & 0xFF)<<24 | (_values[2] & 0xFF)<<16
					| (_values[1] & 0xFF)<<8 | (_values[0] & 0xFF));
			str = ""+tmp;
		} else if (_vr==DiDi.US) {
			int tmp = ((_values[1] & 0xFF)<<8 | (_values[0] & 0xFF));
			str = ""+tmp;
		} else {
			// supports: OB
			for (int i=0; i<_vl; i++) {
				str+=((int)(_values[i])+"|");
			}
		}

		return str;
	}
	
	/**
	 * Returns the vr tag as an integer value (faster for comparing).
	 * @return the vr tag as integer - compare with public DiDi constants
	 * @see DiDi
	 */
	public int getVR() {
		return _vr;
	}

	/**
	 * Sets the vr tag.
	 * 
	 * @param vr	the vr value - use only public DiDi constants here
	 * @see DiDi
	 */
	public void setVR(int vr) {
		this._vr = vr;
	}
	
	/**
	 * Returns the vr tag as an string value (human readable).
	 * 
	 * @return the vr tag as string
	 */
	public String getVRString() {
		return ""+(char)((_vr&0xff00)>>8)+""+(char)(_vr&0x00ff);
	}

	/**
	 * Returns the complete tag id (groub number,elementnumber) as an integer
	 * (fast comparing).
	 * 
	 * @return the tag id as an int
	 */
	public int getTag() {
		return (_groupid<<16 | _elementid);
	}


	/**
	 * Returns the complete tag id (groub number,elementnumber) as a string
	 * (human readable).
	 * 
	 * @return the tag id as a string
	 */
	public String getTagString() {
		return "("+my_format(_groupid)+","+my_format(_elementid)+")";
	}

	/**
	 * This function is not meant to be commented nor looked at.
	 * 
	 * @param num an integer
	 * @return a string
	 */
	private String my_format(int num) {
		String str = new String(Integer.toHexString(num));

		if (num<4096) str="0"+str;
		if (num<256) str="0"+str;
		if (num<16) str="0"+str;
		
		return str;
	}
	
}


