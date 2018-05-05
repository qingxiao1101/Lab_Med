package misc;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.*;

import main.Message;
import main.Viewport;
import main.Segment;

import javax.media.j3d.*;

/**
 * Three dimensional viewport for viewing the dicom images + segmentations.
 * 
 * @author  Karl-Ingo Friese
 */
@SuppressWarnings("serial")
public class Viewport3d extends Viewport implements Observer  {
	/**
	 * Private class, implementing the GUI element for displaying the 3d data.
	 */
	public class Panel3d extends Canvas3D {
		public SimpleUniverse _simple_u;
		public BranchGroup _scene;
		
		
		public Panel3d(GraphicsConfiguration config){	
			super(config);
			setMinimumSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
			setMaximumSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
			setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
			setBackground(Color.black);
	                  

			_simple_u = new SimpleUniverse(this);
		    _simple_u.getViewingPlatform().setNominalViewingTransform();
		    _scene = null;
		    createScene();
		    super.getView().setTransparencySortingPolicy(View.TRANSPARENCY_SORT_GEOMETRY);
		}
	 
		public void createScene() {
			if (_scene != null) {
				_scene.detach( );
			}
			_scene = new BranchGroup();
			_scene.setCapability( BranchGroup.ALLOW_DETACH );
			
			// create a ColorCube object of size 0.5
			ColorCube c = new ColorCube(0.5f);
			
			// add ColorCube to SceneGraph
			_scene.addChild(c);
			
			_simple_u.addBranchGraph(_scene);
		}
			
	}		


	private Panel3d _panel3d;

	/**
	 * Constructor, with a reference to the global image stack as argument.
	 * @param slices a reference to the global image stack
	 */
	public Viewport3d() {
		super();
		
		this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
		this.setLayout( new BorderLayout() );
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		_panel3d = new Panel3d( config );		
        this.add(_panel3d, BorderLayout.CENTER );        
	}

	/**
	 * calculates the 3d data structurs.
	 */
	public void update_view() {
		_panel3d.createScene();
	}
	
	/**
	 * Implements the observer function update. Updates can be triggered by the global
	 * image stack.
	 */
	public void update(final Observable o, final Object obj) {
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
		
		if (m._type == Message.M_SEG_CHANGED) {
			String seg_name = ((Segment)(m._obj)).getName();
			boolean update_needed = _map_name_to_seg.containsKey(seg_name);
			if (update_needed) {
				update_view();
			}
		}
	}
}
