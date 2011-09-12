package com.nmd.philter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlcleaner.HtmlNode;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.TagNodeVisitor;

/**
 * Author: Igor Usenko ( igors48@gmail.com )
 * Date: 12.09.2011
 */
public class SimpleTagNodeVisitor implements TagNodeVisitor {

    private final Log log;

    public SimpleTagNodeVisitor() {
        this.log = LogFactory.getLog(getClass());
    }

    public boolean visit(TagNode tagNode, HtmlNode htmlNode) {
        log.info(htmlNode);
        
        return true;
    }
}
