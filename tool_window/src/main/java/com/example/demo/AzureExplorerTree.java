/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.example.demo;

import com.example.demo.operable.Operable;
import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.DataKey;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Objects;

public class AzureExplorerTree {
    public static DataKey<Object> TARGET = DataKey.create("target");
    public static DataKey<Operable> TARGET_OPERABLE = DataKey.create("target.selected");

    public static JTree createTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Azure Spring Cloud");
        DefaultMutableTreeNode parent1 = new DefaultMutableTreeNode("Azure Spring Cloud Cluster 1");
        DefaultMutableTreeNode child = new DefaultMutableTreeNode(new SpringCloudApp("Spring Cloud App 1"));
        DefaultMutableTreeNode child1 = new DefaultMutableTreeNode(new SpringCloudApp("Spring Cloud App 2"));

        DefaultMutableTreeNode parent2 = new DefaultMutableTreeNode("Azure Spring Cloud Cluster 2");
        DefaultMutableTreeNode child2 = new DefaultMutableTreeNode(new SpringCloudApp("Spring Cloud App 3"));

        // Adding child nodes to parent
        parent1.add(child);
        parent1.add(child1);
        parent2.add(child2);

        // Adding parent nodes to root
        root.add(parent1);
        root.add(parent2);

        final JTree tree = new JTree(root);
        setDataProvider(tree);
        return tree;
    }

    private static void setDataProvider(JTree tree) {
        final DataManager dm = DataManager.getInstance();
        DataManager.registerDataProvider(tree, dataId -> {
            if ("target".equals(dataId)) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (Objects.nonNull(selectedNode)) {
                    return selectedNode.getUserObject();
                }
            } else if ("target.selected".equals(dataId)) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (Objects.nonNull(selectedNode) && selectedNode.getUserObject() instanceof Operable) {
                    return selectedNode.getUserObject();
                }
            }
            return null;
        });
    }
}
