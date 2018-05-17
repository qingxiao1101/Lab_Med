package main;

import java.util.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import misc.DiFile;

/**
 * Two dimensional viewport for viewing the DICOM images + segmentations.
 * 
 * @author  Karl-Ingo Friese
 */
@SuppressWarnings("serial")
public class Viewport2d extends Viewport implements Observer {
	// the background image needs a pixel array, an image object and a MemoryImageSource
	private BufferedImage _bg_img;

	// each segmentation image needs the same, those are stored in a hashtable
	// and referenced by the segmentation name
	private Hashtable<String, BufferedImage> _map_seg_name_to_img;
	
	// this is the gui element where we actualy draw the images	
	private Panel2d _panel2d;
	
	// the gui element that lets us choose which image we want to show and
	// its data source (DefaultListModel)
	private ImageSelector _img_sel;
	private DefaultListModel<String> _slice_names;
	
	// width and heigth of our images. dont mix those with
	// Viewport2D width / height or Panel2d width / height!
	private int _w, _h;
	/**
	 * add some new variable in aufgabe2
	 */
	private int _viewmodel;
	private final int TRANSVERSAL = 0;
	private final int SAGITTAL = 1;
	private final int FRONTAL = 2;
	
	private int _min_slider,_max_slider;
	private String _seg_name;
	/**
	 * Private class, implementing the GUI element for displaying the 2d data.
	 * Implements the MouseListener Interface.
	 */
	public class Panel2d extends JPanel implements MouseListener {
		public Panel2d() {
			super();
			setMinimumSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
			setMaximumSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
			setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
			setBackground(Color.black);
			this.addMouseListener( this );
		}

		public void mouseClicked ( java.awt.event.MouseEvent e ) { 
			System.out.println("Panel2d::mouseClicked: x="+e.getX()+" y="+e.getY());
		}
		public void mousePressed ( java.awt.event.MouseEvent e ) {}
		public void mouseReleased( java.awt.event.MouseEvent e ) {}
		public void mouseEntered ( java.awt.event.MouseEvent e ) {}
		public void mouseExited  ( java.awt.event.MouseEvent e ) {}
	
		/**
		 * paint should never be called directly but via the repaint() method.
		 */
		public void paint(Graphics g) {
			g.drawImage(_bg_img, 0, 0, this.getWidth(), this.getHeight(), this);
			
			Enumeration<BufferedImage> segs = _map_seg_name_to_img.elements();	
			while (segs.hasMoreElements()) {
				g.drawImage(segs.nextElement(), 0, 0,  this.getWidth(), this.getHeight(), this);
			}
		}
	}
	
	/**
	 * Private class: The GUI element for selecting single DicomFiles in the View2D.
	 * Stores two references: the ImageStack (containing the DicomFiles)
	 * and the View2D which is used to show them.
	 * 
	 * @author kif
	 */
	private class ImageSelector extends JPanel {
		private JList<String> _jl_slices;
		private JScrollPane _jsp_scroll;
		
		/**
		 * Constructor with View2D and ImageStack reference.  
		 * The ImageSelector needs to know where to find the images and where to display them
		 */
		public ImageSelector() {
			_jl_slices = new JList<String>(_slice_names);
			//添加图片列表选择监听器
			_jl_slices.setSelectedIndex(0);
			_jl_slices.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			_jl_slices.addListSelectionListener(new ListSelectionListener(){
				/**
				 * valueChanged is called when the list selection changes.   
				 */
			    public void valueChanged(ListSelectionEvent e) {
			      	int slice_index = _jl_slices.getSelectedIndex();
			      	 
			       	if (slice_index>=0){
			       		_slices.setActiveImage(slice_index);
			       	}
				 }
			});
			//列表滚动
			_jsp_scroll = new JScrollPane(_jl_slices);			
			_jsp_scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			_jsp_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			
			setLayout(new BorderLayout());
			add(_jsp_scroll, BorderLayout.CENTER);
		}
	}
		
	/**
	 * Constructor, with a reference to the global image stack as argument.
	 * @param slices a reference to the global image stack
	 */
	public Viewport2d() {
		super();
		//_viewmodel = TRANSVERSAL;  // edition in exercise 2
		_slice_names = new DefaultListModel<String>();
		_slice_names.addElement(" ----- ");

		// create an empty 10x10 image as default
		_bg_img = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
		
		_map_seg_name_to_img = new Hashtable<String, BufferedImage>();

		// The image selector needs to know which images are to select
		_img_sel = new ImageSelector();

		setLayout( new BorderLayout() );
		_panel2d = new Panel2d();	//显示2d坐标位置数据	鼠标监听
        add(_panel2d, BorderLayout.CENTER );        
        add(_img_sel, BorderLayout.EAST );
		setPreferredSize(new Dimension(DEF_WIDTH+50,DEF_HEIGHT)); //设置首选尺寸
		
		_min_slider = 50; //exercise 3
		_max_slider = 50;
		_seg_name = new String();
	}


	/**
	 * This is private method is called when the current image width + height don't
	 * fit anymore (can happen after loading new DICOM series or switching viewmode).
	 * (see e.g. exercise 2)
	 */
	private void reallocate() {
		_w = _slices.getImageWidth();
		_h = _slices.getImageHeight();
		
		// create background image
		_bg_img = new BufferedImage(_w, _h, BufferedImage.TYPE_INT_ARGB);

		// create image for segment layers
		Enumeration<Segment> segs = _map_name_to_seg.elements();			
		while (segs.hasMoreElements()) {
			Segment seg = segs.nextElement();
			String name = seg.getName();
			BufferedImage seg_img = new BufferedImage(_w, _h, BufferedImage.TYPE_INT_ARGB);

			_map_seg_name_to_img.put(name, seg_img);
		}
	}
	
	/*
	 * Calculates the background image and segmentation layer images and forces a repaint.
	 * This function will be needed for several exercises after the first one.
	 * @see Viewport#update_view()
	 */
	public void update_view() {
		if (_slices.getNumberOfImages() == 0)
			return;
		// these are two variables you might need in exercise #2
		// int active_img_id = _slices.getActiveImageID();
		// DiFile active_file = _slices.getDiFile(active_img_id);
		
		// _w and _h need to be initialized BEFORE filling the image array !
		if (_bg_img==null || _bg_img.getWidth(null)!=_w || _bg_img.getHeight(null)!=_h) {
			reallocate();
		}
		
		// rendering the background picture
		if (_show_bg) { //父类里已经初始化为1
			// this is the place for the code displaying a single DICOM image
			// in the 2d viewport (exercise 2)
			//
			// the easiest way to set a pixel of an image is the setRGB method
			// example: _bg_img.setRGB(x,y, 0xff00ff00)
			//                                AARRGGBB
			// the resulting image will be used in the Panel2d::paint() method
			//active_file.getElement(0x00280004).getValueAsString()=="MONOCHROME2"						
			if(true) {
				//System.out.println(active_file.getElement(0x00280004).getValueAsString());
				switch(_viewmodel) {
				case TRANSVERSAL: 
					modusTransversal(); break;
				case SAGITTAL:
					modusSagittal(); break;
				case FRONTAL:
					modusFrontal(); break;
				default:break;		
				}
			}
						
		} else {
			// faster: access the data array directly (see below)
			final int[] bg_pixels = ((DataBufferInt) _bg_img.getRaster().getDataBuffer()).getData();
			for (int i=0; i<bg_pixels.length; i++) {
				bg_pixels[i] = 0xff000000;
			}
		}
		// rendering the segmentations. each segmentation is rendered in a
		// different image.
		Enumeration<Segment> segs = _map_name_to_seg.elements();
		while (segs.hasMoreElements()) {
			// here should be the code for displaying the segmentation data
			// (exercise 3)
			ImageStack is = LabMed.get_is();
			Segment seg = (Segment)(segs.nextElement());
			if(_seg_name==seg.getName()) {
				seg.create_range_seg(_max_slider,_min_slider,is);
				_map_name_to_seg.remove(seg.getName());
				_map_name_to_seg.put(seg.getName(), seg);
				System.out.println(seg.getName());
				displaySegment(seg);
			}
			
			/*
			for (int i=0; i<pixel.length; i++) {
				_bg_img.setRGB(i%_w,i/_w,pixel[i]);
			}*/
			 //int[] seg_pixels = ((DataBufferInt)_bg_img.getRaster().getDataBuffer()).getData();

			// to drawn a segmentation image, fill the pixel array seg_pixels
			// with ARGB values similar to exercise 2
		}		
		

		repaint();
	}
	

	/**
	 * Implements the observer function update. Updates can be triggered by the global
	 * image stack.
	 */
	public void update(final Observable o, final Object obj ) {
		if (!EventQueue.isDispatchThread()) {
			// all swing thingies must be done in the AWT-EventQueue 
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					update(o,obj);
				}
			});
			return;
		}

		// boolean update_needed = false;
		Message m = (Message)obj;
		
		if (m._type == Message.M_CLEAR) {
			// clear all slice info
			_slice_names.clear();
		}
		
		if (m._type == Message.M_NEW_IMAGE_LOADED) {
			// a new image was loaded and needs an entry in the ImageSelector's
			// DefaultListModel _slice_names
			String name = new String();
			int num = _slice_names.getSize();				
	    	name = ""+num;
			if (num<10) name = " "+name;				
			if (num<100) name = " "+name;		
			_slice_names.addElement(name);
			
			if (num==0) {
				// if the new image was the first image in the stack, make it active
				// (display it).
				reallocate();	
				//if(_viewmodel==TRANSVERSAL)
					_slices.setActiveImage(0);
				
			}			
		}
		
		if (m._type == Message.M_NEW_ACTIVE_IMAGE) {
			update_view();			
		}
		
		if (m._type == Message.M_SEG_CHANGED) {
			String seg_name = ((Segment)m._obj).getName();
			boolean update_needed = _map_name_to_seg.containsKey(seg_name);
			if (update_needed) {
				update_view();
			}
		}
		
		if (m._type == Message.M_SEG_SLIDER) {
			/*
			ArrayList<Integer> slider = (ArrayList<Integer>)m._obj;
			_max_slider = slider.get(0);
			_min_slider = slider.get(1);
			_if_segment = true;
			*/
			Segment seg = (Segment)m._obj;
			_seg_name = seg.getName();
			_max_slider = seg.getMaxSlider();
			_min_slider = seg.getMinSlider();
			update_view();
		}
	  }

	
	
    /**
	 * Returns the current file.
	 * 
	 * @return the currently displayed dicom file
	 */
	public DiFile currentFile() {
		return _slices.getDiFile(_slices.getActiveImageID());
	}

	/**
	 * Toggles if a segmentation is shown or not.
	 */
	public boolean toggleSeg(Segment seg) {
		String name = seg.getName();
		boolean gotcha = _map_name_to_seg.containsKey(name);
		
		if (!gotcha) {
			// if a segmentation is shown, we need to allocate memory for pixels
			BufferedImage seg_img = new BufferedImage(_w, _h, BufferedImage.TYPE_INT_ARGB);
			_map_seg_name_to_img.put(name, seg_img);
		} else {
			_map_seg_name_to_img.remove(name);
		}
		// most of the buerocracy is done by the parent viewport class
		super.toggleSeg(seg);
		return gotcha;
	}
	
	/**
	 * Sets the view mode (transversal, sagittal, frontal).
	 * This method will be implemented in exercise 2.
	 * 
	 * @param mode the new viewmode
	 */
	public void setViewMode(int mode) {
		// you should do something with the new viewmode here
		//System.out.println("Viewmode "+mode);
		switch(mode) {
		case 0:{
			_viewmodel = TRANSVERSAL;
		}break;			
		case 1:{
			_viewmodel = SAGITTAL;
			_slices.initSagittal(); //add in exercise 2 
		}break;			 
		case 2:{
			_viewmodel = FRONTAL;
			_slices.initFrontal();	//add in exercise 2
		}break;			
		default:break;
		}
		
		update_view();
	}

	private int[] dataProcess() {
		int active_img_id = _slices.getActiveImageID();
		int high_bit =_slices.getDiFile(0).getHighBit();		
		int max = 2<<high_bit;
		int window_center = max/2;				
		Integer[][] prime_pixel = new Integer[_h][_w];
		
		switch (_viewmodel) {		
		case TRANSVERSAL:{
			prime_pixel = _slices.get_volum_pixel_data(active_img_id);
		}	break;		
		case SAGITTAL:{
			if(_slices.get_sagittal_img().isEmpty())
				System.out.println("sagittal is empty!");
			System.out.println("active_img_id : "+active_img_id);
			prime_pixel = _slices.get_sagittal_img(active_img_id);
			System.out.println("width: "+_w+"  height: "+_h);
//			for(int i=0;i<_h;i++) {
//				for(int j=0;j<_w;j++) {
//					System.out.print(" "+prime_pixel[i][j]);
//				}
//				System.out.println(" ");	
//			}			
//			System.out.println("here!!!");
		}	break;

		default:
			break;
		}
						
		int[] scale_pixel = new int[_w*_h];
		int[] pixel = new int[scale_pixel.length];
		for(int i=0;i<_h;i++) {
			for(int j=0;j<_w;j++) {
				if(prime_pixel[i][j]<=(window_center -0.5 - (max-1)/2)) {
					scale_pixel[i*_w+j] = 0;
				}
				else if(prime_pixel[i][j] > (window_center -0.5 + (max-1)/2)) {
					scale_pixel[i*_w+j] = 255;
				}
				else {
					scale_pixel[i*_w+j] = (int)(((prime_pixel[i][j]-(window_center-0.5))/(max-1)+0.5)*(255-0)+0);
				}
				pixel[i*_w+j] = (0xff<<24) + (scale_pixel[i*_w+j]<<16) + (scale_pixel[i*_w+j]<<8) + scale_pixel[i*_w+j];
			}
		}
		return pixel;
	}
	/**
	 * set different model
	 * @author xiao; Tang
	 */
	public void modusTransversal() {
		System.out.println("Viewmode "+"Transversal");
		
		int active_img_id = _slices.getActiveImageID();
		DiFile active_file = _slices.getDiFile(active_img_id);
			
		_w = active_file.getImageWidth();
		_h = active_file.getImageHeight();
		_bg_img = new BufferedImage(_w, _h, BufferedImage.TYPE_INT_ARGB);
		
		int[] pixel = dataProcess();		
		
		//--------------------------------------------------------------
		for (int i=0; i<pixel.length; i++) {
			_bg_img.setRGB(i/_w,i%_w,pixel[i]);
			//_bg_img.setRGB(i%_w,i/_w,pixel[i]);
		}
		
		
		/*
		final int[] bg_pixels = ((DataBufferInt) _bg_img.getRaster().getDataBuffer()).getData();
		for (int i=0; i<bg_pixels.length; i++) {
			bg_pixels[i] = pixel[i];
		}	
		*/	
	}
	
	public void modusSagittal() {
		System.out.println("Viewmode "+"Sagittal");

		DiFile first_file = _slices.getDiFile(0);
		_w = first_file.getImageHeight();
		_h = _slices.getNumberOfImages();
		_bg_img = new BufferedImage(_w, _h, BufferedImage.TYPE_INT_ARGB);	
		int[] pixel = dataProcess();
		final int[] bg_pixels = ((DataBufferInt) _bg_img.getRaster().getDataBuffer()).getData();
		for (int i=0; i<bg_pixels.length; i++) {
			bg_pixels[i] = pixel[i];
			//bg_pixels[i] = 0xff000000;
		}
	}
	public void modusFrontal() {
		System.out.println("Viewmode "+"Frontal");
		
		DiFile first_file = _slices.getDiFile(0);
		_w = first_file.getImageWidth();
		_h = _slices.getNumberOfImages();
		_bg_img = new BufferedImage(_w, _h, BufferedImage.TYPE_INT_ARGB);
		
		final int[] bg_pixels = ((DataBufferInt) _bg_img.getRaster().getDataBuffer()).getData();
		for (int i=0; i<bg_pixels.length; i++) {
			bg_pixels[i] = 0xffffffff;
		}
	}

	public void displaySegment(Segment seg) {
		int active_img_id = _slices.getActiveImageID();
		int[] pixel = dataProcess();
		
		for (int i=0; i<pixel.length; i++) {
			if(seg.getMask(active_img_id).get(i/_w, i%_w)) {
				_bg_img.setRGB(i%_w,i/_w,pixel[i]);
			}else {
				_bg_img.setRGB(i%_w,i/_w,0xff000000);
			}			
		}
		/*
		 final int[] bg_pixels = ((DataBufferInt) _bg_img.getRaster().getDataBuffer()).getData();
			for (int i=0; i<bg_pixels.length; i++) {
				if(seg.getMask(active_img_id).get(i/_w, i%_w)) {
					bg_pixels[i] = pixel[i];
				}					
				else {
					bg_pixels[i] = 0xff000000;
				}
			}
		*/
	}
}
