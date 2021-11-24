// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.example.demo;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.PopupHandler;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class AzureExplorerToolWindowFactory implements ToolWindowFactory {

  /**
   * Create the tool window content.
   *
   * @param project    current project
   * @param toolWindow current tool window
   */
  public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
    SimpleToolWindowPanel windowPanel = new SimpleToolWindowPanel(true, true);

    // set content
    final JTree tree = AzureExplorerTree.createTree();
    windowPanel.setContent(tree);

    // set toolbar
    final ActionGroup group = (ActionGroup) ActionManager.getInstance().getAction("demo.operations");
    @NotNull ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar("demo.toolbar", group, true);
    toolbar.setTargetComponent(tree);
    windowPanel.setToolbar(toolbar.getComponent());

    // set context menu
    PopupHandler.installPopupHandler(tree, "demo.operations", "demo.menu");

    // install to tool window
    ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
    Content content = contentFactory.createContent(windowPanel, null, false);
    toolWindow.getContentManager().addContent(content);
  }
}
