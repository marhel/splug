package com.alt1.plugins

import com.factor10.plugins.FormattingPlugin

class JsonFormatter extends FormattingPlugin {
	def name = "Javascript Object Notation Plugin"

	def convert(data: Map[String, Any]): String = {
		"{\"some\": {\"json\": true}}"
	}
}