package com.iot.cache.utils;

import java.util.ArrayList;
import java.util.List;

import com.iot.cache.vo.CacheTree;

public class TreeUtils {
    public static List<CacheTree> buildTree(List<CacheTree> trees) {
        List<CacheTree> list = new ArrayList<>();
        for (CacheTree tree : trees) {
            if (tree.getParentId().equals("-1")) {
                list.add(tree);
            }
            for (CacheTree t : trees) {
                if (t.getParentId().equals(tree.getId())) {
                    if (tree.getNodes() == null) {
                        List<CacheTree> myChildren = new ArrayList<>();
                        myChildren.add(t);
                        tree.setNodes(myChildren);
                    } else {
                        tree.getNodes().add(t);
                    }
                }
            }
        }
        return list;
    }

}
