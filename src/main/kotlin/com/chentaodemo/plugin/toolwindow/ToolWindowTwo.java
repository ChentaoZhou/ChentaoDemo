package com.chentaodemo.plugin.toolwindow;

import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.content.Content;

import javax.swing.*;
import java.util.ArrayList;

public class ToolWindowTwo {
    private JPanel baselinePanel;
    private JComboBox<String> baselineComboBox;
    private JButton confirmButton;
    private JLabel titleLabel;
    private JButton cancelButton;
    private JLabel notificationLabel;
    private JComboBox<Module> moduleSelectComboBox;
    private JLabel moduleSelectLabel;
    private JProgressBar progressBar;
    private JLabel progressLabel;

    private static final String PLATFORM_DEP = "com.volcengine.mars:platform";

    private final ArrayList<String> messageList = new ArrayList<>();
    private boolean enablePopupMsgWindow = false;

    /**
     * the UI component that provides a baseline panel for user selection
     */
    public ToolWindowTwo(Project project, ToolWindow toolWindow) {
        notificationLabel.setVisible(false);
        Module[] modules = ModuleManager.getInstance(project).getModules();
        Module appModule = modules[0];
        for (Module module : modules) {
            if (module.getName().contains("app") && !module.getName().contains("demos")) {
                appModule = module;
            }
        }
        moduleSelectComboBox.setModel(new DefaultComboBoxModel(modules));
        moduleSelectComboBox.setSelectedItem(appModule);

        confirmButton.addActionListener(e -> confirmed(project, toolWindow));
        cancelButton.addActionListener(e -> canceled(toolWindow));
    }

    private void confirmed(Project project, ToolWindow toolWindow) {
        confirmButton.setEnabled(false);
        cancelButton.setEnabled(false);
    }

    private static void generateNotifacation(String msg, Project project, NotificationType type) {
        NotificationGroup notificationGroup = new NotificationGroup("BaselineWindow", NotificationDisplayType.BALLOON);
        notificationGroup.createNotification(msg, type).notify(project);
    }

    private void canceled(ToolWindow toolWindow) {
        Content content = toolWindow.getContentManager().getSelectedContent();
        toolWindow.getContentManager().removeContent(content, true);
    }

    public JPanel getContent() {
        return baselinePanel;
    }
}
