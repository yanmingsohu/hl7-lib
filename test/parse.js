var hl7 = require("../");
var fs  = require('fs');
hl7.initJava( require('java') );


// parse1();
parse2();
// parse3();



function parse1() {
  var mid = hl7.parseHl7( read('data.hl7') );
  pl(JSON.stringify(mid.toJson(), null, 2));
  pl(mid.toXml());
  pl('ASK >>> ', mid.ask());
  return mid;
}


function parse2() {
  var mid = hl7.parseJson( read('data.json') );
  pl(mid.toXml());
  pl(mid.toHl7());
  pl('ASK >>> ', mid.ask());
  return mid;
}


function parse3() {
  var mid = hl7.parseXml( read('data.xml') );
  pl(mid.toJson());
  pl(mid.toHl7());
  pl('ASK >>> ', mid.ask('AE'));
  pl('ASK >>> ', mid.ask('AA'));
  pl('ASK >>> ', mid.ask('AR'));
  // pl('ASK >>> ', mid.ask('N'));
  return mid;
}


function read(fname) {
  return fs.readFileSync(__dirname + '/' + fname, 'utf8');
}


function pl() {
  console.log();
  console.log.apply(console, arguments);
  console.log();
}