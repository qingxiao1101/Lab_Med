package main;

/**
 * The Message class covers all messages for observable/observer
 * patterns within YaDiV.
 * 
 * @author Karl-Ingo Friese
 *
 */
public class Message {
	public static final int M_CLEAR            = 100;
	public static final int M_NEW_IMAGE_LOADED = 200;
	public static final int M_NEW_ACTIVE_IMAGE = 201;
	public static final int M_NEW_SEGMENTATION = 300;
	public static final int M_SEG_CHANGED 	   = 301;
	
	public int _type; 
	public Object _obj;
	
	
	/**
	 * Constructor with message type only.
	 * 
	 * @param type	the message type
	 */
	public Message(int type) {
		_type = type;
		_obj = null;
	}

	/**
	 * Constructor with message type and an additional object.
	 * 
	 * @param type	the message type
	 * @param obj	some messages may contain an object
	 */
	public Message(int type, Object obj) {
		_type = type;
		_obj = obj;
	}
}
