package misc;

import java.util.*;

/**
 * Implements the internal representation of a DICOM file.
 * Stores all DataElements and makes them accessable via getDataElement(TagName).
 * Also stores the pixel data & important information for displaying the contained image in 
 * seperate variables with special access functions.
 * 
 * @author Karl-Ingo Friese
 */
public class DiFile {
	private int _w;
	private int _h;
	private int _bits_stored;
	private int _bits_allocated;
	private Hashtable<Integer, DiDataElement> _data_elements;
	private int _image_number;
	String _file_name;

	/**
	 * Default Construtor - creates an empty DicomFile.
	 */
	public DiFile () {
		_w = _h = _bits_stored = _bits_allocated = _image_number = 0;
		_data_elements = new Hashtable<Integer, DiDataElement>();
		_file_name = null;
	}

	/**
	 * Initializes the DicomFile from a file. Might throw an exception (unexpected
	 * end of file, wrong data etc).
	 * This method will be implemented in exercise 1.
	 * 
	 * @param file_name	a string containing the name of a valid dicom file
	 * @throws Exception
	 */
	public void initFromFile(String file_name) throws Exception {
		// exercise 1
		/*
		  _w= 0x00280010;
		  _h= 0x00280010;
		  _bits_stored= 0x00280100;
		  _bits_allocated= 0x00280101;
		  _image_number= 0x00200013;
		  */
		  _file_name=file_name;
		  DiFileInputStream Input = new DiFileInputStream(file_name);
		  if(Input.skipHeader())
		  {		 	
			  int tag;	
			  //System.out.println("ha");
		   do 
		   {		    	
			   DiDataElement DE = new DiDataElement();			
			   DE.readNext(Input);
			   System.out.println(DE.toString());
			   
			   tag = DE.getTag();
			   _data_elements.put(DE.getTag(),DE);
		   }
		   while(tag != 0x7FE00010); 
		   Input.close();
		   _w = getElement(0x00280011).getValueAsInt();
		   _h = getElement(0x00280010).getValueAsInt();
		   _bits_allocated = getElement(0x00280100).getValueAsInt();
		   _bits_stored = getElement(0x00280101).getValueAsInt();
		   _image_number = getElement(0x00200013).getValueAsInt();		   
		   //System.out.println(getElement(0x00200013).toString());
		   //System.out.println(getImageNumber());
		  }		  
	}

	/**
	 * Converts a dicom file into a human readable string info. Might be long.
	 * Useful for debugging.
	 * 
	 * @return		a human readable string representation
	 */
	public String toString() {
		String str = new String();
		
		str+=_file_name+"\n";
		Enumeration<Integer> e = _data_elements.keys();  //function same as iterator
   	  	List<String> l = new ArrayList<String>();

   	  	while(e.hasMoreElements()) {
		  Integer tag  = e.nextElement();
		  DiDataElement el = (DiDataElement)_data_elements.get(tag);
		  l.add(el.toString());
		}
		
        Collections.sort(l);
        Iterator<String> it = l.iterator();
        while (it.hasNext()) {
        	str += it.next();
        }

        return str;
	}

	/**
	 * Returns the number of allocated bits per pixel.
	 * @return the number of allocated bits.
	 */
	public int getBitsAllocated() {
		return _bits_allocated;
	}

	/**
	 * Returns the number of bits per pixel that are actually used for color info.
	 * @return the number of stored bits.
	 */
	public int getBitsStored() {
		return _bits_stored;
	}

	/**
	 * Allows access to the internal data element HashTable.
	 * @return a reference to the data element HashTable
	 * @see IntHashtable
	 */
	public Hashtable<Integer, DiDataElement> getDataElements() {
		return _data_elements;
	}

	/**
	 * Returns the DiDataElement with the given id. Can return null.
	 * @param id
	 * @return
	 */
	public DiDataElement getElement(int id) {
		return _data_elements.get(id);
	}

	/**
	 * Returns the image width of the contained dicom image.
	 * @return the image width
	 */
	public int getImageWidth() {
		return _w;
	}

	/**
	 * Returns the image height of the contained dicom image.
	 * @return the image height
	 */
	public int getImageHeight() {
		return _h;
	}

	/**
	 * Returns the file name of the current file.
	 * 
	 * @return the file name
	 */
	public String getFileName() {
		return _file_name;
	}
	
	/**
	 * Returns the image number in the current dicom series.
	 * 
	 * @return the image number
	 */
	public int getImageNumber() {
		return _image_number;
	}
}
