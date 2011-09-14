package com.nmd.philter;

import com.nmd.philter.util.CollectionTools;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import static com.nmd.philter.util.CollectionTools.newHashMap;

public class App {

    public static void main(String[] args) throws IOException {
        final Log log = LogFactory.getLog(App.class);

//        final URL url = new URL("http://www.the-ebook.org/?p=9279");
//        final URL url = new URL("http://korrespondent.net/business/mmedia_and_adv/1261330-martini-ishchet-specialista-po-poceluyam");
        final URL url = new URL("http://www.3dnews.ru/news/616835");
//        final URL url = new URL("http://www.bbc.co.uk/russian/society/2011/09/110913_kennedy_widow_memoires.shtml");

        final HtmlCleaner htmlCleaner = new HtmlCleaner();
        final TagNode tagNode = htmlCleaner.clean(url);

        final ValuableContentNodesLocator valuableContentNodesLocator = new ValuableContentNodesLocator();

        tagNode.traverse(valuableContentNodesLocator);

        final List<ValuableNode> valuableNodes = valuableContentNodesLocator.getValuableNodes();

        final Map<TagNode, List<ValuableNode>> groupedValuableNodes = groupByGrandparent(valuableNodes);

        final TagNode best = findBest(groupedValuableNodes);

        System.out.println(htmlCleaner.getInnerHtml(best));
    }

    private static Map<TagNode, List<ValuableNode>> groupByGrandparent(List<ValuableNode> valuableNodes) {
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

    private static TagNode findBest(Map<TagNode, List<ValuableNode>> groups) {
        int maxRate = Integer.MIN_VALUE;
        TagNode result = null;

        for (final TagNode candidate : groups.keySet()) {

            if (notCommentNode(candidate)) {
                final int rate = getRate(groups.get(candidate));

                if (rate > maxRate) {
                    maxRate = rate;
                    result = candidate;
                }
            }
        }

        return result;
    }

    private static boolean notCommentNode(TagNode candidate) {
        final Map<String, String> attributes = candidate.getAttributes();

        for (final String attribute : attributes.keySet()) {
            final String value = attributes.get(attribute).toLowerCase();

            if (value.contains("comment")) {
                return false;
            }
        }

        return true;
    }

    private static int getRate(List<ValuableNode> nodes) {
        int result = 0;

        for (final ValuableNode current : nodes) {
            result += current.getRate();
        }

        return result;
    }
}
