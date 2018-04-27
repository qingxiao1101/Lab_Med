package misc;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import javax.swing.filechooser.*;

/**
 * This class is necessary for the file choose dialog.
 * Originally ExampleFileFilter from Jeff Dinkins, modified for DICOM usage.
 * Does not need to be looked at in the labor medizin :)
 * 
 * @author  Jeff Dinkins, heavily modified by Karl-Ingo Friese
 * 
 */
public class DiFileFilter extends FileFilter {
	// private static String TYPE_UNKNOWN = "Type Unknown";
	// private static String HIDDEN_FILE = "Hidden File";

	private Hashtable<String,DiFileFilter> _filters  = null;
	private String _description = null;
	private boolean _useExtensionsInDescription = true;

	/**
	 * Creates a file filter. If no filters are added, then all files are accepted.
	 */
	public DiFileFilter() {
		_filters = new Hashtable<String,DiFileFilter>();
	}

	/**
	 * Creates a file filter that accepts files with the given extension.
	 * Example: new ExampleFileFilter("jpg");
	 * 
	 * @param extension
	 */
	public DiFileFilter(String extension) {
		this(extension, null);
	}

	/**
	 * Creates a file filter that accepts the given file type.
	 * Example: new ExampleFileFilter("jpg", "JPEG Image Images");
	 * Note that the "." before the extension is not needed. If provided, it will be ignored.
	 * 
	 * @param extension
	 * @param description
	 */
	public DiFileFilter(String extension, String description) {
		this();
		if (extension != null) {
			addExtension(extension);
		}
		
		if (description != null) {
			setDescription(description);
		}
	}

	/**
	 * Creates a file filter from the given string array.
	 * Example: new ExampleFileFilter(String {"gif", "jpg"});
	 *
	 * Note that the "." before the extension is not needed adn
	 * will be ignored.
	 */
	public DiFileFilter(String[] filters) {
		this(filters, null);
	}

	/**
	 * Creates a file filter from the given string array and description.
	 * Example: new ExampleFileFilter(String {"gif", "jpg"}, "Gif and JPG Images");
	 *
	 * Note that the "." before the extension is not needed and will be ignored.
	 */
	public DiFileFilter(String[] filters, String description) {
		this();
		for (int i = 0; i < filters.length; i++) {
			// add filters one by one
			addExtension(filters[i]);
		}
		if (description != null)
			setDescription(description);
	}

	/**
	 * Return true if this file should be shown in the directory pane,
	 * false if it shouldn't.
	 *
	 * Files that begin with "." are ignored.
	 */
	public boolean accept(File f) {
		boolean result = false;
		
		if (f != null) {
			if (f.isDirectory()) {
				result = true;
			} else {
				try {
					DiFileInputStream dis = new DiFileInputStream(f);
					result = dis.skipHeader();
					dis.close();
				} catch (IOException ex) {
					// usualy: if a file is not accessible, dont show it, so exception can
					// be ignored. TODO: think if this is always the case :)
					// System.err.println(this.getClass()+": unhandled exception: accept" + ex);
				}
			}
		}
		
		return result;
	}

	/**
	 * Return the extension portion of the file's name .
	 */
	public String getExtension(File f) {
		if (f != null) {
			String filename = f.getName();
			int i = filename.lastIndexOf('.');
			if (i > 0 && i < filename.length() - 1) {
				return filename.substring(i + 1).toLowerCase();
			}
		}
		return null;
	}

	/**
	 * Adds a filetype "dot" extension to filter against.
	 *
	 * For example: the following code will create a filter that filters
	 * out all files except those that end in ".jpg" and ".tif":
	 * <pre>
	 *   ExampleFileFilter filter = new ExampleFileFilter();
	 *   filter.addExtension("jpg");
	 *   filter.addExtension("tif");
	 * </pre>
	 * Note that the "." before the extension is not needed and will be ignored.
	 */
	public void addExtension(String extension) {
		if (_filters == null) {
			_filters = new Hashtable<String,DiFileFilter>(5);
		}
		_filters.put(extension.toLowerCase(), this);
	}

	/**
	 * Returns the human readable description of this filter. For example: "JPEG and GIF Image Files (*.jpg, *.gif)"
	 */
	public String getDescription() {
		return _description;
	}

	/**
	 * Sets the human readable description of this filter. For example: filter.setDescription("Gif and JPG Images");
	 */
	public void setDescription(String description) {
		this._description = description;
	}

	/**
	 * Determines whether the extension list (.jpg, .gif, etc) should
	 * show up in the human readable description.
	 *
	 * Only relevent if a description was provided in the constructor
	 * or using setDescription();
	 */
	public void setExtensionListInDescription(boolean b) {
		_useExtensionsInDescription = b;
	}

	/**
	 * Returns whether the extension list (.jpg, .gif, etc) should
	 * show up in the human readable description.
	 *
	 * Only relevent if a description was provided in the constructor
	 * or using setDescription();
	 */
	public boolean isExtensionListInDescription() {
		return _useExtensionsInDescription;
	}
}
