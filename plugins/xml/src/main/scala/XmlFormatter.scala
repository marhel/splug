package com.alt2.plugins

import com.factor10.plugins.FormattingPlugin

class XmlFormatter extends FormattingPlugin {
	def name = "XmlFormatter Plugin"

	def convert(data: Map[String, Any]): String = {
		"<some xml=true />"
	}
}
