/* CatfoOD 2013 yanming-sohu@sohu.com */

package zr.hl7.test;

import zr.hl7.HParser;


public class THparser {
	
	static String m2 = 
 "MSH|^~\\&|NES|NINTENDO|TESTSYSTEM|TESTFACILITY|20010101000000||ADT^A04|Q123456789T123456789X123456|P|2.3\r"
+"EVN|A04|20010101000000|||^KOOPA^BOWSER\r"
+"PID|1||123456789|0123456789^AA^^JP|BROS^MARIO||19850101000000|M|||123 FAKE STREET^MARIO \\T\\ LUIGI BROS PLACE^TOADSTOOL KINGDOM^NES^A1B2C3^JP^HOME^^1234|1234|(555)555-0123^HOME^JP:1234567|||S|MSH|12345678|||||||0|||||N\r"
+"NK1|1|PEACH^PRINCESS|SO|ANOTHER CASTLE^^TOADSTOOL KINGDOM^NES^^JP|(123)555-1234|(123)555-2345|NOK\r"
+"NK1|2|TOADSTOOL^PRINCESS|SO|YET ANOTHER CASTLE^^TOADSTOOL KINGDOM^NES^^JP|(123)555-3456|(123)555-4567|EMC\r"
+"PV1|1|O|ABCD^EFGH||||123456^DINO^YOSHI^^^^^^MSRM^CURRENT^^^NEIGHBOURHOOD DR NBR|^DOG^DUCKHUNT^^^^^^^CURRENT||CRD|||||||123456^DINO^YOSHI^^^^^^MSRM^CURRENT^^^NEIGHBOURHOOD DR NBR|AO|0123456789|1|||||||||||||||||||MSH||A|||20010101000000\r"
+"IN1|1|PAR^PARENT||||LUIGI\r"
+"IN1|2|FRI^FRIEND||||PRINCESS";
	
	
	public static void main(String[] args) throws Exception {
		HParser p = new HParser(m2, HParser.HL7);
		System.out.println('\n'+p.toXml()+'\n');
		System.out.println(p.toHl7());
		
		HParser p2 = new HParser(p.toXml(), HParser.XML);
		System.out.println(p2.toHl7());
		
//		HParser p3 = new HParser(p.toJson(), HParser.JSON);
//		System.out.println(p3.toHl7());
		
	}

}
