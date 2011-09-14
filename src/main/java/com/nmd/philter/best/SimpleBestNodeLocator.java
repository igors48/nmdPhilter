package com.nmd.philter.best;

import com.nmd.philter.BestNodeLocator;
import com.nmd.philter.util.Assert;
import com.nmd.philter.util.CollectionTools;
import org.htmlcleaner.TagNode;

import java.util.List;
import java.util.Map;

import static com.nmd.philter.util.CollectionTools.newHashMap;

/**
 * Author: Igor Usenko ( igors48@gmail.com )
 * Date: 14.09.2011
 */
public class SimpleBestNodeLocator implements BestNodeLocator {

    private static final int MAGIC_WORDS_IN_ATTRIBUTES_RATIO = 2;

    public TagNode findBest(TagNode root) {
        Assert.notNull(root);

        final ValuableContentNodesLocator valuableContentNodesLocator = new ValuableContentNodesLocator();

        root.traverse(valuableContentNodesLocator);

        final List<ValuableNode> valuableNodes = valuableContentNodesLocator.getValuableNodes();

        final Map<TagNode, List<ValuableNode>> groupedValuableNodes = groupByGrandparent(valuableNodes);

        return findBest(groupedValuableNodes);
    }

    private Map<TagNode, List<ValuableNode>> groupByGrandparent(List<ValuableNode> valuableNodes) {
        final Map<TagNode, List<ValuableNode>> result = newHashMap();

        for (final ValuableNode node : valuableNodes) {
            final TagNode key = node.getGrandParent();

            if (result.get(key) == null) {
                result.put(key, CollectionTools.<ValuableNode>newArrayList());
            }

            final List<ValuableNode> entry = result.get(key);

            entry.add(node);
        }

        return result;
    }

    private TagNode findBest(Map<TagNode, List<ValuableNode>> groups) {
        int maxRate = Integer.MIN_VALUE;
        TagNode result = null;

        for (final TagNode candidate : groups.keySet()) {

            if (notCommentNode(candidate)) {
                final int childrenRate = getChildrenRate(groups.get(candidate));
                final int finalRate = getNodeRate(candidate, childrenRate);

                if (finalRate > maxRate) {
                    maxRate = finalRate;
                    result = candidate;
                }
            }
        }

        return result;
    }

    private boolean notCommentNode(TagNode candidate) {
        final Map<String, String> attributes = candidate.getAttributes();

        for (final String attribute : attributes.keySet()) {
            final String value = attributes.get(attribute).toLowerCase();

            if (value.contains("comment")) {
                return false;
            }
        }

        return true;
    }

    private int getChildrenRate(List<ValuableNode> nodes) {
        int result = 0;

        for (final ValuableNode current : nodes) {
            result += current.getRate();
        }

        return result;
    }

    private int getNodeRate(TagNode node, int childrenRate) {
        final Map<String, String> attributes = node.getAttributes();

        for (final String attribute : attributes.keySet()) {
            final String value = attributes.get(attribute).toLowerCase();

            if (value.contains("news") || value.contains("entry") || value.contains("article")) {
                return childrenRate * MAGIC_WORDS_IN_ATTRIBUTES_RATIO;
            }
        }

        return childrenRate;
    }
}
