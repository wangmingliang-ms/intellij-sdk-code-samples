// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package com.example.demo.operable.action;

import com.example.demo.AzureExplorerTree;
import com.example.demo.operable.Operable;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.ui.AnimatedIcon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class StartAction extends AnAction {

  public StartAction() {
    super();
  }

  public StartAction(@Nullable String text, @Nullable String description, @Nullable Icon icon) {
    super(text, description, icon);
  }

  @Override
  public void actionPerformed(@NotNull AnActionEvent e) {
    final Operable selected = e.getData(AzureExplorerTree.TARGET_OPERABLE);
    if (selected != null) {
      selected.start();
    }
  }

  @Override
  public void update(AnActionEvent e) {
    final Operable selected = e.getData(AzureExplorerTree.TARGET_OPERABLE);
    if (selected != null) {
      if (selected.getStatus() == Operable.Status.UPDATING) {
        e.getPresentation().setIcon(new AnimatedIcon.Default());
      }
      e.getPresentation().setEnabled(selected.getStatus() == Operable.Status.STOPPED);
      e.getPresentation().setText(String.format("start '%s'", selected.getName()));
    } else {
      e.getPresentation().setEnabled(false);
    }
  }
}
