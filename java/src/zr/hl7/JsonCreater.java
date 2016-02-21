/* CatfoOD 2013 yanming-sohu@sohu.com */

package zr.hl7;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Composite;
import ca.uhn.hl7v2.model.Group;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.Primitive;
import ca.uhn.hl7v2.model.Segment;
import ca.uhn.hl7v2.model.Structure;
import ca.uhn.hl7v2.model.Type;


/**
 * @deprecated
 */
public class JsonCreater {
	
	private String JSON;
	private StringBuilder out;
	private int deep;
	private boolean newline = false;

	
	public JsonCreater(Message root) throws HL7Exception {
		out = new StringBuilder();
		out.append("{\n");
		loop(root);
		out.append('}');
		formatjson(out);
		JSON = out.toString();
		out = null;
	}
	
	private void formatjson(StringBuilder buf) {
		int state = 0;
		int index = -1;
		
		for (int i=0; i<buf.length(); ++i) {
			char c = buf.charAt(i);
			if (c == '{') {
				state = 0;
			}
			else if (c == '}') {
				if (state == 1) {
					buf.setCharAt(index, ' ');
					state = 0;
				}
			}
			else if (c == ',') {
				index = i;
				state = 1;
			}
		}
	}
	
	private void loop(Message h) throws HL7Exception  {
		p(h.getName(), true);
		p(" : {\n");
		
		String[] names = h.getNames();
		
		for (int i=0; i<names.length; ++i) {
			Structure[] st = h.getAll(names[i]);
			for (int j=0; j<st.length; ++j) {
				loop(st[j]);
			}
		}
		
		p("},\n");
	}
	
	private void loop(Structure ss) throws HL7Exception {
		if (ss instanceof Segment) {
			loop((Segment) ss);
		} else if (ss instanceof Group) {
			Group g = (Group) ss;
			p(g.getName(), true);
			p(" : {\n");
			String[] names= g.getNames();
			
			for (int i=0; i<names.length; ++i) {
				Structure[] st = g.getAll(names[i]);
				for (int j=0; j<st.length; ++j) {
					loop(st[j]);
				}
			}
			
			p("},\n");
		} else {
			throw new HL7Exception("语法错误");
		}
	}
	
	private void loop(Segment sg) throws HL7Exception {
		String name = sg.getName();
		p(name, true);
		p(" : {\n");
//		p("Segment: " + name + " [" + sg.getClass());
		
		for (int i=1; i<=sg.numFields(); ++i) {
			Type[] ts = sg.getField(i);
			loop(ts, name + '.' + i);
		}

		p("},\n");
	}
	
	private void loop(Type[] ts, String sname) {
		if (ts.length == 0) return;
		boolean onlyone = ts.length == 1;
		
		if (!onlyone) {
			p(sname, true);
			p(" : {\n");
		}
		
		for (int j=0; j<ts.length; ++j) {
			String name;
			
			if (ts.length == 1) {
				name = sname;
			} else {
				name = sname + '.' + (j+1);
			}
			
			if (ts[j] instanceof Primitive) {
				Primitive p = (Primitive) ts[j];
				p(name, true);
				p(" : ");
				p(p.getValue(), true);
				p(",\n");
//				p(name + " (" + ts[j].getName() +  ") = " + p.getValue());
			}
			else if (ts[j] instanceof Composite) {
				Composite c = (Composite) ts[j];
//				p("Composite " + sname + " (" + c.getName() + ")");
				loop(c.getComponents(), name);
			}
		}
		
		if (!onlyone) {
			p("},\n");
		}
	}
	
	private void p(Object o) {
		p(o.toString(), false);
	}
	
	public String toString() {
		return JSON;
	}
	
	private void p(String s, boolean tag) {
		if (newline) {
			switch(deep) {
			default: out.append(' ');
			case 10: out.append(' ');
			case 9:  out.append(' ');
			case 8:  out.append(' ');
			case 7:  out.append(' ');
			case 6:  out.append(' ');
			case 5:  out.append(' ');
			case 4:  out.append(' ');
			case 3:  out.append(' ');
			case 2:  out.append(' ');
			case 1:  out.append(' ');
			case 0:  out.append(' ');
			}
			newline = false;
		}
		
		if (s != null) {
			if (s.charAt(s.length()-1) == '\n') {
				newline = true;
			}
		}
		
		if (s != null) {
			if (tag) out.append('"');
			else if (s.indexOf('{') >= 0) deep+=2;
			else if (s.indexOf('}') >= 0) deep-=2;
		}
		
		if (s != null) {
			String [] ss = s.split("\\\\");
			if (ss.length > 1) {
				for (int i=0; i<ss.length-1; ++i) {
					out.append(ss[i]);
					out.append("\\\\");
				}
				out.append(ss[ss.length-1]);
			} else {
				out.append(s);
			}
		} else {
			out.append(s);
		}
		
		if (s != null) {
			if (tag) out.append('"');
		}
	}
	
}
