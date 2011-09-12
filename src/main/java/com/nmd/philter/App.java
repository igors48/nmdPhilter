package com.nmd.philter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import java.io.IOException;
import java.net.URL;

public class App {
    public static void main(String[] args) throws IOException {
        final Log log = LogFactory.getLog(App.class);

        final URL url = new URL("http://www.the-ebook.org/?p=9279");

        final HtmlCleaner htmlCleaner = new HtmlCleaner();
        final TagNode tagNode = htmlCleaner.clean(url);

        tagNode.traverse(new SimpleTagNodeVisitor());
        
        System.out.println();
    }
}
