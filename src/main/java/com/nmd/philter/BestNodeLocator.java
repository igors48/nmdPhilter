package com.nmd.philter;

import org.htmlcleaner.TagNode;

/**
 * Author: Igor Usenko ( igors48@gmail.com )
 * Date: 14.09.2011
 */
public interface BestNodeLocator {

    TagNode findBest(TagNode root);

}
