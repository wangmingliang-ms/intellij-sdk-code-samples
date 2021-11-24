/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.example.demo.operable.action;

import com.example.demo.operable.Operable;
import com.example.demo.AzureExplorerTree;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;

public class OperableActionGroup extends DefaultActionGroup {
    @Override
    public void update(AnActionEvent event) {
        final Object selected = event.getData(AzureExplorerTree.TARGET);
        event.getPresentation().setEnabled(selected instanceof Operable);
    }
}
