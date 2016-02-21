/* CatfoOD 2013 yanming-sohu@sohu.com */

package zr.hl7;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.Segment;
import ca.uhn.hl7v2.model.Type;
import ca.uhn.hl7v2.parser.EncodingCharacters;


/**
 * @deprecated 未完成
 */
public class JsonParser extends ca.uhn.hl7v2.parser.Parser {
	
	private HapiContext context;
	
	
	public JsonParser(HapiContext _context) {
		context = _context;
		context.getClass();
	}

	@Override
	protected String doEncode(Message arg0) throws HL7Exception {
		return null;
	}

	@Override
	protected String doEncode(Message arg0, String arg1) throws HL7Exception {
		return null;
	}

	@Override
	public String doEncode(Segment arg0, EncodingCharacters arg1)
			throws HL7Exception {
		return null;
	}

	@Override
	public String doEncode(Type arg0, EncodingCharacters arg1)
			throws HL7Exception {
		return null;
	}

	@Override
	protected Message doParse(String arg0, String arg1) throws HL7Exception {
		return null;
	}

	@Override
	protected Message doParseForSpecificPackage(String arg0, String arg1,
			String arg2) throws HL7Exception {
		return null;
	}

	@Override
	public String getAckID(String arg0) {
		return null;
	}

	@Override
	public Segment getCriticalResponseData(String arg0) throws HL7Exception {
		return null;
	}

	@Override
	public String getDefaultEncoding() {
		return null;
	}

	@Override
	public String getEncoding(String arg0) {
		return null;
	}

	@Override
	public String getVersion(String arg0) throws HL7Exception {
		return null;
	}

	@Override
	public void parse(Message arg0, String arg1) throws HL7Exception {
	}

	@Override
	public void parse(Type arg0, String arg1, EncodingCharacters arg2)
			throws HL7Exception {
	}

	@Override
	public void parse(Segment arg0, String arg1, EncodingCharacters arg2)
			throws HL7Exception {
	}

}
