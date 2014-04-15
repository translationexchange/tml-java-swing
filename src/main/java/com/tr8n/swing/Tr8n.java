/*
 *  Copyright (c) 2014 Michael Berkovich, http://tr8nhub.com All rights reserved.
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package com.tr8n.swing;

import java.text.AttributedString;
import java.util.Locale;
import java.util.Map;

import com.tr8n.core.Language;
import com.tr8n.core.TranslationKey;
import com.tr8n.swing.tokenizers.AttributedStringTokenizer;

public class Tr8n extends com.tr8n.core.Tr8n {

	public static void init(String key, String secret, String host) {
		com.tr8n.core.Tr8n.init(key, secret, host);
		com.tr8n.core.Tr8n.getConfig().addTokenizerClass(
				TranslationKey.DEFAULT_TOKENIZERS_STYLED,
				AttributedStringTokenizer.class.getName());

//		String locale = Locale.getDefault().toString();
//		getLogger().debug("System locale: " + locale);
//		locale = locale.replaceAll("_", "-");
//
//		// TODO: map android locales to tr8nhub
//		locale = locale.split("-")[0];
//
//		if (!getApplication().isSupportedLocale(locale))
//			return;
//
//    	Language language = getApplication().getLanguage(locale);
//    	if (language != null && language.isLoaded())
//    		switchLanguage(language);
	}

	public static AttributedString translateAttributedString(String label) {
		return translateAttributedString(label, "");
	}

	public static AttributedString translateAttributedString(String label,
			String description) {
		return translateAttributedString(label, description, null);
	}

	public static AttributedString translateAttributedString(String label,
			String description, Map<String, Object> tokens) {
		return translateAttributedString(label, description, tokens, null);
	}

	public static AttributedString translateAttributedString(String label,
			Map<String, Object> tokens) {
		return translateAttributedString(label, null, tokens, null);
	}

	public static AttributedString translateAttributedString(String label,
			Map<String, Object> tokens, Map<String, Object> options) {
		return translateAttributedString(label, null, tokens, options);
	}

	public static AttributedString translateAttributedString(String label,
			String description, Map<String, Object> tokens,
			Map<String, Object> options) {
		return (AttributedString) getSession().translateStyledString(label,
				tokens, options);
	}

}
