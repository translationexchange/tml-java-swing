package com.tr8n.swing.tokenizers;

import java.util.Arrays;

import junit.framework.Assert;

import com.tr8n.core.Utils;

public class AttributedStringTokenizerTest {
    
//	@Test
    public void testTokenization() {
        AttributedStringTokenizer dt = new AttributedStringTokenizer("Hello [bold: World]");

        Assert.assertEquals(
                Arrays.asList("bold"),
                dt.getTokenNames()
        );

        dt = new AttributedStringTokenizer("Hello [bold: How are [italic: you?]]");

        Assert.assertEquals(
                Arrays.asList("bold", "italic"),
                dt.getTokenNames()
        );


        // broken
        dt.tokenize("[bold: Hello World");
        Assert.assertEquals(
                Arrays.asList("bold"),
                dt.getTokenNames()
        );

        dt.tokenize("[bold: Hello [strong: World]]");
        Assert.assertEquals(
                Arrays.asList("bold", "strong"),
                dt.getTokenNames()
        );
    }

//    @Test
    public void testSubstitution() {
        AttributedStringTokenizer dt = new AttributedStringTokenizer("Hello [bold: World]");
        Assert.assertNotNull(
                dt.substitute(Utils.buildMap())
        );

    }
}
