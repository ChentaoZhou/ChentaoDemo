package com.chentaodemo.plugin.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.wm.ToolWindowManager

class OpenToolWindowAction:AnAction() {
    override fun actionPerformed(event: AnActionEvent) {
        if (event.project != null) {
            val toolWindow = ToolWindowManager.getInstance(event.project!!).getToolWindow("Demo")
            toolWindow?.show()
        }
    }
}