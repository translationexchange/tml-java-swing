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

package com.tr8n.swing.tokenizers;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tr8n.core.tokenizers.StyledTokenizer;

public class AttributedStringTokenizer extends StyledTokenizer {

    /**
     *
     * @param label
     */
	public AttributedStringTokenizer(String label) {
       this(label, null);
	}

    /**
     *
     * @param label
     * @param allowedTokenNames
     */
   	public AttributedStringTokenizer(String label, List<String> allowedTokenNames) {
       super(label, allowedTokenNames);
   	}
   
   	
    protected Object createStyledString(String label) {
    	return new AttributedString(label);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	protected void applyStyles(Object styledString, Map<String, Object> styles, List<Map<String, Object>> ranges) {
    	AttributedString attributedString = (AttributedString) styledString;
    	
        Iterator<Map.Entry<String, Object>> entries = styles.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Object> entry = entries.next();

            String styleName = (String) entry.getKey();
            Object styleAttributes = entry.getValue();

            for (Map<String, Object> range : ranges) {
                Integer start = (Integer) range.get(StyledTokenizer.ATTRIBUTE_RANGE_ORIGIN);
                Integer end = start + (Integer) range.get(StyledTokenizer.ATTRIBUTE_RANGE_LENGTH);

                if (styleName.equals("attributes")) {
                    attributedString.addAttributes((Map)styleAttributes, start, end);
                } else if (styleName.equals("font")) {
                	addFont(attributedString, styleAttributes, start, end);
                } else if (styleName.equals("color")) {
                	addForegroundColor(attributedString, styleAttributes, start, end);
                } else if (styleName.equals("background-color")) {
                	addBackgroundColor(attributedString, styleAttributes, start, end);
                } else if (styleName.equals("underline")) {
                	addUnderline(attributedString, styleAttributes, start, end);
                } else if (styleName.equals("strikethrough")) {
                	addStrikeThrough(attributedString, styleAttributes, start, end);
                } else if (styleName.equals("weight")) {
                	addWeight(attributedString, styleAttributes, start, end);
                } else if (styleName.equals("family")) {
                	addFamily(attributedString, styleAttributes, start, end);
                } else if (styleName.equals("width")) {
                	addWidth(attributedString, styleAttributes, start, end);
                } else if (styleName.equals("justification")) {
                	addJustification(attributedString, styleAttributes, start, end);
                } else if (styleName.equals("posture")) {
                	addPosture(attributedString, styleAttributes, start, end);
                } else if (styleName.equals("size")) {
                	addSize(attributedString, styleAttributes, start, end);
                } else if (styleName.equals("superscript")) {
                	addSuperscript(attributedString, styleAttributes, start, end);
                } else if (styleName.equals("direction")) {
                	addDirection(attributedString, styleAttributes, start, end);
                } else if (styleName.equals("ligatures")) {
                	addLigatures(attributedString, styleAttributes, start, end);
                } else if (styleName.equals("transform")) {
                	addTransform(attributedString, styleAttributes, start, end);
                } else if (styleName.equals("kerning")) {
                	addKerning(attributedString, styleAttributes, start, end);
                } else if (styleName.equals("tracking")) {
                	addTracking(attributedString, styleAttributes, start, end);
                }
            }

        }
    }   	
   	
    protected void addTracking(AttributedString attributedString, Object style, Integer start, Integer end) {
    	Float tracking = TextAttribute.TRACKING_TIGHT;
    	if (style instanceof String) {
    		tracking = getTracking((String) style);
    	}    	
    	attributedString.addAttribute(TextAttribute.TRACKING, tracking, start, end);	
    }
    
    protected void addKerning(AttributedString attributedString, Object style, Integer start, Integer end) {
    	Integer kerning = TextAttribute.KERNING_ON;
    	if (style instanceof String) {
    		if (style.equals("on"))
    			kerning = TextAttribute.KERNING_ON;
    	}    	
    	attributedString.addAttribute(TextAttribute.KERNING, kerning, start, end);	
    }
    
    protected void addTransform(AttributedString attributedString, Object style, Integer start, Integer end) {
    	attributedString.addAttribute(TextAttribute.TRANSFORM, style, start, end);	
    }
    
    protected void addLigatures(AttributedString attributedString, Object style, Integer start, Integer end) {
    	Integer ligatures = TextAttribute.LIGATURES_ON;
    	if (style instanceof String) {
    		if (style.equals("on"))
    			ligatures = TextAttribute.LIGATURES_ON;
    	}    	
    	attributedString.addAttribute(TextAttribute.LIGATURES, ligatures, start, end);	
    }
    
    protected void addDirection(AttributedString attributedString, Object style, Integer start, Integer end) {
    	Boolean dir = TextAttribute.RUN_DIRECTION_LTR;
    	if (style instanceof String) {
    		if (style.equals("rtl"))
    			dir = TextAttribute.RUN_DIRECTION_RTL;
    		else
    			dir = TextAttribute.RUN_DIRECTION_LTR;
    	}    	
    	attributedString.addAttribute(TextAttribute.RUN_DIRECTION, dir, start, end);	
    }
    
    protected void addSuperscript(AttributedString attributedString, Object style, Integer start, Integer end) {
    	Integer superscript = TextAttribute.SUPERSCRIPT_SUPER;
    	if (style instanceof String) {
    		superscript = getSuperscript((String) style);
    	}    	
    	attributedString.addAttribute(TextAttribute.SUPERSCRIPT, superscript, start, end);	
    }
    
    protected void addSize(AttributedString attributedString, Object style, Integer start, Integer end) {
    	attributedString.addAttribute(TextAttribute.SIZE, Float.parseFloat("" + style), start, end);	
    }
    
    protected void addPosture(AttributedString attributedString, Object style, Integer start, Integer end) {
    	Float posture = TextAttribute.POSTURE_REGULAR;
    	if (style instanceof String) {
    		posture = getPosture((String) style);
    	}    	
    	
    	attributedString.addAttribute(TextAttribute.POSTURE, posture, start, end);	
    }
    
    protected void addJustification(AttributedString attributedString, Object style, Integer start, Integer end) {
    	Float justification = TextAttribute.JUSTIFICATION_NONE;
    	if (style instanceof String) {
    		justification = getJustification((String) style);
    	}    	
    	
    	attributedString.addAttribute(TextAttribute.JUSTIFICATION, justification, start, end);	
    }
    
    protected void addWidth(AttributedString attributedString, Object style, Integer start, Integer end) {
    	Float width = TextAttribute.WIDTH_REGULAR;
    	if (style instanceof String) {
    		width = getWidth((String) style);
    	}    	
    	
    	attributedString.addAttribute(TextAttribute.WIDTH, width, start, end);	
    }
    
    protected void addFamily(AttributedString attributedString, Object style, Integer start, Integer end) {
    	attributedString.addAttribute(TextAttribute.FAMILY, style, start, end);	
    }
    
    protected void addWeight(AttributedString attributedString, Object style, Integer start, Integer end) {
    	Float weight = TextAttribute.WEIGHT_REGULAR;
    	if (style instanceof String) {
    		weight = getWeight((String) style);
    	}    	

    	attributedString.addAttribute(TextAttribute.WEIGHT, weight, start, end);	
    }
    
    protected void addStrikeThrough(AttributedString attributedString, Object style, Integer start, Integer end) {
    	Boolean on = TextAttribute.STRIKETHROUGH_ON;
    	if (style instanceof String) {
    		if (style.equals("true"))
    			on = TextAttribute.STRIKETHROUGH_ON;
    		else
    			on = !TextAttribute.STRIKETHROUGH_ON;
    	}
    	
    	attributedString.addAttribute(TextAttribute.STRIKETHROUGH, on, start, end);	
    }
    
    protected void addUnderline(AttributedString attributedString, Object style, Integer start, Integer end) {
    	Integer type = TextAttribute.UNDERLINE_LOW_ONE_PIXEL;
    	if (style instanceof String) {
    		type = getUnderlineStyle((String)style);
    	}
    	
    	attributedString.addAttribute(TextAttribute.UNDERLINE, type, start, end);	
    }
    
    protected void addBackgroundColor(AttributedString attributedString, Object style, Integer start, Integer end) {
    	if (style instanceof Color) {
        	attributedString.addAttribute(TextAttribute.BACKGROUND, style, start, end);	
    		return;
    	}
    	
    	if (style instanceof String) {
	    	String color = (String) style;
        	attributedString.addAttribute(TextAttribute.BACKGROUND, getColor(color), start, end);	
    	}
    }
    
    protected void addForegroundColor(AttributedString attributedString, Object style, Integer start, Integer end) {
    	if (style instanceof Color) {
        	attributedString.addAttribute(TextAttribute.FOREGROUND, style, start, end);	
    		return;
    	}
    	
    	if (style instanceof String) {
	    	String color = (String) style;
        	attributedString.addAttribute(TextAttribute.FOREGROUND, getColor(color), start, end);	
    	}
    }

    @SuppressWarnings("unchecked")
	protected void addFont(AttributedString attributedString, Object style, Integer start, Integer end) {
    	if (style instanceof Font) {
        	attributedString.addAttribute(TextAttribute.FONT, style, start, end);	
    		return;
    	}
    	
    	if (style instanceof Map) {
	    	Map<String, Object> attributes = (Map<String, Object>) style;
	    	String family = (String) attributes.get("family");    	
	    	Integer fontStyle = Font.PLAIN;
	    	
	    	if (attributes.get("style") != null) {
	    		if (attributes.get("style").equals("bold")) {
	    			fontStyle = Font.BOLD;
	    		} else if (attributes.get("style").equals("italic")) {
	    			fontStyle = Font.ITALIC;
	    		}
	    	}
	    	Integer fontSize = 12;
	    	if (attributes.get("size") != null) {
	    		fontSize = (Integer) attributes.get("size");
	    	}
	    	attributedString.addAttribute(TextAttribute.FONT, new Font(family, fontStyle, fontSize), start, end);	
    	}
    }

    protected Color getColor(String color) {
    	if (color.equals("red")) return Color.RED;
    	if (color.equals("green")) return Color.GREEN;
    	if (color.equals("white")) return Color.WHITE;
    	if (color.equals("light-gray")) return Color.LIGHT_GRAY;
    	if (color.equals("gray")) return Color.GRAY;
    	if (color.equals("dark-gray")) return Color.DARK_GRAY;
    	if (color.equals("black")) return Color.BLACK;
    	if (color.equals("pink")) return Color.PINK;
    	if (color.equals("orange")) return Color.ORANGE;
    	if (color.equals("yellow")) return Color.YELLOW;
    	if (color.equals("magenta")) return Color.MAGENTA;
    	if (color.equals("cyan")) return Color.CYAN;
    	if (color.equals("blue")) return Color.BLUE;
    	
    	return Color.BLACK;
    }

    private Integer getUnderlineStyle(String type) {
    	if (type.equals("one-pixel")) return TextAttribute.UNDERLINE_LOW_ONE_PIXEL;
    	if (type.equals("two-pixel")) return TextAttribute.UNDERLINE_LOW_TWO_PIXEL;
    	if (type.equals("dotted")) return TextAttribute.UNDERLINE_LOW_DOTTED;
    	if (type.equals("gray")) return TextAttribute.UNDERLINE_LOW_GRAY;
    	if (type.equals("dashed")) return TextAttribute.UNDERLINE_LOW_DASHED;
    	if (type.equals("on")) return TextAttribute.UNDERLINE_ON;
    	
    	return TextAttribute.UNDERLINE_LOW_ONE_PIXEL;
    }
    
    private Float getWeight(String weight) {
    	if (weight.equals("extra-light")) return TextAttribute.WEIGHT_EXTRA_LIGHT;
    	if (weight.equals("light")) return TextAttribute.WEIGHT_LIGHT;
    	if (weight.equals("demi-light")) return TextAttribute.WEIGHT_DEMILIGHT;
    	if (weight.equals("regular")) return TextAttribute.WEIGHT_REGULAR;
    	if (weight.equals("semi-bold")) return TextAttribute.WEIGHT_SEMIBOLD;
    	if (weight.equals("medium")) return TextAttribute.WEIGHT_MEDIUM;
    	if (weight.equals("demi-bold")) return TextAttribute.WEIGHT_DEMIBOLD;
    	if (weight.equals("bold")) return TextAttribute.WEIGHT_BOLD;
    	if (weight.equals("heavy")) return TextAttribute.WEIGHT_HEAVY;
    	if (weight.equals("extra-bold")) return TextAttribute.WEIGHT_EXTRABOLD;
    	if (weight.equals("ultra-bold")) return TextAttribute.WEIGHT_ULTRABOLD;

    	return TextAttribute.WEIGHT_REGULAR;
    }

    protected Float getWidth(String weight) {
    	if (weight.equals("condensed")) return TextAttribute.WIDTH_CONDENSED;
    	if (weight.equals("semi-condensed")) return TextAttribute.WIDTH_SEMI_CONDENSED;
    	if (weight.equals("regular")) return TextAttribute.WIDTH_REGULAR;
    	if (weight.equals("semi-extended")) return TextAttribute.WIDTH_SEMI_EXTENDED;
    	if (weight.equals("extended")) return TextAttribute.WIDTH_EXTENDED;
    	return TextAttribute.WIDTH_REGULAR;
    }
  
    protected Float getJustification(String weight) {
    	if (weight.equals("full")) return TextAttribute.JUSTIFICATION_FULL;
    	if (weight.equals("none")) return TextAttribute.JUSTIFICATION_NONE;
    	return TextAttribute.JUSTIFICATION_NONE;
    }
    
    protected Float getPosture(String posture) {
    	if (posture.equals("regular")) return TextAttribute.POSTURE_REGULAR;
    	if (posture.equals("oblique")) return TextAttribute.POSTURE_OBLIQUE;
    	return TextAttribute.POSTURE_REGULAR;
    }

    protected Integer getSuperscript(String style) {
    	if (style.equals("super")) return TextAttribute.SUPERSCRIPT_SUPER;
    	if (style.equals("sub")) return TextAttribute.SUPERSCRIPT_SUB;
    	return TextAttribute.SUPERSCRIPT_SUPER;
    }

    protected Float getTracking(String style) {
    	if (style.equals("tight")) return TextAttribute.TRACKING_TIGHT;
    	if (style.equals("loose")) return TextAttribute.TRACKING_LOOSE;
    	return TextAttribute.TRACKING_TIGHT;
    }

    
}
