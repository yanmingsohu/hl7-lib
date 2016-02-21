var cp    = require('child_process');
var jfact = require('java-factory-lib');

jfact.loadjar(__dirname + '/jar/');
jfact.loadjar(__dirname + '/java/bin/');

var src = __dirname + '/java/src/';
var bin = __dirname + '/java/bin/';


jfact.compile(src, bin, function(err, msg) {
  if (err) {
    console.log(err);
    process.exit(-1);
  } else {
    console.log(msg);
  }
});