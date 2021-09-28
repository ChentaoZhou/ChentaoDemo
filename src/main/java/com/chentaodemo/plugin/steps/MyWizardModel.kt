package com.volcengine.plugin.step

import com.android.tools.idea.observable.core.StringValueProperty
import com.android.tools.idea.wizard.model.WizardModel
import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project


class MyWizardModel(project: Project?) : WizardModel() {
    val projectName = StringValueProperty()
    val packageName = StringValueProperty()
    val configPath = StringValueProperty()
    val projectPath = StringValueProperty()
    val project = project

    override fun handleFinished() {
        val notificationGroup = NotificationGroup("MyWizardModel", NotificationDisplayType.BALLOON)
        notificationGroup.createNotification("ProjectWizardModel done", NotificationType.INFORMATION).notify(project)
    }
}