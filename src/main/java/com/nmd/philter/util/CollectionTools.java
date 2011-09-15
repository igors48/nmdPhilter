package com.nmd.philter.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Author: Igor Usenko ( igors48@gmail.com )
 * Date: 21.07.2011
 */
public final class CollectionTools {

    public static <T> ArrayList<T> newArrayList() {
        return new ArrayList<T>();
    }

    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<K, V>();
    }

    public static <T> LinkedList<T> newLinkedList() {
        return new LinkedList<T>();
    }

    public static <T> HashSet<T> newHashSet() {
        return new HashSet<T>();
    }

    private CollectionTools() {
        // empty
    }

}
