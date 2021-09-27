package com.volcengine.plugin.ui

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import java.awt.Dimension
import javax.swing.JComponent

class ExampleDialogWrapper(
    val project: Project
) : DialogWrapper(project) {

    private var mainPanel = ExamplePanel(project)

    init {
        init()
    }

    override fun createCenterPanel(): JComponent? = mainPanel.apply {
        minimumSize = Dimension(500, 340)
        preferredSize = Dimension(500, 340)
    }

    override fun doOKAction() {
        this.close(23)
    }
}