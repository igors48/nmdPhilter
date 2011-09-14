package com.nmd.philter;

import com.nmd.philter.best.SimpleBestNodeLocator;
import com.nmd.philter.util.Assert;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

/**
 * Author: Igor Usenko ( igors48@gmail.com )
 * Date: 14.09.2011
 */
public class Philter {

    public String filter(String content) {
        Assert.isValidString(content);

        final HtmlCleaner htmlCleaner = new HtmlCleaner();
        final TagNode rootNode = htmlCleaner.clean(content);

        final BestNodeLocator bestNodeLocator = new SimpleBestNodeLocator();
        final TagNode bestNode = bestNodeLocator.findBest(rootNode);

        return htmlCleaner.getInnerHtml(bestNode);
    }
    
}
