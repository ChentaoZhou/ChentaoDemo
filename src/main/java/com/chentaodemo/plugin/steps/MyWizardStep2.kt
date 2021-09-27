package com.volcengine.plugin.step

import com.android.tools.idea.wizard.model.SkippableWizardStep
import com.intellij.openapi.ui.VerticalFlowLayout
import java.awt.Font
import javax.swing.*

class MyWizardStep2(model: MyWizardModel) :
    SkippableWizardStep<MyWizardModel?>(model, "Custom Wizard Step 2") {

    override fun getComponent(): JComponent {
        var contentPanel = JPanel()
        return contentPanel.apply {
            layout = VerticalFlowLayout()
            contentPanel.add(JPanel().apply {
                layout = VerticalFlowLayout()
                add(JLabel("This is an example and empty step").apply {
                    font = Font("Dialog", 1, 12)
                })
            })

            // label
            contentPanel.add(JPanel().apply {
                layout = VerticalFlowLayout()
                add(JLabel("Label 1").apply {
                    font = Font("Dialog", 1, 17)
                })

                add(JLabel("Label 2"))
            })

        }
    }
}