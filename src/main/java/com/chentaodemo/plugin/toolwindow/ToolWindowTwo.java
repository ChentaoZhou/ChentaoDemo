package com.chentaodemo.plugin.toolwindow;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.content.Content;

import javax.swing.*;
import java.util.ArrayList;

public class ToolWindowTwo {
    private JPanel baselinePanel;
    private JButton confirmButton;
    private JButton cancelButton;
    private JLabel notificationLabel;

    private final ArrayList<String> messageList = new ArrayList<>();
    private boolean enablePopupMsgWindow = false;

    public ToolWindowTwo(Project project, ToolWindow toolWindow) {
        notificationLabel.setVisible(false);
        confirmButton.addActionListener(e -> confirmed());
        cancelButton.addActionListener(e -> canceled(toolWindow));
    }

    private void confirmed() {
        confirmButton.setEnabled(false);
        cancelButton.setEnabled(false);
    }

    private void canceled(ToolWindow toolWindow) {
        Content content = toolWindow.getContentManager().getSelectedContent();
        toolWindow.getContentManager().removeContent(content, true);
    }

    public JPanel getContent() {
        return baselinePanel;
    }
}
