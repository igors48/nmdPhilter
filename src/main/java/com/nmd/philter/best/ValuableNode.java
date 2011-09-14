package com.nmd.philter.best;

import com.nmd.philter.util.Assert;
import org.htmlcleaner.ContentNode;
import org.htmlcleaner.TagNode;

/**
 * Author: Igor Usenko ( igors48@gmail.com )
 * Date: 14.09.2011
 */
public class ValuableNode {

    private final ContentNode contentNode;
    private final TagNode parent;
    private final TagNode grandParent;
    private final int rate;

    public ValuableNode(ContentNode contentNode, TagNode parent, TagNode grandParent, int rate) {
        Assert.notNull(contentNode);
        this.contentNode = contentNode;

        Assert.notNull(parent);
        this.parent = parent;

        Assert.notNull(grandParent);
        this.grandParent = grandParent;

        Assert.greaterOrEqual(rate, 0, "");
        this.rate = rate;
    }

    public ContentNode getContentNode() {
        return contentNode;
    }

    public TagNode getParent() {
        return parent;
    }

    public TagNode getGrandParent() {
        return grandParent;
    }

    public int getRate() {
        return rate;
    }

}
