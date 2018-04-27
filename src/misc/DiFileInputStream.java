package misc;

import java.io.*;

/**
 * Represents a byte stream for DICOM files. Extends the default FileReader class.
 * Contains useful functions for handling DICOM files, such as skipHeader or getByte()
 * or the current byte position in the file.
 * 
 * @author Karl-Ingo Friese
 */
public class DiFileInputStream extends FileInputStream {
	private int _location; // position in the file, equal to the number of bytes read
	private boolean _little_endian;

	/**
	 * Default Constructor, argument is a string containing the file name. Endianess is little by default.
	 * 
	 * @param fname	the filename
	 * @throws IOException
	 */
	public DiFileInputStream(String fname) throws IOException {
		super(fname);
		_location = 0;
		_little_endian = true;
	}
	
	/**
	 * Constructor, argument is a file containing a DICOM image.
	 * 
	 * @param file the DICOM image file
	 * @throws IOException
	 */
	public DiFileInputStream(File file) throws IOException {
		super(file);
		_location = 0;
		_little_endian = true;
	}
	
	public int read() throws IOException {
        ++_location;
        return super.read();
	}
	
	/**
	 * Reads bytes.length bytes and is much faster than calling read() bytes.length times.
	 * 
	 * @param bytes the reference of a byte array
	 * @return the length of the bytes read
	 */
	public int read(byte[] bytes) throws IOException {
		int len = super.read(bytes);		
		_location += len;
		return len;
	}

	/**
	 * Reads a byte from the stream. Returns -1 if end of file
	 * is reached & throws an IOException.
	 * 
	 * @return the byte (as int value)
	 * @throws IOException
	 */
    int getByte() throws IOException {
        int b = read();

        return b;
    }

	/**
	 * Reads a short (= 2 bytes) from the stream. Returns -1 if end of file
	 * is reached & throws an IOException.
	 * 
	 * @return the short (as int value)
	 * @throws IOException
	 */
    public int getShort() throws IOException {
    	int result;
    	
        int b0 = getByte();
        int b1 = getByte();
        
        if (b0==-1 || b1==-1) {
        	result = -1;
        } else {
        	result = _little_endian ? ((b1 << 8) + b0) : ((b0 << 8) + b1);
        }
        
        return result;
    }
  
	/**
	 * Reads an int (= 4 bytes) from the stream. Returns -1 if end of file
	 * is reached & throws an IOException.
	 * 
	 * @return the int
	 * @throws IOException
	 */
    public int getInt() throws IOException {
    	int result;
    	
        int b0 = getByte();
        int b1 = getByte();
        int b2 = getByte();
        int b3 = getByte();

        if (b0==-1 || b1==-1 || b2==-1 || b3==-1) {
        	result = -1;
        } else {
        	result = _little_endian ? ((b3<<24) + (b2<<16) + (b1<<8) + b0) : ((b0<<24) + (b1<<16) + (b2<<8) + b3);
        }

        return result;
    }

	/**
	 * Skips the first 132 bytes. Returns true if it seems to be a dicom file.
	 * This method will be implemented in exercise 1.
	 * 
	 * @return true if the bytes 128-131 contained "DICM".
	 */
    public boolean skipHeader() throws IOException {
    	// exercise 1 - skip header, return true if prefix = DICM.
    	// dont forget to set the _location attribute !
    	
    	return false;
    }
    
    
    /**
     * Returns the image number (from tag 0020,0013) or -1 if the file did not
     * contain an image number. Can throw an exception if the stream was no valid
     * DICOM file.
     * 
     * @return the image number
     * @throws Exception
     */
    public int quickscan_for_image_number() throws Exception {
    	DiDataElement de = new DiDataElement();
    	
		do  {
			de.readNext(this);
		} while (de.getTag()<0x00200013);
    	
		return de.getValueAsInt();
    }
    
    /**
     * Returns the current location (= number of bytes read) of the stream.
     * @return
     */
    public int get_location() {
    	return _location;
    }
    
    /**
     * Sets little endian true or false.
     * 
     * @param little_endian true if little endian should be true
     */
    public void set_little_endian(boolean little_endian) {
    	_little_endian = little_endian;
    }
}

