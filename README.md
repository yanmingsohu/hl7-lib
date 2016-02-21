# HL7 消息转换


## install

npm install --save hl7-lib


## Usage

```js
//
// 引入并初始化 java 组件
//
var hl7 = require('hl7-lib');
hl7.initJava( require('java') );


//
// 解析各种数据类型到中间状态
//
var mid7data = hl7.parseXml('string');
var mid7data = hl7.parseHl7('string');
var mid7data = hl7.parseJson(js_object);


//
// 从中间状态, 转换为目标状态
// HL7 是按照协议定义的字符串
//
mid7data.toXml();
mid7data.toHl7();
mid7data.toJson();


//
// 生成应答数据, 参数为空使用 'AA'
// AA, AE, AR, CA, CE, CR
//
mid7data.ask('AE')
```