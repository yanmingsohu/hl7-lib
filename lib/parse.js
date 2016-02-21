var xml = require('xson-lib');

var java;
var HParser;
var AcknowledgmentCode;


module.exports = {
  parseXml  : parseXml,
  parseJson : parseJson,
  parseHl7  : parseHl7,
};


function parseXml(xmlstr) {
  _init();
  var jparse = new HParser(xmlstr, HParser.XML);
  var ret = createRet(jparse);
  return ret;
}


function parseJson(json_obj) {
  var xmlstr = xml.toXml(json_obj);
  return parseXml(xmlstr);
}


function parseHl7(hl7_str) {
  _init();
  var jparse = new HParser(hl7_str, HParser.HL7);
  var ret = createRet(jparse);
  return ret;
}


function createRet(jparse) {
  var ret = {
    toXml  : toXml,
    toHl7  : toHl7,
    toJson : toJson,
    ask    : ask,
  };

  function toXml() {
    return jparse.toXmlSync();
  }

  function toHl7() {
    return jparse.toHl7Sync();
  }

  //
  // 转换为 json 对象
  //
  function toJson() {
    return xml.toJson(toXml(), {
      object : true,
      reversible : true,
    });
  }

  //
  // type -- 字符串, 描述返回消息类型, AA(默认), AE, AR, CA, CE, CR
  //
  function ask(type) {
    if (type) {
      var atype = AcknowledgmentCode[type];
      if (!atype)
        throw new Error('Unknow AcknowledgmentCode:' + type);
      return jparse.askSync(atype);
    }
    return jparse.askSync();
  }

  return ret;
}


//
// java 相关对象, 必须延迟创建
//
function _init() {
  if (java) return;
  java                = require('java-factory-lib').getJavaInstance();
  HParser             = java.import('zr.hl7.HParser');
  AcknowledgmentCode  = java.import('ca.uhn.hl7v2.AcknowledgmentCode');
}