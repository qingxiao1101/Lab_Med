package main;

import java.util.*;
import java.io.*;
import javax.swing.*;

import com.sun.org.apache.bcel.internal.generic.NEW;

import misc.DiFile;
import misc.DiFileInputStream;

/**
 * The ImageStack class represents all DicomFiles of a series and its segments.
 * It is the global data structure in YaDiV.
 * This class is implemented as singleton, meaning the constructor is private.
 * Use getInstance() instead.
 * 
 * @author  Karl-Ingo Friese
 */
public class ImageStack extends Observable {
	private static ImageStack _instance = null;
	private Vector<DiFile> _dicom_files;
	private DefaultListModel<String> _seg_names;
	private Hashtable<String, Segment> _segment;
	private String _dir_name;
	private int _w, _h, _active;
	
	//for different model
	private Map<Integer,Integer[][]> _volum_pixel_data;
	private Vector<Integer[][]> _transversal_img;
	private Vector<Integer[][]> _sagittal_img;
	private Vector<Integer[][]> _frontal_img;
	
	public Vector<Integer[][]> get_transversal_img() {
		return _transversal_img;
	}
	public Integer[][] get_transversal_img(int key) {
		return _transversal_img.get(key);
	}
	public void set_transversal_img(Vector<Integer[][]> _transversal_img) {
		this._transversal_img = _transversal_img;
	}
	public Integer[][] get_sagittal_img(int i){
		return _sagittal_img.get(i);
	}
	public Vector<Integer[][]> get_sagittal_img(){
		return _sagittal_img;
	}
	public Integer[][] get_frontal_img(int i){
		return _frontal_img.get(i);
	}
	public Vector<Integer[][]> get_frontal_img(){
		return _frontal_img;
	}
	
	public Integer[][] get_volum_pixel_data(int i) {
		return _volum_pixel_data.get(i);
	}

	/**
	 * Default Constructor.
	 */
	private ImageStack() {
		_dicom_files = new Vector<DiFile>();
		_segment = new Hashtable<String, Segment>();
		_seg_names = new DefaultListModel<String>();
		_dir_name = new String();
		_active = 0;
		_volum_pixel_data = new HashMap<Integer, Integer[][]>();
		_sagittal_img = new Vector<Integer[][]>();
		_frontal_img = new Vector<Integer[][]>();
		_transversal_img = new Vector<Integer[][]>();
	}

	public static ImageStack getInstance() {
    	if (_instance==null) {
    		_instance = new ImageStack();
    	}
    	return _instance;
	}

	/**
	 * Reads all DICOM files from the given directory. All files are checked
	 * for correctness before loading. The load process is implemented as a thread.
	 * 
	 * @param dir_name	string contaning the directory name.
	 */
	public void initFromDirectory(String dir_name) {
		_dir_name = dir_name;
		_w = 0;
		_h = 0;
		
		// loading thread
		Thread t = new Thread() {
			JProgressBar progress_bar; //进度条
			
			// returns the image number of a dicom file or -1 if something wents wrong
			private int check_file(File file) {
				int result = -1;
				
				if (!file.isDirectory()) {
		        	try {		        		
		        		DiFileInputStream candidate = new DiFileInputStream(file);
		        		
			    		if (candidate.skipHeader()) {
			    			result = candidate.quickscan_for_image_number();
			    		}				    	
						candidate.close();
						
		    		} catch (Exception ex) {
						System.out.println("this will work after exercise 1");
		    			result = -1;
		    		}
				}				
	            return result;
			}
			
			// checks the DICOM files, retrieves their image number and loads them in the right order.
			public void run() {
				Hashtable<Integer, String> map_number_to_difile_name = new Hashtable<Integer, String>();
				DiFile df;
				
				setChanged();				
			    notifyObservers(new Message(Message.M_CLEAR));

				JFrame progress_win = new JFrame("checking ...");
				progress_win.setResizable(false);
				progress_win.setAlwaysOnTop(true);
				
				File dir = new File(_dir_name);
			    File[] files_unchecked = dir.listFiles();
			    
				progress_bar = new JProgressBar(0, files_unchecked.length);
				progress_bar.setValue(0);
				progress_bar.setStringPainted(true); //
				
				progress_win.add(progress_bar);
				
				progress_win.pack();  			
				// progress_bar.setIndeterminate(true);
				int main_width = (int)(LabMed.get_window().getSize().getWidth());
				int main_height = (int)(LabMed.get_window().getSize().getHeight());
				progress_win.setLocation((main_width-progress_win.getSize().width)/2, (main_height-progress_win.getSize().height)/2);
				progress_win.setVisible(true);		
			    for (int i=0; i<files_unchecked.length; i++) {
			    	int num = check_file(files_unchecked[i]);
			    	if (num >= 0) {
			    		map_number_to_difile_name.put(new Integer(num), files_unchecked[i].getAbsolutePath());			    					        		
		        	}
			    	progress_bar.setValue(i+1);
			    }
				
			    progress_win.setTitle("loading ...");
			    
				Enumeration<Integer> e = map_number_to_difile_name.keys();
		   	  	List<Integer> l = new ArrayList<Integer>();
		   	  	while(e.hasMoreElements()) {
		   	  		l.add((Integer)e.nextElement());
				}
				
			    String[] file_names = new String[l.size()];
		        Collections.sort(l);
		        Iterator<Integer> it = l.iterator();
		        int file_deeper = 0;
		        while (it.hasNext()) {
		        	file_names[file_deeper++] =  map_number_to_difile_name.get(it.next());		       	
		        }
		        
				progress_bar.setMaximum(file_names.length);
				progress_bar.setValue(0);

				_dicom_files.clear();
				_dicom_files.setSize(file_names.length);
				
				for (int i=0; i<file_names.length; i++) {
			    	df = new DiFile();
			    	try {
			    		df.initFromFile(file_names[i]);
			    	} catch (Exception ex) {
			    		System.out.println(getClass()+"::initFromDirectory -> failed to open "+file_names[i]);
			    		System.out.println(ex);
			    		System.exit(0);
			    	}
			    	progress_bar.setValue(i+1);
					_dicom_files.set(i, df);					
					// initialize default image width and heigth from the first image read
					if (_w==0) _w = df.getImageWidth();
					if (_h==0) _h = df.getImageHeight();
					
					preproccessPrimeData(df,i);
					setChanged();
				    notifyObservers(new Message(Message.M_NEW_IMAGE_LOADED));					
				}
			    
			    progress_win.setVisible(false);
			}
		};
		
		t.start();
	}

	/**
	 * Adds a new segment with the given name.
	 * 
	 * @param name	the name of the new segment (must be unique)
	 * @return		the new segment or null if the name was not unique
	 */
	public Segment addSegment(String name) {
		Segment seg;
		

		if (_segment.containsKey(name)) {
			seg = null;
		} else {
			int[] def_colors = {0xff0000, 0x00ff00, 0x0000ff};
			seg = new Segment(name, _w, _h, _dicom_files.size());
			seg.setColor(def_colors[_segment.size()]);	
			seg.setMinSlider(50); //exercise 3 
			seg.setMaxSlider(50);
			
			_segment.put(name, seg);
			_seg_names.addElement(name);
		}
		
		return seg;
	}
	
	
	/**
	 * Returns the DicomFile from the series with image number i; 
	 * 
	 * @param i	image number
	 * @return the DIOCM file
	 */
	public DiFile getDiFile(int i) {
		if (i<_dicom_files.size()) {
			return (DiFile)(_dicom_files.get(i));
		}
		
		return null;
	}
	
	public Vector<DiFile> getDicomFiles(){
		return _dicom_files;
	}
	/**
	 * Returns the segment with the given name.
	 * 
	 * @param name	the name of a segment
	 * @return		the segment
	 */
	public Segment getSegment(String name) {
		return (Segment)(_segment.get(name));
	}

	/**
	 * Returns the number of segments.
	 * 
	 * @return		the number of segments
	 */
	public int getSegmentNumber() {
		return _segment.size();
	}

	/**
	 * Returns the Number of DicomFiles in the ImageStack.
	 *   
	 * @return the number of files
	 */
	public int getNumberOfImages() {
		return _dicom_files.size();
	}
	
	/**
	 * Returns the DefaultListModel containing the segment names.
	 *   
	 * @return guess what
	 */
	public DefaultListModel<String> getSegNames() {
		return _seg_names;
	}

	/**
	 * Returns the width of the images in the image stack.
	 *   
	 * @return the image width
	 */
	public int getImageWidth() {
		return _w;
	}
	
	/**
	 * Returns the height of the images in the image stack.
	 *   
	 * @return the image height
	 */
	public int getImageHeight() {
		return _h;
	}
	
	/**
	 * Returns the currently active image.
	 * 
	 * @return the currently active image
	 */
	public int getActiveImageID() {
		return _active;
	}

	/**
	 * Sets the currently active image in the viewmode.
	 * 
	 * @param i			the active image
	 */
	public void setActiveImage(int i) {
		_active = i;

		setChanged();
	    notifyObservers(new Message(Message.M_NEW_ACTIVE_IMAGE, new Integer(i)));
	}
	
	/** observe slider value ; exercise 3
 	 * @author Xiao Tang
	 * @param seg
	 */
	void setSegSlider(Segment seg) {
		Enumeration<Segment> segs = _segment.elements();
		
		while (segs.hasMoreElements()) 	{
			if(segs.nextElement().getName()==seg.getName()) {
				this.getSegment(seg.getName()).setMaxSlider(seg.getMaxSlider());
				this.getSegment(seg.getName()).setMinSlider(seg.getMinSlider());
				
				setChanged();
			    notifyObservers(new Message(Message.M_SEG_SLIDER, new Segment(seg)));
			}			
		}		
	}
	void segChanged(Segment seg) {
		
		setChanged();
	    notifyObservers(new Message(Message.M_SEG_CHANGED, new Segment(seg)));
	}
	
	//////////
	/**
	 * initial different views model --aufgabe2.2
	 * @author Xiao Tang
	 */
	public void initThreeViewModel(int model) {
		if(this.getNumberOfImages()==0)
			return;
		setChanged();				
		notifyObservers(new Message(Message.M_CLEAR));
		switch(model) {
		case 0:{
			int width = this.getImageWidth(); //breite
			int high = this.getImageHeight(); //hoehe
			int img_num = this.getNumberOfImages();
			
			for(int w=0;w<img_num;w++) {
				Integer[][] transversal = new Integer[high][width];
				transversal = _volum_pixel_data.get(w);
				_transversal_img.addElement(transversal);
				
				setChanged();				
				notifyObservers(new Message(Message.M_NEW_IMAGE_LOADED));
			}
		}break;
		case 1:{
			int width = this.getImageHeight();
			int high = this.getNumberOfImages();
			int img_num = this.getImageWidth();
			
			for(int num=0;num<img_num;num++) {
				Integer[][] sagittal = new Integer[high][width];
				
				for(int layer=0;layer<_volum_pixel_data.size();layer++) {
					Integer[][] board = _volum_pixel_data.get(layer);
					for(int i=0;i<width;i++) {
						sagittal[layer][i] = board[num][i];
					}
				}
				_sagittal_img.addElement(sagittal);
				
				setChanged();				
				notifyObservers(new Message(Message.M_NEW_IMAGE_LOADED));
			}
		}break;
		case 2:{
			int width = this.getImageWidth();
			int high = this.getNumberOfImages();
					
			for(int h=0;h<this.getImageHeight();h++) {
				Integer[][] frontal = new Integer[high][width];			
				
				for(int layer=0;layer<_volum_pixel_data.size();layer++) {
					Integer[][] board = _volum_pixel_data.get(layer);
					for(int i=0;i<width;i++) {
						frontal[layer][i] = board[i][h];
					}
				}
				_frontal_img.addElement(frontal);
				
				setChanged();				
				notifyObservers(new Message(Message.M_NEW_IMAGE_LOADED));
			}
		}break;
		default: break;
		}		
	}
	
	/**
	 * @author Tang
	 * @param df
	 * @param active_id
	 */
	public void preproccessPrimeData(DiFile df,int active_id) {		
		int bits_allocated = df.getBitsAllocated();
		int slope = df.getSlope();
		int intercept = df.getIntercept();
		byte[] prime_data = new byte[(bits_allocated/8)*_w*_h];		
		prime_data = df.getElement(0x7FE00010).getValues();
			
		int[] prime_pixel = new int[_w*_h];
		int it = 0;
		Integer[][] board = new Integer[_h][_w];
		for(int i=0;i<(prime_data.length)/2;i++) {			
			prime_pixel[i] = (int)((prime_data[it+1] << 8)) + (int)((prime_data[it]));
			//prime_pixel[i] = slope*prime_pixel[i] + intercept; // original data modification
			board[i%_w][i/_w] = prime_pixel[i];
			it += 2;
		}
		_volum_pixel_data.put(active_id, board);	
	}
}

