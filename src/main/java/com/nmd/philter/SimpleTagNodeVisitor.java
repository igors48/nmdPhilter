package com.nmd.philter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlcleaner.ContentNode;
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

        if (candidateNode(tagNode)) {

            if (htmlNode instanceof ContentNode) {
                final ContentNode contentNode = (ContentNode) htmlNode;

                if (contentFound(contentNode.getContent().toString())) {
                    log.info(tagNode.getName() + " :: " + String.valueOf(contentNode.getContent().length()) +  " :: " + contentNode.getContent());
                }
            }
        }

        return true;
    }

    private boolean candidateNode(TagNode node) {

        if (node == null) {
            return false;
        }

        return node.getName().equalsIgnoreCase("p") || node.getName().equalsIgnoreCase("div");
    }

    private boolean contentFound(String text) {
        final String cleaned = text.replaceAll("\\s", "");

        return !cleaned.isEmpty();
    }
}
