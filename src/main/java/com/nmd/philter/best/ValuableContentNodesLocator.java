package com.nmd.philter.best;

import org.htmlcleaner.ContentNode;
import org.htmlcleaner.HtmlNode;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.TagNodeVisitor;

import java.util.List;

import static com.nmd.philter.util.CollectionTools.newArrayList;

/**
 * Author: Igor Usenko ( igors48@gmail.com )
 * Date: 12.09.2011
 */
public class ValuableContentNodesLocator implements TagNodeVisitor {

    private static final int MIN_RATE = 20;

    private final List<ValuableNode> valuableNodes;

    public ValuableContentNodesLocator() {
        this.valuableNodes = newArrayList();
    }

    public boolean visit(TagNode parent, HtmlNode candidate) {

        if (candidateNode(parent)) {

            if (candidate instanceof ContentNode) {
                final ContentNode contentNode = (ContentNode) candidate;

                final int rate = getRate(contentNode);

                if (rate > MIN_RATE) {
                    valuableNodes.add(new ValuableNode(contentNode, parent, parent.getParent(), rate));
                }
            }
        }

        return true;
    }

    public List<ValuableNode> getValuableNodes() {
        return valuableNodes;
    }

    private boolean candidateNode(TagNode node) {
        return node != null && (node.getName().equalsIgnoreCase("p") || node.getName().equalsIgnoreCase("div"));
    }

    private int getRate(ContentNode node) {
        final String cleaned = node.getContent().toString().replaceAll("\\s", "");

        return cleaned.length();
    }
}
