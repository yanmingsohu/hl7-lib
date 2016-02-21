/* CatfoOD 2015 yanming-sohu@sohu.com */

package zr.hl7;

import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.w3c.dom.DOMException;

import ca.uhn.hl7v2.AcknowledgmentCode;
import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.GenericParser;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.XMLParser;


public class HParser {
	
	public final static int XML = 1;
	public final static int HL7 = 2;
	
	private HapiContext context = new DefaultHapiContext();
	private Message root;
	
	private String c_xml;
	private String c_txt;
	
	static {
		LogManager.getRootLogger().setLevel(Level.WARN); 
	}
	
	
	/**
	 * 解析 str 中的数据, 类型由 type 指定
	 * @throws HL7Exception 
	 */
	public HParser(String str, int type) throws HL7Exception {
		Parser p;
		
		switch (type) {
		
		case HL7:
			c_txt = str;
			p = context.getGenericParser();
			break;
			
		case XML:
			c_xml = str;
			p = context.getXMLParser();
			break;
			
		default:
			throw new IllegalArgumentException("无效的类型:" + type);
		}
		
		root = p.parse(str);
	}
	
	
	public String toXml() throws DOMException, HL7Exception {
		if (c_xml == null) {
			XMLParser dc = context.getXMLParser();
			c_xml = dc.encode(root).toString();
		}
		return c_xml;
	}
	
	
	public String toHl7() throws HL7Exception {
		if (c_txt == null) {
			GenericParser gp = context.getGenericParser();
			c_txt = gp.encode(root);
		}
		return c_txt;
	}
	
	
	public String ask() throws HL7Exception, IOException {
		GenericParser gp = context.getGenericParser();
		return gp.encode(root.generateACK());
	}
	
	
	public String ask(AcknowledgmentCode type) throws HL7Exception, IOException {
		GenericParser gp = context.getGenericParser();
		return gp.encode(root.generateACK(type, null));
	}
}
