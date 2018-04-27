package misc;

import java.util.HashMap;

/**
 * Singleton class. Contains VR (value range) constants and tag descriptions.
 * 
 * @author kif
 */
public final class DiDi {
	// only use THESE constants as VR types. DO NOT HARDCODE VR identifiers.
    public static final int AE = 0x4145; // AE (Application Entity)
    public static final int AS = 0x4153; // AS (Age String)
    public static final int AT = 0x4154; // AT (Attribute Tag)
    public static final int CS = 0x4353; // CS (Code String)
    public static final int DA = 0x4441; // DA (Date)
    public static final int DS = 0x4453; // DS (Decimal String)
    public static final int DT = 0x4454; // DT (Date Time)
    public static final int FD = 0x4644; // FD (Floating Point Double)
    public static final int FL = 0x464C; // FL (Floating Point Single)
    public static final int IS = 0x4953; // IS (Integer String)
    public static final int LO = 0x4C4F; // LO (Long String)
    public static final int LT = 0x4C54; // LT (Long Text)
    public static final int PN = 0x504E; // PN (Patient Name)
    public static final int SH = 0x5348; // SH (Short String)
    public static final int SL = 0x534C; // SL (Signed Long)
    public static final int SS = 0x5353; // SS (Signed Short)
    public static final int ST = 0x5354; // ST (Short Text)
    public static final int TM = 0x544D; // TM (Time)
    public static final int UI = 0x5549; // UI (Unique Identifier)
    public static final int UL = 0x554C; // UL (Unsigned Long)
    public static final int US = 0x5553; // US (Unsigned Short)
    public static final int UT = 0x5554; // UT (Unlimited Text)
    public static final int OB = 0x4F42; // OB (Other Byte)
    public static final int OF = 0x4F46; // OF (Other Float)
    public static final int OW = 0x4F57; // OW (Other Word)
    public static final int SQ = 0x5351; // SQ (Sequence)

    // special types
    public static final int UN = 0x554E; // 
    public static final int QQ = 0x3F3F; // 
    public static final int OX = 0x4F58; //
    public static final int DL = 0x444C; //
    public static final int XX = 0x0000; //

    private final HashMap<Integer,  TagMetaData> _data_element_map = new HashMap<Integer, TagMetaData>();
    private final HashMap<String,  String> _media_storage_map = new HashMap<String, String>();
    private static DiDi _instance = new DiDi();

    /**
     * Private class for storing the tag meta data in the DiDi HashTable.
     * It contains only the value range and its description of a given
     * tag, the tag id itself is stored in the global DiDi HashTable.
     * @author kif
     *
     */
    private final class TagMetaData {
		public int _vr; 
		public String _descr;
		
		/**
		 * The one and only constructor for this class.
		 * 
		 * @param vr    the VR identifier
		 * @param descr the VR description
		 */
		public TagMetaData(final int vr, final String descr) {
			_vr = vr;
			_descr = descr;
		}
	}
	    
    /**
     * The default constructor is private (singleton).
     *
     */
    private DiDi() {
		super();

    	_media_storage_map.put("1.2.840.10008.1.3.10", "Media Storage Directory Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.1", "Computed Radiography Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.1.1", "Digital X-Ray Image Storage For Presentation");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.1.1.1", "Digital X-Ray Image Storage For Processing");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.1.2", "Digital Mammography Image Storage For Presentation");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.1.2.1", "Digital Mammography Image Storage For Processing");

    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.1.3", "Digital Intra-oral X-Ray Image Storage For Presentation");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.1.3.1", "Digital Intra-oral X-Ray Image Storage For Processing");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.2", "CT Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.2.1", "Enhanced CT Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.3.1", "Ultrasound Multi-frame Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.4", "MR Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.4.1", "Enhanced MR Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.4.2", "MR Spectroscopy Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.6.1", "Ultrasound Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.7", "Secondary Capture Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.7.1", "Multi-frame Single Bit Secondary Capture Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.7.2", "Secondary Capture Image Multi-frame Grayscale Byte Secondary Capture Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.7.3", "Multi-frame Grayscale Word Secondary Capture Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.7.4", "Multi-frame True Color Secondary Capture Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.9.1.1", "12-lead ECG Waveform Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.9.1.2", "General ECG Waveform Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.9.1.3", "Ambulatory ECG Waveform Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.9.2.1", "Hemodynamic Waveform Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.9.3.1", "Cardiac Electrophysiology Waveform Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.9.4.1", "Basic Voice Audio Waveform Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.11.1", "Grayscale Softcopy Presentation State Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.11.2", "Color Softcopy Presentation State Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.11.3", "Presentation State Storage");

    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.11.4", "Blending Softcopy Presentation State Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.12.1", "X-Ray Angiographic Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.12.1.1", "Enhanced XA Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.12.2", "X-Ray Radiofluoroscopic Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.12.2.1", "Enhanced XRF Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.13.1.1", "X-Ray 3D Angiographic Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.13.1.2", "X-Ray 3D Craniofacial Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.20", "Nuclear Medicine Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.66", "Raw Data Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.66.1", "Spatial Registration Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.66.2", "Spatial Fiducials Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.66.3", "Deformable Spatial Registration Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.66.4", "Segmentation Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.67", "Real World Value Mapping Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.77.1.1", "VL Endoscopic Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.77.1.1.1", "Video Endoscopic Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.77.1.2", "VL Microscopic Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.77.1.2.1", "Video Microscopic Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.77.1.3", "VL Slide-Coordinates Microscopic Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.77.1.4", "VL Photographic Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.77.1.4.1", "Video Photographic Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.77.1.5.1", "Ophthalmic Photography 8 Bit Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.77.1.5.2", "Ophthalmic Photography 16 Bit Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.77.1.5.3", "Stereometric Relationship Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.77.1.5.4", "Ophthalmic Tomography Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.88.11", "Basic Text SR Enhanced SR");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.88.22", "Basic Text SR");

    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.88.33", "Comprehensive SR");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.88.40", "Procedure Log");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.88.50", "Mammography CAD SR");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.88.59", "Key Object Selection Document");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.88.65", "Chest CAD SR");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.88.67", "X-Ray Radiation Dose SR");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.104.1", "Encapsulated PDF Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.128", "Positron Emission Tomography Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.481.1", "RT Image Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.481.2", "RT Dose Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.481.3", "RT Structure Set Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.481.4", "RT Beams Treatment Record Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.481.5", "RT Plan Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.481.6", "RT Brachy Treatment Record Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.481.7", "RT Treatment Summary Record Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.481.8", "RT Ion Plan Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.1.1.481.9", "RT Ion Beams Treatment Record Storage");
    	_media_storage_map.put("1.2.840.10008.5.1.4.38.1", "Hanging Protocol Storage");

		_data_element_map.put(0x00000000, new TagMetaData(UL, "Group 0000 Length"));
		_data_element_map.put(0x00000001, new TagMetaData(UL, "Group 0000 Length to End (RET)"));
		_data_element_map.put(0x00000002, new TagMetaData(UI, "Affected SOP Class UID"));
		_data_element_map.put(0x00000003, new TagMetaData(UI, "Requested SOP Class UID"));
		_data_element_map.put(0x00000010, new TagMetaData(SH, "Recognition Code (RET)"));
		_data_element_map.put(0x00000100, new TagMetaData(US, "Command Field"));
		_data_element_map.put(0x00000110, new TagMetaData(US, "Message ID"));
		_data_element_map.put(0x00000120, new TagMetaData(US, "Message Id being Responded to"));
		_data_element_map.put(0x00000200, new TagMetaData(AE, "Initiator (RET)"));
		_data_element_map.put(0x00000300, new TagMetaData(AE, "Receiver (RET)"));
		_data_element_map.put(0x00000400, new TagMetaData(AE, "Find Location (RET)"));
		_data_element_map.put(0x00000600, new TagMetaData(AE, "Move Destination"));
		_data_element_map.put(0x00000700, new TagMetaData(US, "Priority"));
		_data_element_map.put(0x00000800, new TagMetaData(US, "Data Set Type"));
		_data_element_map.put(0x00000850, new TagMetaData(US, "Number of Matches (RET)"));
		_data_element_map.put(0x00000860, new TagMetaData(US, "Response Sequence Number (RET)"));
		_data_element_map.put(0x00000900, new TagMetaData(US, "Status"));
		_data_element_map.put(0x00000901, new TagMetaData(AT, "Offending Element"));
		_data_element_map.put(0x00000902, new TagMetaData(LO, "Error Comment"));
		_data_element_map.put(0x00000903, new TagMetaData(US, "Error ID"));
		_data_element_map.put(0x00001000, new TagMetaData(UI, "Affected SOP Instance UID"));
		_data_element_map.put(0x00001001, new TagMetaData(UI, "Requested SOP Instance UID"));
		_data_element_map.put(0x00001002, new TagMetaData(US, "Event Type ID"));
		_data_element_map.put(0x00001005, new TagMetaData(AT, "Attribute Identifier List"));
		_data_element_map.put(0x00001008, new TagMetaData(US, "Action Type ID"));
		_data_element_map.put(0x00001012, new TagMetaData(UI, "Requested SOP Instance UID List"));
		_data_element_map.put(0x00001020, new TagMetaData(US, "Number of Remaining Sub-operations"));
		_data_element_map.put(0x00001021, new TagMetaData(US, "Number of Completed Sub-operations"));
		_data_element_map.put(0x00001022, new TagMetaData(US, "Number of Failed Sub-operations"));
		_data_element_map.put(0x00001023, new TagMetaData(US, "Number of Warning Sub-operations"));
		_data_element_map.put(0x00001030, new TagMetaData(AE, "Move Originator Application Entity Title"));
		_data_element_map.put(0x00001031, new TagMetaData(US, "Move Originator Message ID"));
		_data_element_map.put(0x00005010, new TagMetaData(LO, "Message Set ID (RET)"));
		_data_element_map.put(0x00005020, new TagMetaData(LO, "End Message Set ID (RET)"));
		_data_element_map.put(0x00020000, new TagMetaData(UL, "Group 0002 Length"));
		_data_element_map.put(0x00020001, new TagMetaData(OB, "File Meta Information Version"));
		_data_element_map.put(0x00020002, new TagMetaData(UI, "Media Stored SOP Class UID"));
		_data_element_map.put(0x00020003, new TagMetaData(UI, "Media Stored SOP Instance UID"));
		_data_element_map.put(0x00020010, new TagMetaData(UI, "Transfer Syntax UID"));
		_data_element_map.put(0x00020012, new TagMetaData(UI, "Implementation Class UID"));
		_data_element_map.put(0x00020013, new TagMetaData(SH, "Implementation Version Name"));
		_data_element_map.put(0x00020016, new TagMetaData(AE, "Source Application Entity Title"));
		_data_element_map.put(0x00020100, new TagMetaData(UI, "Private Information Creator UID"));
		_data_element_map.put(0x00020102, new TagMetaData(OB, "Private Information"));
		_data_element_map.put(0x00040000, new TagMetaData(UL, "Group 0004 Length"));
		_data_element_map.put(0x00041130, new TagMetaData(CS, "File-set ID"));
		_data_element_map.put(0x00041141, new TagMetaData(CS, "File-set Descriptor File File ID"));
		_data_element_map.put(0x00041142, new TagMetaData(CS, "File-set Descriptor File Format"));
		_data_element_map.put(0x00041200, new TagMetaData(UL, "Root Directory Entity's First Directory Record Offset"));
		_data_element_map.put(0x00041202, new TagMetaData(UL, "Root Directory Entity's Last Directory Record Offset"));
		_data_element_map.put(0x00041212, new TagMetaData(US, "File-set Consistence Flag"));
		_data_element_map.put(0x00041220, new TagMetaData(SQ, "Directory Record Sequence"));
		_data_element_map.put(0x00041400, new TagMetaData(UL, "Next Directory Record Offset"));
		_data_element_map.put(0x00041410, new TagMetaData(US, "Record In-use Flag"));
		_data_element_map.put(0x00041420, new TagMetaData(UL, "Referenced Lower-level Directory Entity Offset"));
		_data_element_map.put(0x00041430, new TagMetaData(CS, "Directory Record Type"));
		_data_element_map.put(0x00041432, new TagMetaData(UI, "Private Record UID"));
		_data_element_map.put(0x00041500, new TagMetaData(CS, "Referenced File ID"));
		_data_element_map.put(0x00041510, new TagMetaData(UI, "Referenced SOP Class UID in File"));
		_data_element_map.put(0x00041511, new TagMetaData(UI, "Referenced SOP Instance UID in File"));
		_data_element_map.put(0x00041600, new TagMetaData(UL, "Number of References"));
		_data_element_map.put(0x00080000, new TagMetaData(UL, "Group 0008 Length"));
		_data_element_map.put(0x00080001, new TagMetaData(UL, "Group 0008 Length to End (RET)"));
		_data_element_map.put(0x00080005, new TagMetaData(CS, "Specific Character Set"));
		_data_element_map.put(0x00080008, new TagMetaData(CS, "Image Type"));
		_data_element_map.put(0x00080010, new TagMetaData(SH, "Recognition Code (RET)"));
		_data_element_map.put(0x00080012, new TagMetaData(DA, "Instance Creation Date"));
		_data_element_map.put(0x00080013, new TagMetaData(TM, "Instance Creation Time"));
		_data_element_map.put(0x00080014, new TagMetaData(UI, "Instance Creator UID"));
		_data_element_map.put(0x00080016, new TagMetaData(UI, "SOP Class UID"));
		_data_element_map.put(0x00080018, new TagMetaData(UI, "SOP Instance UID"));
		_data_element_map.put(0x00080020, new TagMetaData(DA, "Study Date"));
		_data_element_map.put(0x00080021, new TagMetaData(DA, "Series Date"));
		_data_element_map.put(0x00080022, new TagMetaData(DA, "Acquisition Date"));
		_data_element_map.put(0x00080023, new TagMetaData(DA, "Image Date"));
		_data_element_map.put(0x00080024, new TagMetaData(DA, "Overlay Date"));
		_data_element_map.put(0x00080025, new TagMetaData(DA, "Curve Date"));
		_data_element_map.put(0x00080030, new TagMetaData(TM, "Study Time"));
		_data_element_map.put(0x00080031, new TagMetaData(TM, "Series Time"));
		_data_element_map.put(0x00080032, new TagMetaData(TM, "Acquisition Time"));
		_data_element_map.put(0x00080033, new TagMetaData(TM, "Image Time"));
		_data_element_map.put(0x00080034, new TagMetaData(TM, "Overlay Time"));
		_data_element_map.put(0x00080035, new TagMetaData(TM, "Curve Time"));
		_data_element_map.put(0x00080040, new TagMetaData(US, "Data Set Type (RET)"));
		_data_element_map.put(0x00080041, new TagMetaData(SH, "Data Set Subtype (RET)"));
		_data_element_map.put(0x00080042, new TagMetaData(CS, "Nuclear Medicine Series Type"));
		_data_element_map.put(0x00080050, new TagMetaData(SH, "Accession Number"));
		_data_element_map.put(0x00080052, new TagMetaData(CS, "Query/Retrieve Level"));
		_data_element_map.put(0x00080054, new TagMetaData(AE, "Retrieve AE Title"));
		_data_element_map.put(0x00080058, new TagMetaData(AE, "Failed SOP Instance UID List"));
		_data_element_map.put(0x00080060, new TagMetaData(CS, "Modality"));
		_data_element_map.put(0x00080064, new TagMetaData(CS, "Conversion Type"));
		_data_element_map.put(0x00080070, new TagMetaData(LO, "Manufacturer"));
		_data_element_map.put(0x00080080, new TagMetaData(LO, "Institution Name"));
		_data_element_map.put(0x00080081, new TagMetaData(ST, "Institution Address"));
		_data_element_map.put(0x00080082, new TagMetaData(SQ, "Institution Code Sequence"));
		_data_element_map.put(0x00080090, new TagMetaData(PN, "Referring Physician's Name"));
		_data_element_map.put(0x00080092, new TagMetaData(ST, "Referring Physician's Address"));
		_data_element_map.put(0x00080094, new TagMetaData(SH, "Referring Physician's Telephone Numbers"));
		_data_element_map.put(0x00080100, new TagMetaData(SH, "Code Value"));
		_data_element_map.put(0x00080102, new TagMetaData(SH, "Coding Scheme Designator"));
		_data_element_map.put(0x00080104, new TagMetaData(LO, "Code Meaning"));
		_data_element_map.put(0x00081000, new TagMetaData(SH, "Network ID (RET)"));
		_data_element_map.put(0x00081010, new TagMetaData(SH, "Station Name"));
		_data_element_map.put(0x00081030, new TagMetaData(LO, "Study Description"));
		_data_element_map.put(0x00081032, new TagMetaData(SQ, "Procedure Code Sequence"));
		_data_element_map.put(0x0008103E, new TagMetaData(LO, "Series Description"));
		_data_element_map.put(0x00081040, new TagMetaData(LO, "Institutional Department Name"));
		_data_element_map.put(0x00081050, new TagMetaData(PN, "Attending Physician's Name"));
		_data_element_map.put(0x00081060, new TagMetaData(PN, "Name of Physician(s) Reading Study"));
		_data_element_map.put(0x00081070, new TagMetaData(PN, "Operator's Name"));
		_data_element_map.put(0x00081080, new TagMetaData(LO, "Admitting Diagnoses Description"));
		_data_element_map.put(0x00081084, new TagMetaData(SQ, "Admitting Diagnosis Code Sequence"));
		_data_element_map.put(0x00081090, new TagMetaData(LO, "Manufacturer's Model Name"));
		_data_element_map.put(0x00081100, new TagMetaData(SQ, "Referenced Results Sequence"));
		_data_element_map.put(0x00081110, new TagMetaData(SQ, "Referenced Study Sequence"));
		_data_element_map.put(0x00081111, new TagMetaData(SQ, "Referenced Study Component Sequence"));
		_data_element_map.put(0x00081115, new TagMetaData(SQ, "Referenced Series Sequence"));
		_data_element_map.put(0x00081120, new TagMetaData(SQ, "Referenced Patient Sequence"));
		_data_element_map.put(0x00081125, new TagMetaData(SQ, "Referenced Visit Sequence"));
		_data_element_map.put(0x00081130, new TagMetaData(SQ, "Referenced Overlay Sequence"));
		_data_element_map.put(0x00081140, new TagMetaData(SQ, "Referenced Image Sequence"));
		_data_element_map.put(0x00081145, new TagMetaData(SQ, "Referenced Curve Sequence"));
		_data_element_map.put(0x00081150, new TagMetaData(UI, "Referenced SOP Class UID"));
		_data_element_map.put(0x00081155, new TagMetaData(UI, "Referenced SOP Instance UID"));
		_data_element_map.put(0x00082111, new TagMetaData(ST, "Derivation Description"));
		_data_element_map.put(0x00082112, new TagMetaData(SQ, "Source Image Sequence"));
		_data_element_map.put(0x00082120, new TagMetaData(SH, "Stage Name"));
		_data_element_map.put(0x00082122, new TagMetaData(IS, "Stage Number"));
		_data_element_map.put(0x00082124, new TagMetaData(IS, "Number of Stages"));
		_data_element_map.put(0x00082129, new TagMetaData(IS, "Number of Event Timers"));
		_data_element_map.put(0x00082128, new TagMetaData(IS, "View Number"));
		_data_element_map.put(0x0008212A, new TagMetaData(IS, "Number of Views in Stage"));
		_data_element_map.put(0x00082130, new TagMetaData(DS, "Event Elapsed Time(s)"));
		_data_element_map.put(0x00082132, new TagMetaData(LO, "Event Timer Name(s)"));
		_data_element_map.put(0x00082142, new TagMetaData(IS, "Start Trim"));
		_data_element_map.put(0x00082143, new TagMetaData(IS, "Stop Trim"));
		_data_element_map.put(0x00082144, new TagMetaData(IS, "Recommended Display Frame Rate"));
		_data_element_map.put(0x00082200, new TagMetaData(CS, "Transducer Position"));
		_data_element_map.put(0x00082204, new TagMetaData(CS, "Transducer Orientation"));
		_data_element_map.put(0x00082208, new TagMetaData(CS, "Anatomic Structure"));
		_data_element_map.put(0x00084000, new TagMetaData(SH, "Group 0008 Comments (RET)"));
		_data_element_map.put(0x00100000, new TagMetaData(UL, "Group 0010 Length"));
		_data_element_map.put(0x00100010, new TagMetaData(PN, "Patient's Name"));
		_data_element_map.put(0x00100020, new TagMetaData(LO, "Patient ID"));
		_data_element_map.put(0x00100021, new TagMetaData(LO, "Issuer of Patient ID"));
		_data_element_map.put(0x00100030, new TagMetaData(DA, "Patient's Birth Date"));
		_data_element_map.put(0x00100032, new TagMetaData(TM, "Patient's Birth Time"));
		_data_element_map.put(0x00100040, new TagMetaData(CS, "Patient's Sex"));
		_data_element_map.put(0x00100042, new TagMetaData(SH, "Patient's Social Security Number"));
		_data_element_map.put(0x00100050, new TagMetaData(SQ, "Patient's Insurance Plan Code Sequence"));
		_data_element_map.put(0x00101000, new TagMetaData(LO, "Other Patient IDs"));
		_data_element_map.put(0x00101001, new TagMetaData(PN, "Other Patient Names"));
		_data_element_map.put(0x00101005, new TagMetaData(PN, "Patient's Maiden Name"));
		_data_element_map.put(0x00101010, new TagMetaData(AS, "Patient's Age"));
		_data_element_map.put(0x00101020, new TagMetaData(DS, "Patient's Size"));
		_data_element_map.put(0x00101030, new TagMetaData(DS, "Patient's Weight"));
		_data_element_map.put(0x00101040, new TagMetaData(LO, "Patient's Address"));
		_data_element_map.put(0x00101050, new TagMetaData(SH, "Insurance Plan Identification (RET)"));
		_data_element_map.put(0x00101060, new TagMetaData(PN, "Patient's Mother's Maiden Name"));
		_data_element_map.put(0x00101080, new TagMetaData(LO, "Military Rank"));
		_data_element_map.put(0x00101081, new TagMetaData(LO, "Branch of Service"));
		_data_element_map.put(0x00101090, new TagMetaData(LO, "Medical Record Locator"));
		_data_element_map.put(0x00102000, new TagMetaData(LO, "Medical Alerts"));
		_data_element_map.put(0x00102110, new TagMetaData(LO, "Contrast Allergies"));
		_data_element_map.put(0x00102150, new TagMetaData(LO, "Country of Residence"));
		_data_element_map.put(0x00102152, new TagMetaData(LO, "Region of Residence"));
		_data_element_map.put(0x00102154, new TagMetaData(SH, "Patient's Telephone Numbers"));
		_data_element_map.put(0x00102160, new TagMetaData(SH, "Ethnic Group"));
		_data_element_map.put(0x00102180, new TagMetaData(SH, "Occupation"));
		_data_element_map.put(0x001021A0, new TagMetaData(CS, "Smoking Status"));
		_data_element_map.put(0x001021B0, new TagMetaData(LT, "Additional Patient History"));
		_data_element_map.put(0x001021C0, new TagMetaData(US, "Pregnancy Status"));
		_data_element_map.put(0x001021D0, new TagMetaData(DA, "Last Menstrual Date"));
		_data_element_map.put(0x001021F0, new TagMetaData(LO, "Patient's Religious Preference"));
		_data_element_map.put(0x00104000, new TagMetaData(LT, "Patient Comments"));
		_data_element_map.put(0x00180000, new TagMetaData(UL, "Group 0018 Length"));
		_data_element_map.put(0x00180010, new TagMetaData(LO, "Contrast/Bolus Agent"));
		_data_element_map.put(0x00180015, new TagMetaData(CS, "Body Part Examined"));
		_data_element_map.put(0x00180020, new TagMetaData(CS, "Scanning Sequence"));
		_data_element_map.put(0x00180021, new TagMetaData(CS, "Sequence Variant"));
		_data_element_map.put(0x00180022, new TagMetaData(CS, "Scan Options"));
		_data_element_map.put(0x00180023, new TagMetaData(CS, "MR Acquisition Type"));
		_data_element_map.put(0x00180024, new TagMetaData(SH, "Sequence Name"));
		_data_element_map.put(0x00180025, new TagMetaData(CS, "Angio Flag"));
		_data_element_map.put(0x00180030, new TagMetaData(LO, "Radionuclide"));
		_data_element_map.put(0x00180031, new TagMetaData(LO, "Radiopharmaceutical"));
		_data_element_map.put(0x00180032, new TagMetaData(DS, "Energy Window Centerline"));
		_data_element_map.put(0x00180033, new TagMetaData(DS, "Energy Window Total Width"));
		_data_element_map.put(0x00180034, new TagMetaData(LO, "Intervention Drug Name"));
		_data_element_map.put(0x00180035, new TagMetaData(TM, "Intervention Drug Start Time"));
		_data_element_map.put(0x00180040, new TagMetaData(IS, "Cine Rate"));
		_data_element_map.put(0x00180050, new TagMetaData(DS, "Slice Thickness"));
		_data_element_map.put(0x00180060, new TagMetaData(DS, "KVP"));
		_data_element_map.put(0x00180070, new TagMetaData(IS, "Counts Accumulated"));
		_data_element_map.put(0x00180071, new TagMetaData(CS, "Acquisition Termination Condition"));
		_data_element_map.put(0x00180072, new TagMetaData(DS, "Effective Series Duration"));
		_data_element_map.put(0x00180080, new TagMetaData(DS, "Repetition Time"));
		_data_element_map.put(0x00180081, new TagMetaData(DS, "Echo Time"));
		_data_element_map.put(0x00180082, new TagMetaData(DS, "Inversion Time"));
		_data_element_map.put(0x00180083, new TagMetaData(DS, "Number of Averages"));
		_data_element_map.put(0x00180084, new TagMetaData(DS, "Imaging Frequency"));
		_data_element_map.put(0x00180085, new TagMetaData(SH, "Imaged Nucleus"));
		_data_element_map.put(0x00180086, new TagMetaData(IS, "Echo Numbers(s)"));
		_data_element_map.put(0x00180087, new TagMetaData(DS, "Magnetic Field Strength"));
		_data_element_map.put(0x00180088, new TagMetaData(DS, "Spacing Between Slices"));
		_data_element_map.put(0x00180089, new TagMetaData(IS, "Number of Phase Encoding Steps"));
		_data_element_map.put(0x00180090, new TagMetaData(DS, "Data Collection Diameter"));
		_data_element_map.put(0x00180091, new TagMetaData(IS, "Echo Train Length"));
		_data_element_map.put(0x00180093, new TagMetaData(DS, "Percent Sampling"));
		_data_element_map.put(0x00180094, new TagMetaData(DS, "Percent Phase Field of View"));
		_data_element_map.put(0x00180095, new TagMetaData(DS, "Pixel Bandwidth"));
		_data_element_map.put(0x00181000, new TagMetaData(LO, "Device Serial Number"));
		_data_element_map.put(0x00181004, new TagMetaData(LO, "Plate ID"));
		_data_element_map.put(0x00181010, new TagMetaData(LO, "Secondary Capture Device ID"));
		_data_element_map.put(0x00181012, new TagMetaData(DA, "Date of Secondary Capture"));
		_data_element_map.put(0x00181014, new TagMetaData(TM, "Time of Secondary Capture"));
		_data_element_map.put(0x00181016, new TagMetaData(LO, "Secondary Capture Device Manufacturer"));
		_data_element_map.put(0x00181018, new TagMetaData(LO, "Secondary Capture Device Manufacturer's Model Name"));
		_data_element_map.put(0x00181019, new TagMetaData(LO, "Secondary Capture Device Software Version(s)"));
		_data_element_map.put(0x00181020, new TagMetaData(LO, "Software Versions(s)"));
		_data_element_map.put(0x00181022, new TagMetaData(SH, "Video Image Format Acquired"));
		_data_element_map.put(0x00181023, new TagMetaData(LO, "Digital Image Format Acquired"));
		_data_element_map.put(0x00181030, new TagMetaData(LO, "Protocol Name"));
		_data_element_map.put(0x00181040, new TagMetaData(LO, "Contrast/Bolus Route"));
		_data_element_map.put(0x00181041, new TagMetaData(DS, "Contrast/Bolus Volume"));
		_data_element_map.put(0x00181042, new TagMetaData(TM, "Contrast/Bolus Start Time"));
		_data_element_map.put(0x00181043, new TagMetaData(TM, "Contrast/Bolus Stop Time"));
		_data_element_map.put(0x00181044, new TagMetaData(DS, "Contrast/Bolus Total Dose"));
		_data_element_map.put(0x00181045, new TagMetaData(IS, "Syringe Counts"));
		_data_element_map.put(0x00181050, new TagMetaData(DS, "Spatial Resolution"));
		_data_element_map.put(0x00181060, new TagMetaData(DS, "Trigger Time"));
		_data_element_map.put(0x00181061, new TagMetaData(LO, "Trigger Source or Type"));
		_data_element_map.put(0x00181062, new TagMetaData(IS, "Nominal Interval"));
		_data_element_map.put(0x00181063, new TagMetaData(DS, "Frame Time"));
		_data_element_map.put(0x00181064, new TagMetaData(LO, "Framing Type"));
		_data_element_map.put(0x00181065, new TagMetaData(DS, "Frame Time Vector"));
		_data_element_map.put(0x00181066, new TagMetaData(DS, "Frame Delay"));
		_data_element_map.put(0x00181070, new TagMetaData(LO, "Radionuclide Route"));
		_data_element_map.put(0x00181071, new TagMetaData(DS, "Radionuclide Volume"));
		_data_element_map.put(0x00181072, new TagMetaData(TM, "Radionuclide Start Time"));
		_data_element_map.put(0x00181073, new TagMetaData(TM, "Radionuclide Stop Time"));
		_data_element_map.put(0x00181074, new TagMetaData(DS, "Radionuclide Total Dose"));
		_data_element_map.put(0x00181080, new TagMetaData(CS, "Beat Rejection Flag"));
		_data_element_map.put(0x00181081, new TagMetaData(IS, "Low R-R Value"));
		_data_element_map.put(0x00181082, new TagMetaData(IS, "High R-R Value"));
		_data_element_map.put(0x00181083, new TagMetaData(IS, "Intervals Acquired"));
		_data_element_map.put(0x00181084, new TagMetaData(IS, "Intervals Rejected"));
		_data_element_map.put(0x00181085, new TagMetaData(LO, "PVC Rejection"));
		_data_element_map.put(0x00181086, new TagMetaData(IS, "Skip Beats"));
		_data_element_map.put(0x00181088, new TagMetaData(IS, "Heart Rate"));
		_data_element_map.put(0x00181090, new TagMetaData(IS, "Cardiac Number of Images"));
		_data_element_map.put(0x00181094, new TagMetaData(IS, "Trigger Window"));
		_data_element_map.put(0x00181100, new TagMetaData(DS, "Reconstruction Diameter"));
		_data_element_map.put(0x00181110, new TagMetaData(DS, "Distance Source to Detector"));
		_data_element_map.put(0x00181111, new TagMetaData(DS, "Distance Source to Patient"));
		_data_element_map.put(0x00181120, new TagMetaData(DS, "Gantry/Detector Tilt"));
		_data_element_map.put(0x00181030, new TagMetaData(DS, "Table Height"));
		_data_element_map.put(0x00181131, new TagMetaData(DS, "Table Traverse"));
		_data_element_map.put(0x00181140, new TagMetaData(CS, "Rotation Direction"));
		_data_element_map.put(0x00181141, new TagMetaData(DS, "Angular Position"));
		_data_element_map.put(0x00181142, new TagMetaData(DS, "Radial Position"));
		_data_element_map.put(0x00181143, new TagMetaData(DS, "Scan Arc"));
		_data_element_map.put(0x00181144, new TagMetaData(DS, "Angular Step"));
		_data_element_map.put(0x00181145, new TagMetaData(DS, "Center of Rotation Offset"));
		_data_element_map.put(0x00181146, new TagMetaData(DS, "Rotation Offset"));
		_data_element_map.put(0x00181147, new TagMetaData(CS, "Field of View Shape"));
		_data_element_map.put(0x00181149, new TagMetaData(IS, "Field of View Dimensions(s)"));
		_data_element_map.put(0x00181150, new TagMetaData(IS, "Exposure Time"));
		_data_element_map.put(0x00181151, new TagMetaData(IS, "X-ray Tube Current"));
		_data_element_map.put(0x00181152, new TagMetaData(IS, "Exposure"));
		_data_element_map.put(0x00181160, new TagMetaData(SH, "Filter Type"));
		_data_element_map.put(0x00181170, new TagMetaData(IS, "Generator Power"));
		_data_element_map.put(0x00181180, new TagMetaData(SH, "Collimator/grid Name"));
		_data_element_map.put(0x00181181, new TagMetaData(CS, "Collimator Type"));
		_data_element_map.put(0x00181182, new TagMetaData(IS, "Focal Distance"));
		_data_element_map.put(0x00181183, new TagMetaData(DS, "X Focus Center"));
		_data_element_map.put(0x00181184, new TagMetaData(DS, "Y Focus Center"));
		_data_element_map.put(0x00181190, new TagMetaData(DS, "Focal Spot(s)"));
		_data_element_map.put(0x00181200, new TagMetaData(DA, "Date of Last Calibration"));
		_data_element_map.put(0x00181201, new TagMetaData(TM, "Time of Last Calibration"));
		_data_element_map.put(0x00181210, new TagMetaData(SH, "Convolution Kernel"));
		_data_element_map.put(0x00181240, new TagMetaData(DS, "Upper/Lower Pixel Values (RET)"));
		_data_element_map.put(0x00181242, new TagMetaData(IS, "Actual Frame Duration"));
		_data_element_map.put(0x00181243, new TagMetaData(IS, "Count Rate"));
		_data_element_map.put(0x00181250, new TagMetaData(SH, "Receiving Coil"));
		_data_element_map.put(0x00181151, new TagMetaData(SH, "Transmitting Coil"));
		_data_element_map.put(0x00181160, new TagMetaData(SH, "Screen Type"));
		_data_element_map.put(0x00181261, new TagMetaData(LO, "Phosphor Type"));
		_data_element_map.put(0x00181300, new TagMetaData(IS, "Scan Velocity"));
		_data_element_map.put(0x00181301, new TagMetaData(CS, "Whole Body Technique"));
		_data_element_map.put(0x00181302, new TagMetaData(IS, "Scan Length"));
		_data_element_map.put(0x00181310, new TagMetaData(US, "Acquisition Matrix"));
		_data_element_map.put(0x00181312, new TagMetaData(CS, "Phase Encoding Direction"));
		_data_element_map.put(0x00181314, new TagMetaData(DS, "Flip Angle"));
		_data_element_map.put(0x00181315, new TagMetaData(CS, "Variable Flip Angle Flag"));
		_data_element_map.put(0x00181316, new TagMetaData(DS, "SAR"));
		_data_element_map.put(0x00181318, new TagMetaData(DS, "dB/dt"));
		_data_element_map.put(0x00181400, new TagMetaData(LO, "Acquisition Device Processing Description"));
		_data_element_map.put(0x00181401, new TagMetaData(LO, "Acquisition Device Processing Code"));
		_data_element_map.put(0x00181402, new TagMetaData(CS, "Cassette Orientation"));
		_data_element_map.put(0x00181403, new TagMetaData(CS, "Cassette Size"));
		_data_element_map.put(0x00181404, new TagMetaData(US, "Exposures on Plate"));
		_data_element_map.put(0x00181405, new TagMetaData(IS, "Relative X-ray Exposure"));
		_data_element_map.put(0x00184000, new TagMetaData(SH, "Group 0018 Comments (RET)"));
		_data_element_map.put(0x00185000, new TagMetaData(SH, "Output Power"));
		_data_element_map.put(0x00185010, new TagMetaData(LO, "Transducer Data"));
		_data_element_map.put(0x00185012, new TagMetaData(DS, "Focus Depth"));
		_data_element_map.put(0x00185020, new TagMetaData(LO, "Preprocessing Function"));
		_data_element_map.put(0x00185021, new TagMetaData(LO, "Postprocessing Function"));
		_data_element_map.put(0x00185022, new TagMetaData(DS, "Mechanical Index"));
		_data_element_map.put(0x00185024, new TagMetaData(DS, "Thermal Index"));
		_data_element_map.put(0x00185026, new TagMetaData(DS, "Cranial Thermal Index"));
		_data_element_map.put(0x00185027, new TagMetaData(DS, "Soft Tissue Thermal Index"));
		_data_element_map.put(0x00185028, new TagMetaData(DS, "Soft Tissue-focus Thermal Index"));
		_data_element_map.put(0x00185029, new TagMetaData(DS, "Soft Tissue-surface Thermal Index"));
		_data_element_map.put(0x00185030, new TagMetaData(IS, "Dynamic Range (RET)"));
		_data_element_map.put(0x00185040, new TagMetaData(IS, "Total Gain (RET)"));
		_data_element_map.put(0x00185050, new TagMetaData(IS, "Depth of Scan Field"));
		_data_element_map.put(0x00185100, new TagMetaData(CS, "Patient Position"));
		_data_element_map.put(0x00185101, new TagMetaData(CS, "View Position"));
		_data_element_map.put(0x00185210, new TagMetaData(DS, "Image Transformation Matrix"));
		_data_element_map.put(0x00185212, new TagMetaData(DS, "Image Translation Vector"));
		_data_element_map.put(0x00186000, new TagMetaData(DS, "Sensitivity"));
		_data_element_map.put(0x00186011, new TagMetaData(SQ, "Sequence of Ultrasound Regions"));
		_data_element_map.put(0x00186012, new TagMetaData(US, "Region Spatial Format"));
		_data_element_map.put(0x00186014, new TagMetaData(US, "Region Data Type"));
		_data_element_map.put(0x00186016, new TagMetaData(UL, "Region Flags"));
		_data_element_map.put(0x00186018, new TagMetaData(UL, "Region Location Min X0"));
		_data_element_map.put(0x0018601A, new TagMetaData(UL, "Region Location Min Y0"));
		_data_element_map.put(0x0018601C, new TagMetaData(UL, "Region Location Max X1"));
		_data_element_map.put(0x0018601E, new TagMetaData(UL, "Region Location Max Y1"));
		_data_element_map.put(0x00186020, new TagMetaData(SL, "Reference Pixel X0"));
		_data_element_map.put(0x00186022, new TagMetaData(SL, "Reference Pixel Y0"));
		_data_element_map.put(0x00186024, new TagMetaData(US, "Physical Units X Direction"));
		_data_element_map.put(0x00186026, new TagMetaData(US, "Physical Units Y Direction"));
		_data_element_map.put(0x00181628, new TagMetaData(FD, "Reference Pixel Physical Value X"));
		_data_element_map.put(0x0018602A, new TagMetaData(FD, "Reference Pixel Physical Value Y"));
		_data_element_map.put(0x0018602C, new TagMetaData(FD, "Physical Delta X"));
		_data_element_map.put(0x0018602E, new TagMetaData(FD, "Physical Delta Y"));
		_data_element_map.put(0x00186030, new TagMetaData(UL, "Transducer Frequency"));
		_data_element_map.put(0x00186031, new TagMetaData(CS, "Transducer Type"));
		_data_element_map.put(0x00186032, new TagMetaData(UL, "Pulse Repetition Frequency"));
		_data_element_map.put(0x00186034, new TagMetaData(FD, "Doppler Correction Angle"));
		_data_element_map.put(0x00186036, new TagMetaData(FD, "Sterring Angle"));
		_data_element_map.put(0x00186038, new TagMetaData(UL, "Doppler Sample Volume X Position"));
		_data_element_map.put(0x0018603A, new TagMetaData(UL, "Doppler Sample Volume Y Position"));
		_data_element_map.put(0x0018603C, new TagMetaData(UL, "TM-Line Position X0"));
		_data_element_map.put(0x0018603E, new TagMetaData(UL, "TM-Line Position Y0"));
		_data_element_map.put(0x00186040, new TagMetaData(UL, "TM-Line Position X1"));
		_data_element_map.put(0x00186042, new TagMetaData(UL, "TM-Line Position Y1"));
		_data_element_map.put(0x00186044, new TagMetaData(US, "Pixel Component Organization"));
		_data_element_map.put(0x00186046, new TagMetaData(UL, "Pixel Component Organization"));
		_data_element_map.put(0x00186048, new TagMetaData(UL, "Pixel Component Range Start"));
		_data_element_map.put(0x0018604A, new TagMetaData(UL, "Pixel Component Range Stop"));
		_data_element_map.put(0x0018604C, new TagMetaData(US, "Pixel Component Physical Units"));
		_data_element_map.put(0x0018604E, new TagMetaData(US, "Pixel Component Data Type"));
		_data_element_map.put(0x00186050, new TagMetaData(UL, "Number of Table Break Points"));
		_data_element_map.put(0x00186052, new TagMetaData(UL, "Table of X Break Points"));
		_data_element_map.put(0x00186054, new TagMetaData(FD, "Table of Y Break Points"));
		_data_element_map.put(0x00200000, new TagMetaData(UL, "Group 0020 Length"));
		_data_element_map.put(0x0020000D, new TagMetaData(UI, "Study Instance UID"));
		_data_element_map.put(0x0020000E, new TagMetaData(UI, "Series Instance UID"));
		_data_element_map.put(0x00200010, new TagMetaData(SH, "Study ID"));
		_data_element_map.put(0x00200011, new TagMetaData(IS, "Series Number"));
		_data_element_map.put(0x00200012, new TagMetaData(IS, "Scquisition Number"));
		_data_element_map.put(0x00200013, new TagMetaData(IS, "Image Number"));
		_data_element_map.put(0x00200014, new TagMetaData(IS, "Isotope Number"));
		_data_element_map.put(0x00200015, new TagMetaData(IS, "Phase Number"));
		_data_element_map.put(0x00200016, new TagMetaData(IS, "Interval Number"));
		_data_element_map.put(0x00200017, new TagMetaData(IS, "Time Slot Number"));
		_data_element_map.put(0x00200018, new TagMetaData(IS, "Angle Number"));
		_data_element_map.put(0x00200020, new TagMetaData(CS, "Patient Orientation"));
		_data_element_map.put(0x00200022, new TagMetaData(US, "Overlay Number"));
		_data_element_map.put(0x00200024, new TagMetaData(US, "Curve Number"));
		_data_element_map.put(0x00200030, new TagMetaData(DS, "Image Position (RET)"));
		_data_element_map.put(0x00200032, new TagMetaData(DS, "Image Position (Patient)"));
		_data_element_map.put(0x00200035, new TagMetaData(DS, "Image Orientation (RET)"));
		_data_element_map.put(0x00200037, new TagMetaData(DS, "Image Orientation (Patient)"));
		_data_element_map.put(0x00200050, new TagMetaData(DS, "Location (RET)"));
		_data_element_map.put(0x00200052, new TagMetaData(UI, "Frame of Reference UID"));
		_data_element_map.put(0x00200060, new TagMetaData(CS, "Laterality"));
		_data_element_map.put(0x00200070, new TagMetaData(SH, "Image Geometry Type (RET)"));
		_data_element_map.put(0x00200080, new TagMetaData(UI, "Masking Image UID"));
		_data_element_map.put(0x00200100, new TagMetaData(IS, "Temporal Position Identifier"));
		_data_element_map.put(0x00200105, new TagMetaData(IS, "Number of Temporal Positions"));
		_data_element_map.put(0x00200110, new TagMetaData(DS, "Temporal Resolution"));
		_data_element_map.put(0x00201000, new TagMetaData(IS, "Series in Study"));
		_data_element_map.put(0x00201001, new TagMetaData(IS, "Acquisitions in Series (RET)"));
		_data_element_map.put(0x00201002, new TagMetaData(IS, "Images in Acquisition"));
		_data_element_map.put(0x00201004, new TagMetaData(IS, "Acquisition in Study"));
		_data_element_map.put(0x00201020, new TagMetaData(SH, "Reference (RET)"));
		_data_element_map.put(0x00201040, new TagMetaData(LO, "Position Reference Indicator"));
		_data_element_map.put(0x00201041, new TagMetaData(DS, "Slice Location"));
		_data_element_map.put(0x00201070, new TagMetaData(IS, "Other Study Numbers"));
		_data_element_map.put(0x00201200, new TagMetaData(IS, "Number of Patient Related Studies"));
		_data_element_map.put(0x00201202, new TagMetaData(IS, "Number of Patient Related Series"));
		_data_element_map.put(0x00201204, new TagMetaData(IS, "Number of Patient Related Images"));
		_data_element_map.put(0x00201206, new TagMetaData(IS, "Number of Study Related Series"));
		_data_element_map.put(0x00201208, new TagMetaData(IS, "Number of Study Related Images"));
		_data_element_map.put(0x00203100, new TagMetaData(SH, "Source Image ID (RET)s"));
		_data_element_map.put(0x00203401, new TagMetaData(SH, "Modifying Device ID (RET)"));
		_data_element_map.put(0x00203402, new TagMetaData(SH, "Modified Image ID (RET)"));
		_data_element_map.put(0x00203403, new TagMetaData(SH, "Modified Image Date (RET)"));
		_data_element_map.put(0x00203404, new TagMetaData(SH, "Modifying Device Manufacturer (RET)"));
		_data_element_map.put(0x00203405, new TagMetaData(SH, "Modified Image Time (RET)"));
		_data_element_map.put(0x00203406, new TagMetaData(SH, "Modified Image Description (RET)"));
		_data_element_map.put(0x00204000, new TagMetaData(LT, "Image Comments"));
		_data_element_map.put(0x00205000, new TagMetaData(US, "Original Image Identification (RET)"));
		_data_element_map.put(0x00205002, new TagMetaData(SH, "Original Image Identification Nomenclature (RET)"));
		_data_element_map.put(0x00280000, new TagMetaData(UL, "Group 0028 Length"));
		_data_element_map.put(0x00280002, new TagMetaData(US, "Samples per Pixel"));
		_data_element_map.put(0x00280004, new TagMetaData(CS, "Photometric Interpretation"));
		_data_element_map.put(0x00280005, new TagMetaData(US, "Image Dimensions (RET)"));
		_data_element_map.put(0x00280006, new TagMetaData(US, "Planar Configuration"));
		_data_element_map.put(0x00280008, new TagMetaData(IS, "Number of Frames"));
		_data_element_map.put(0x00280009, new TagMetaData(AT, "Frame Increment Pointer"));
		_data_element_map.put(0x00280010, new TagMetaData(US, "Rows"));
		_data_element_map.put(0x00280011, new TagMetaData(US, "Columns"));
		_data_element_map.put(0x00280030, new TagMetaData(DS, "Pixel Spacing"));
		_data_element_map.put(0x00280031, new TagMetaData(DS, "Zoom Factor"));
		_data_element_map.put(0x00280032, new TagMetaData(DS, "Zoom Center"));
		_data_element_map.put(0x00280034, new TagMetaData(IS, "Pixel Aspect Ratio"));
		_data_element_map.put(0x00280040, new TagMetaData(SH, "Image Format (RET)"));
		_data_element_map.put(0x00280050, new TagMetaData(SH, "Manipulated Image (RET)"));
		_data_element_map.put(0x00280051, new TagMetaData(CS, "Corrected Image"));
		_data_element_map.put(0x00280060, new TagMetaData(SH, "Compression Code (RET)"));
		_data_element_map.put(0x00280100, new TagMetaData(US, "Bits Allocated"));
		_data_element_map.put(0x00280101, new TagMetaData(US, "Bits Stored"));
		_data_element_map.put(0x00280102, new TagMetaData(US, "High Bit"));
		_data_element_map.put(0x00280103, new TagMetaData(US, "Pixel Representation"));
		_data_element_map.put(0x00280104, new TagMetaData(US, "Smallest Valid Pixel Value (RET)"));
		_data_element_map.put(0x00280105, new TagMetaData(US, "Largest Valid Pixel Value (RET)"));
		_data_element_map.put(0x00280106, new TagMetaData(US, "Smallest Image Pixel Value"));
		_data_element_map.put(0x00280107, new TagMetaData(US, "Largest Image Pixel Value"));
		_data_element_map.put(0x00280108, new TagMetaData(US, "Smallest Pixel Value in Series"));
		_data_element_map.put(0x00280109, new TagMetaData(US, "Largest Pixel Value in Series"));
		_data_element_map.put(0x00280120, new TagMetaData(US, "Pixel Padding Value"));
		_data_element_map.put(0x00280200, new TagMetaData(US, "Image Location (RET)"));
		_data_element_map.put(0x00281050, new TagMetaData(DS, "Window Center"));
		_data_element_map.put(0x00281051, new TagMetaData(DS, "Window Width"));
		_data_element_map.put(0x00281052, new TagMetaData(DS, "Rescale Intercept"));
		_data_element_map.put(0x00281053, new TagMetaData(DS, "Rescale Slope"));
		_data_element_map.put(0x00281054, new TagMetaData(LO, "Rescale Type"));
		_data_element_map.put(0x00281055, new TagMetaData(LO, "Window Center & Width Explanation"));
		_data_element_map.put(0x00281080, new TagMetaData(SH, "Gray Scale (RET)"));
		_data_element_map.put(0x00281100, new TagMetaData(US, "Gray Lookup Table Descriptor (RET)"));
		_data_element_map.put(0x00281101, new TagMetaData(US, "Red Palette Color Lookup Table Descriptor"));
		_data_element_map.put(0x00281102, new TagMetaData(US, "Green Palette Color Lookup Table Descriptor"));
		_data_element_map.put(0x00281103, new TagMetaData(US, "Blue Palette Color Lookup Table Descriptor"));
		_data_element_map.put(0x00281200, new TagMetaData(US, "Gray Lookup Table Data (RET)"));
		_data_element_map.put(0x00281201, new TagMetaData(US, "Red Palette Color Lookup Table Data"));
		_data_element_map.put(0x00281202, new TagMetaData(US, "Green Palette Color Lookup Table Data"));
		_data_element_map.put(0x00281203, new TagMetaData(US, "Blue Palette Color Lookup Table Data"));
		_data_element_map.put(0x00283000, new TagMetaData(SQ, "Modality LUT Sequence"));
		_data_element_map.put(0x00283002, new TagMetaData(US, "LUT Descriptor"));
		_data_element_map.put(0x00283003, new TagMetaData(LO, "LUT Explanation"));
		_data_element_map.put(0x00283004, new TagMetaData(LO, "Madality LUT Type"));
		_data_element_map.put(0x00283006, new TagMetaData(US, "LUT Data"));
		_data_element_map.put(0x00283010, new TagMetaData(SQ, "VOI LUT Sequence"));
		_data_element_map.put(0x00284000, new TagMetaData(SH, "Group 0028 Comments (RET)"));
		_data_element_map.put(0x00320000, new TagMetaData(UL, "Group 0032 Length"));
		_data_element_map.put(0x0032000A, new TagMetaData(CS, "Study Status ID"));
		_data_element_map.put(0x0032000C, new TagMetaData(CS, "Study Priority ID"));
		_data_element_map.put(0x00320012, new TagMetaData(LO, "Study ID Issuer"));
		_data_element_map.put(0x00320032, new TagMetaData(DA, "Study Verified Date"));
		_data_element_map.put(0x00320033, new TagMetaData(TM, "Study Verified Time"));
		_data_element_map.put(0x00320034, new TagMetaData(DA, "Study Read Date"));
		_data_element_map.put(0x00320035, new TagMetaData(TM, "Study Read Time"));
		_data_element_map.put(0x00321000, new TagMetaData(DA, "Scheduled Study Start Date"));
		_data_element_map.put(0x00321001, new TagMetaData(TM, "Scheduled Study Start Time"));
		_data_element_map.put(0x00321010, new TagMetaData(DA, "Scheduled Study Stop Date"));
		_data_element_map.put(0x00321011, new TagMetaData(TM, "Scheduled Study Stop Time"));
		_data_element_map.put(0x00321020, new TagMetaData(LO, "Scheduled Study Location"));
		_data_element_map.put(0x00321021, new TagMetaData(AE, "Scheduled Study Location AE Title(s)"));
		_data_element_map.put(0x00321030, new TagMetaData(LO, "Reason  for Study"));
		_data_element_map.put(0x00321032, new TagMetaData(PN, "Requesting Physician"));
		_data_element_map.put(0x00321033, new TagMetaData(LO, "Requesting Service"));
		_data_element_map.put(0x00321040, new TagMetaData(DA, "Study Arrival Date"));
		_data_element_map.put(0x00321041, new TagMetaData(TM, "Study Arrival Time"));
		_data_element_map.put(0x00321050, new TagMetaData(DA, "Study Completion Date"));
		_data_element_map.put(0x00321051, new TagMetaData(TM, "Study Completion Time"));
		_data_element_map.put(0x00321055, new TagMetaData(CS, "Study Component Status ID"));
		_data_element_map.put(0x00321060, new TagMetaData(LO, "Requested Procedure Description"));
		_data_element_map.put(0x00321064, new TagMetaData(SQ, "Requested Procedure Code Sequence"));
		_data_element_map.put(0x00321070, new TagMetaData(LO, "Requested Contrast Agent"));
		_data_element_map.put(0x00324000, new TagMetaData(LT, "Study Comments"));
		_data_element_map.put(0x00380000, new TagMetaData(UL, "Group 0038 Length"));
		_data_element_map.put(0x00380004, new TagMetaData(SQ, "Referenced Patient Alias Sequence"));
		_data_element_map.put(0x00380008, new TagMetaData(CS, "Visit Status ID"));
		_data_element_map.put(0x00380010, new TagMetaData(LO, "Admissin ID"));
		_data_element_map.put(0x00380011, new TagMetaData(LO, "Issuer of Admission ID"));
		_data_element_map.put(0x00380016, new TagMetaData(LO, "Route of Admissions"));
		_data_element_map.put(0x0038001A, new TagMetaData(DA, "Scheduled Admissin Date"));
		_data_element_map.put(0x0038001B, new TagMetaData(TM, "Scheduled Adission Time"));
		_data_element_map.put(0x0038001C, new TagMetaData(DA, "Scheduled Discharge Date"));
		_data_element_map.put(0x0038001D, new TagMetaData(TM, "Scheduled Discharge Time"));
		_data_element_map.put(0x0038001E, new TagMetaData(LO, "Scheduled Patient Institution Residence"));
		_data_element_map.put(0x00380020, new TagMetaData(DA, "Admitting Date"));
		_data_element_map.put(0x00380021, new TagMetaData(TM, "Admitting Time"));
		_data_element_map.put(0x00380030, new TagMetaData(DA, "Discharge Date"));
		_data_element_map.put(0x00380032, new TagMetaData(TM, "Discharge Time"));
		_data_element_map.put(0x00380040, new TagMetaData(LO, "Discharge Diagnosis Description"));
		_data_element_map.put(0x00380044, new TagMetaData(SQ, "Discharge Diagnosis Code Sequence"));
		_data_element_map.put(0x00380050, new TagMetaData(LO, "Special Needs"));
		_data_element_map.put(0x00380300, new TagMetaData(LO, "Current Patient Location"));
		_data_element_map.put(0x00380400, new TagMetaData(LO, "Patient's Institution Residence"));
		_data_element_map.put(0x00380500, new TagMetaData(LO, "Patient State"));
		_data_element_map.put(0x00384000, new TagMetaData(LT, "Visit Comments"));
		_data_element_map.put(0x00880000, new TagMetaData(UL, "Group 0088 Length"));
		_data_element_map.put(0x00880130, new TagMetaData(SH, "Storage Media File-set ID"));
		_data_element_map.put(0x00880140, new TagMetaData(UI, "Storage Media File-set UID"));
		_data_element_map.put(0x20000000, new TagMetaData(UL, "Group 2000 Length"));
		_data_element_map.put(0x20000010, new TagMetaData(IS, "Number of Copies"));
		_data_element_map.put(0x20000020, new TagMetaData(CS, "Print Priority"));
		_data_element_map.put(0x20000030, new TagMetaData(CS, "Medium Type"));
		_data_element_map.put(0x20000040, new TagMetaData(CS, "Film Destination"));
		_data_element_map.put(0x20000050, new TagMetaData(LO, "Film Session Label"));
		_data_element_map.put(0x20000060, new TagMetaData(IS, "Memory Allocation"));
		_data_element_map.put(0x20000500, new TagMetaData(SQ, "Referenced Film Box Sequence"));
		_data_element_map.put(0x20100000, new TagMetaData(UL, "Group 2010 Length"));
		_data_element_map.put(0x20100010, new TagMetaData(ST, "Smage Display Format"));
		_data_element_map.put(0x20100030, new TagMetaData(CS, "Annotation Display Format ID"));
		_data_element_map.put(0x20100040, new TagMetaData(CS, "Film Orientation"));
		_data_element_map.put(0x20100050, new TagMetaData(CS, "Film Size ID"));
		_data_element_map.put(0x20100060, new TagMetaData(CS, "Magnification Type"));
		_data_element_map.put(0x20100080, new TagMetaData(CS, "Smoothing Type"));
		_data_element_map.put(0x20100100, new TagMetaData(CS, "Border Density"));
		_data_element_map.put(0x20100110, new TagMetaData(CS, "Empty Image Density"));
		_data_element_map.put(0x20100120, new TagMetaData(US, "Min Density"));
		_data_element_map.put(0x20100130, new TagMetaData(US, "Max Density"));
		_data_element_map.put(0x20100140, new TagMetaData(CS, "Trim"));
		_data_element_map.put(0x20100150, new TagMetaData(ST, "Configuration Information"));
		_data_element_map.put(0x20100500, new TagMetaData(SQ, "Referenced Film Session Sequence"));
		_data_element_map.put(0x20100510, new TagMetaData(SQ, "Referenced Basic Image Box Sequence"));
		_data_element_map.put(0x20100520, new TagMetaData(SQ, "Referenced Basic Annotation Box Sequence"));
		_data_element_map.put(0x20200000, new TagMetaData(UL, "Group 2020 Length"));
		_data_element_map.put(0x20200010, new TagMetaData(US, "Image Position"));
		_data_element_map.put(0x20200020, new TagMetaData(CS, "Polarity"));
		_data_element_map.put(0x20200030, new TagMetaData(DS, "Requested Image Size"));
		_data_element_map.put(0x20200110, new TagMetaData(SQ, "Preformatted Greyscale Image Sequence"));
		_data_element_map.put(0x20200111, new TagMetaData(SQ, "Preformatted Color Image Sequence"));
		_data_element_map.put(0x20200130, new TagMetaData(SQ, "Referenced Image Overlay Box Sequence"));
		_data_element_map.put(0x20200140, new TagMetaData(SQ, "Referenced VOI LUT Sequence"));
		_data_element_map.put(0x20300000, new TagMetaData(UL, "Group 2030 Length"));
		_data_element_map.put(0x20300010, new TagMetaData(US, "Annotation Position"));
		_data_element_map.put(0x20300020, new TagMetaData(LO, "Text Object"));
		_data_element_map.put(0x20400000, new TagMetaData(UL, "Group 2040 Length"));
		_data_element_map.put(0x20400010, new TagMetaData(SQ, "Referenced Overlay Plane Sequence"));
		_data_element_map.put(0x20400011, new TagMetaData(US, "Refenced Overlay Plane Groups"));
		_data_element_map.put(0x20400060, new TagMetaData(CS, "Overlay Magnification Type"));
		_data_element_map.put(0x20400070, new TagMetaData(CS, "Overlay Smoothing Type"));
		_data_element_map.put(0x20400080, new TagMetaData(CS, "Overlay Foreground Density"));
		_data_element_map.put(0x20400090, new TagMetaData(CS, "overlay Mode"));
		_data_element_map.put(0x20400100, new TagMetaData(CS, "Threshold Density"));
		_data_element_map.put(0x20400500, new TagMetaData(SQ, "Referenced Image Box Sequence"));
		_data_element_map.put(0x21000000, new TagMetaData(UL, "Group 2100 Length"));
		_data_element_map.put(0x21000020, new TagMetaData(CS, "Execution Status"));
		_data_element_map.put(0x21000030, new TagMetaData(CS, "Execution Status Info"));
		_data_element_map.put(0x21000040, new TagMetaData(DA, "Creation Date"));
		_data_element_map.put(0x21000050, new TagMetaData(TM, "Creation Time"));
		_data_element_map.put(0x21000070, new TagMetaData(AE, "Originator"));
		_data_element_map.put(0x21000500, new TagMetaData(SQ, "Referenced Print Job Sequence"));
		_data_element_map.put(0x21100000, new TagMetaData(UL, "Group 2110 Length"));
		_data_element_map.put(0x21100010, new TagMetaData(CS, "Printer Status"));
		_data_element_map.put(0x21100020, new TagMetaData(CS, "Printer Status Info"));
		_data_element_map.put(0x21100030, new TagMetaData(ST, "Printer Name"));
		_data_element_map.put(0x40000000, new TagMetaData(UL, "Group 4000 Length (RET)"));
		_data_element_map.put(0x40000010, new TagMetaData(SH, "Arbitray (RET)"));
		_data_element_map.put(0x40004000, new TagMetaData(LT, "Group 4000 Comments (RET)"));
		_data_element_map.put(0x40080000, new TagMetaData(UL, "Group 4008 Length"));
		_data_element_map.put(0x40080040, new TagMetaData(SH, "Results ID"));
		_data_element_map.put(0x40080042, new TagMetaData(LO, "Results ID Issuer"));
		_data_element_map.put(0x40080050, new TagMetaData(SQ, "Referenced Interpretation Sequence"));
		_data_element_map.put(0x40080100, new TagMetaData(DA, "Interpretation Recorded Date"));
		_data_element_map.put(0x40080101, new TagMetaData(TM, "Interpretation Recorded Time"));
		_data_element_map.put(0x40080102, new TagMetaData(PN, "Interpretation Recorder"));
		_data_element_map.put(0x40080103, new TagMetaData(LO, "Reference to Recorded Sound"));
		_data_element_map.put(0x40080108, new TagMetaData(DA, "Interpretation Transcription Time"));
		_data_element_map.put(0x40080109, new TagMetaData(TM, "Interpretation Transcription Time"));
		_data_element_map.put(0x4008010A, new TagMetaData(PN, "Interpretation Transcriber"));
		_data_element_map.put(0x4008010B, new TagMetaData(ST, "Interpretation Text"));
		_data_element_map.put(0x4008010C, new TagMetaData(PN, "Interpretation Author"));
		_data_element_map.put(0x40080111, new TagMetaData(SQ, "Interpretation Approver Sequence"));
		_data_element_map.put(0x40080112, new TagMetaData(DA, "Interpretation Approval Date"));
		_data_element_map.put(0x40080113, new TagMetaData(TM, "Interpretation Approval Time"));
		_data_element_map.put(0x40080114, new TagMetaData(PN, "Physician Approving Interpretation"));
		_data_element_map.put(0x40080115, new TagMetaData(LT, "Interpretation Diagnosis Description"));
		_data_element_map.put(0x40080117, new TagMetaData(SQ, "Diagnosis Code Sequence"));
		_data_element_map.put(0x40080118, new TagMetaData(SQ, "Results Distribution List Sequence"));
		_data_element_map.put(0x40080119, new TagMetaData(PN, "Distribution Name"));
		_data_element_map.put(0x4008011A, new TagMetaData(LO, "Distribution Address"));
		_data_element_map.put(0x40080200, new TagMetaData(SH, "Interpretation ID"));
		_data_element_map.put(0x40080202, new TagMetaData(LO, "Interpretation ID Issuer"));
		_data_element_map.put(0x40080210, new TagMetaData(CS, "Interpretation Type ID"));
		_data_element_map.put(0x40080212, new TagMetaData(CS, "Interpretation Status ID"));
		_data_element_map.put(0x40080300, new TagMetaData(ST, "Impression"));
		_data_element_map.put(0x40084000, new TagMetaData(SH, "Group 4008 Comments"));
		_data_element_map.put(0x50000000, new TagMetaData(UL, "Group 5000 Length"));
		_data_element_map.put(0x50000005, new TagMetaData(US, "Curve Dimensions"));
		_data_element_map.put(0x50000010, new TagMetaData(US, "Number of Points"));
		_data_element_map.put(0x50000020, new TagMetaData(CS, "Type of Data"));
		_data_element_map.put(0x50000022, new TagMetaData(LO, "Curve Description"));
		_data_element_map.put(0x50000030, new TagMetaData(SH, "Axis Units"));
		_data_element_map.put(0x50000040, new TagMetaData(SH, "Axis Labels"));
		_data_element_map.put(0x50000103, new TagMetaData(US, "Data Value Representation"));
		_data_element_map.put(0x50000104, new TagMetaData(US, "Minimum Coordinate Value"));
		_data_element_map.put(0x50000105, new TagMetaData(US, "Maximum Coordinate Value"));
		_data_element_map.put(0x50000106, new TagMetaData(SH, "Curve Range"));
		_data_element_map.put(0x50000110, new TagMetaData(US, "Curve Data Descriptor"));
		_data_element_map.put(0x50000112, new TagMetaData(US, "Coordinate Start Value"));
		_data_element_map.put(0x50000114, new TagMetaData(US, "Coordinate Step Value"));
		_data_element_map.put(0x50002000, new TagMetaData(US, "Audio Type"));
		_data_element_map.put(0x50002002, new TagMetaData(US, "Audio Sample Format"));
		_data_element_map.put(0x50002004, new TagMetaData(US, "Number of Channels"));
		_data_element_map.put(0x50002006, new TagMetaData(UL, "Number of Samples"));
		_data_element_map.put(0x50002008, new TagMetaData(UL, "Sample Rate"));
		_data_element_map.put(0x5000200A, new TagMetaData(UL, "Total Time"));
		_data_element_map.put(0x5000200C, new TagMetaData(OX, "Audio Sample Data"));
		_data_element_map.put(0x5000200E, new TagMetaData(LT, "Audio Comments"));
		_data_element_map.put(0x50003000, new TagMetaData(OX, "Curve Data"));
		_data_element_map.put(0x60000000, new TagMetaData(UL, "Group 6000 Length"));
		_data_element_map.put(0x60000010, new TagMetaData(US, "Rows"));
		_data_element_map.put(0x60000011, new TagMetaData(US, "Columns"));
		_data_element_map.put(0x60000015, new TagMetaData(IS, "Number of Frames in Overlay"));
		_data_element_map.put(0x60000040, new TagMetaData(CS, "Overlay Type"));
		_data_element_map.put(0x60000050, new TagMetaData(SS, "Origin"));
		_data_element_map.put(0x60000060, new TagMetaData(SH, "Compression Code (RET)"));
		_data_element_map.put(0x60000100, new TagMetaData(US, "Bits Allocated"));
		_data_element_map.put(0x60000102, new TagMetaData(US, "Bit Position"));
		_data_element_map.put(0x60000110, new TagMetaData(SH, "Overlay Format (RET)"));
		_data_element_map.put(0x60000200, new TagMetaData(US, "Overlay Location (RET)"));
		_data_element_map.put(0x60001100, new TagMetaData(US, "Overlay Descriptor - Gray"));
		_data_element_map.put(0x60001101, new TagMetaData(US, "Overlay Descriptor - Red"));
		_data_element_map.put(0x60001102, new TagMetaData(US, "Overlay Descriptor - Green"));
		_data_element_map.put(0x60001103, new TagMetaData(US, "Overlay Descriptor - Blue"));
		_data_element_map.put(0x60001200, new TagMetaData(US, "Overlays - Gray"));
		_data_element_map.put(0x60001201, new TagMetaData(US, "Overlays - Red"));
		_data_element_map.put(0x60001202, new TagMetaData(US, "Overlays - Green"));
		_data_element_map.put(0x60001203, new TagMetaData(US, "Overlays - Blue"));
		_data_element_map.put(0x60001301, new TagMetaData(IS, "ROI Area"));
		_data_element_map.put(0x60001302, new TagMetaData(DS, "ROI Mean"));
		_data_element_map.put(0x60001303, new TagMetaData(DS, "ROI Standard Deviation"));
		_data_element_map.put(0x60003000, new TagMetaData(OW, "Overlay Data"));
		_data_element_map.put(0x60004000, new TagMetaData(SH, "Group 6000 Comments (RET)"));
		_data_element_map.put(0x7FE00000, new TagMetaData(UL, "Group 7FE0 Length"));
		_data_element_map.put(0x7FE00010, new TagMetaData(OX, "Pixel Data"));
		_data_element_map.put(0xFFFEE000, new TagMetaData(DL, "Item"));
		_data_element_map.put(0xFFFEE00D, new TagMetaData(DL, "Item Delimitation Item"));
		_data_element_map.put(0xFFFEE0DD, new TagMetaData(DL, "Sequence Delimitation Item"));
    }

    /**
     * Returns the value range (VR) of a given tag.
     * @param tag  the tag id (group number + element number)
     * @return     the value range
     */
	public static final int getVR(final int tag) {
		final TagMetaData meta_data = _instance._data_element_map.get(tag);
		int result;

		if (meta_data!=null) {	
			result = meta_data._vr;
		} else {
			result = XX;
		}

		return result;
	}

	/**
	 * Returns a string representation of the value range (VR) of a given tag.
     * @param tag  the tag id (group number + element number)
     * @return     a string representing the value range
	 */
	public static final String getVRstr(final int tag) {
		final TagMetaData meta_data = _instance._data_element_map.get(tag);
		final String result;
		
		if (meta_data!=null) {	
			result = ""+meta_data._vr;
		} else {
			result = "--";
		}
		
		return result;
	}

	
	/**
	 * Returns a description of the given tag (window width, patient birth, etc.).
     * @param tag  the tag id (group number + element number)
     * @return     a string containing a description of the tag
     */
	public static final String getTagDescr(final int tag) {
		final TagMetaData meta_data = _instance._data_element_map.get(tag);
		String result;
		
		if (meta_data!=null) {	
			result = ""+meta_data._descr;
		} else {
			result = "unknown";
		}

		return result;
	}
	
	public static final String getSopMediaDescr(final String id) {
		return _instance._media_storage_map.get(id);
	}
}
