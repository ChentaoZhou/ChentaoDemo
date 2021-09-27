package com.chentaodemo.plugin.toolwindow;

import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

import javax.swing.*;

public class ToolWindowOne {

    private JPanel myToolWindowContent;
    private JLabel welcomeLabel;
    private JLabel subTitle1;
    private JLabel subTitle2;
    private JLabel prepare;
    private JLabel baseline;
    private JLabel config;
    private JLabel update;
    private JLabel start;
    private JLabel clickableLink1;
    private JLabel clickableLink2;
    private JButton step2Btn;
    private JButton step3Btn;
    private JButton step4Btn;
    public JLabel baselineVersionLabel;
    private JLabel onekitConfigLabel;

    public static String selectedVersion;
    public static Module selectedModule;

    public ToolWindowOne(Project project, ToolWindow toolWindow) {

        step2Btn.addActionListener(e -> {
            generateNotifacation("步骤2弹窗", project, NotificationType.INFORMATION);
        });

        step3Btn.addActionListener(e -> {
            ToolWindowTwo baselineWindow = new ToolWindowTwo(project, toolWindow);
            ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
            Content baselineContent = contentFactory.createContent(baselineWindow.getContent(), "", false);
            toolWindow.getContentManager().addContent(baselineContent);
            toolWindow.getContentManager().setSelectedContent(baselineContent);
        });

        step4Btn.addActionListener(e -> {
            generateNotifacation("步骤4弹窗", project, NotificationType.INFORMATION);
        });
    }

    private int recheckBaseline() {
        return Messages.showYesNoDialog(
                "在进行下一步前，需再次确认基线版本" + "\r\n 是否进入'选择基线'页面",
                "Ops.. 未缓存基线版本", Messages.getWarningIcon());
    }


    public JPanel getContent() {
        return myToolWindowContent;
    }

    private static void generateNotifacation(String msg, Project project, NotificationType type) {
        NotificationGroup notificationGroup = new NotificationGroup("MarsToolWindow", NotificationDisplayType.BALLOON);
        notificationGroup.createNotification(msg, type).notify(project);
    }

    private void createUIComponents() {
        //init custom component here
        subTitle2 = new LinkLabel("在浏览器中查阅相关文档", "https://www.volcengine.com/docs/6468/71258");
        clickableLink1 = new LinkLabel("创建一个账户，并开通veMARS服务", "https://www.volcengine.com/docs/6468/71606");
        clickableLink2 = new LinkLabel("在veMARS平台上创建一个应用程序", "https://www.volcengine.com/docs/6468/71519");

    }
}
