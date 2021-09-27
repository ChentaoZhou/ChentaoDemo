package com.chentaodemo.plugin.actions

import com.android.tools.idea.ui.wizard.StudioWizardDialogBuilder
import com.android.tools.idea.wizard.model.ModelWizard
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.volcengine.plugin.step.MyWizardStep1

class CreateNewWizardAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {

        StudioWizardDialogBuilder(
            ModelWizard.Builder().addStep(MyWizardStep1(e.project)).build(),
            "Simulated Android project creation & Wizard Step 1"
        ).build().show()
    }
}