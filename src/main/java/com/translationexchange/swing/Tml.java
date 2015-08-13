/**
 * Copyright (c) 2015 Translation Exchange, Inc. All rights reserved.
 *
 *  _______                  _       _   _             ______          _
 * |__   __|                | |     | | (_)           |  ____|        | |
 *    | |_ __ __ _ _ __  ___| | __ _| |_ _  ___  _ __ | |__  __  _____| |__   __ _ _ __   __ _  ___
 *    | | '__/ _` | '_ \/ __| |/ _` | __| |/ _ \| '_ \|  __| \ \/ / __| '_ \ / _` | '_ \ / _` |/ _ \
 *    | | | | (_| | | | \__ \ | (_| | |_| | (_) | | | | |____ >  < (__| | | | (_| | | | | (_| |  __/
 *    |_|_|  \__,_|_| |_|___/_|\__,_|\__|_|\___/|_| |_|______/_/\_\___|_| |_|\__,_|_| |_|\__, |\___|
 *                                                                                        __/ |
 *                                                                                       |___/
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.translationexchange.swing;

import java.text.AttributedString;
import java.util.Map;

import com.translationexchange.core.TranslationKey;
import com.translationexchange.core.Utils;
import com.translationexchange.swing.tokenizers.AttributedStringTokenizer;

public class Tml extends com.translationexchange.core.Tml {

	public static void init(String key, String token, String host) {
		com.translationexchange.core.Tml.init(key, token, Utils.buildMap("host", host));
		
		com.translationexchange.core.Tml.getConfig().addTokenizerClass(
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
