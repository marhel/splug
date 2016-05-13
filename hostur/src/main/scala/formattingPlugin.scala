package com.factor10.plugins

trait FormattingPlugin {
	def name: String
	def convert(data: Map[String, Any]): String
}

// class XmlFormatter extends FormattingPlugin {
// 	def name = "XmlFormatter Plugin"

// 	def convert(data: Map[String, Any]): String = {
// 		"<some xml=true />"
// 	}
// }
