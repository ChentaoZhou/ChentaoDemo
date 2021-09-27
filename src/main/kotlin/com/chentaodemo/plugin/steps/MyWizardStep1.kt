package com.volcengine.plugin.step

import com.android.tools.adtui.util.FormScalingUtil
import com.android.tools.adtui.validation.Validator
import com.android.tools.adtui.validation.ValidatorPanel
import com.android.tools.idea.observable.BindingsManager
import com.android.tools.idea.observable.core.ObservableBool
import com.android.tools.idea.observable.ui.TextProperty
import com.android.tools.idea.ui.wizard.WizardUtils
import com.android.tools.idea.ui.wizard.deprecated.StudioWizardStepPanel
import com.android.tools.idea.wizard.model.ModelWizardStep
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.TextComponentAccessor
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.openapi.ui.VerticalFlowLayout
import com.intellij.ui.components.JBScrollPane
import com.volcengine.plugin.utils.MyWizardUtils
import java.awt.Font
import javax.swing.*

class MyWizardStep1(project: Project?) : ModelWizardStep<MyWizardModel?>(
    MyWizardModel(project), "Custom Wizard Step 1") {
    var validatorPanel: ValidatorPanel
    var rootPanel: JBScrollPane
    var contentPanel: JPanel
    var projectLocation: TextFieldWithBrowseButton
    var configLocation: TextFieldWithBrowseButton
    val bindings = BindingsManager()

    init {
        contentPanel = JPanel()
        contentPanel.layout = VerticalFlowLayout()
        validatorPanel = ValidatorPanel(this, contentPanel)
        rootPanel = wrappedWithVScroll(validatorPanel)
        FormScalingUtil.scaleComponentTree(this.javaClass, contentPanel)
        // title
        contentPanel.add(JPanel().apply {
            layout = VerticalFlowLayout()
            add(JLabel("Simulated Android project creation").apply {
                font = Font("Dialog", 1, 15)
            })
        })
        // project name
        contentPanel.add(JPanel().apply {
            layout = VerticalFlowLayout()
            add(JLabel("Project Name").apply {
                font = Font("Dialog", 1, 12)
            })
            add(JTextField().apply {
                bindings.bind(model.projectName, TextProperty(this))
                validatorPanel.registerValidator(model.projectName, object : Validator<String> {
                    override fun validate(value: String): Validator.Result =
                        MyWizardUtils.validateProjectFullPath(model.projectPath.get(), model.projectName.get())
                })
            })
        })
        // package name
        contentPanel.add(JPanel().apply {
            layout = VerticalFlowLayout()
            add(JLabel("Package Name").apply {
                font = Font("Dialog", 1, 12)
            })
            add(JTextField().apply {
                bindings.bind(model.packageName, TextProperty(this))
                validatorPanel.registerValidator(model.packageName, object : Validator<String> {
                    override fun validate(value: String): Validator.Result =
                        MyWizardUtils.validatePackageName(model.packageName.get())
                })
            })
        })
        // config file location
        contentPanel.add(JPanel().apply {
            layout = VerticalFlowLayout()
            add(JLabel("Choose your config file").apply {
                font = Font("Dialog", 1, 12)
            })
            add(TextFieldWithBrowseButton().apply {
                configLocation = this
                addBrowseFolderListener(
                    "Select Config File Location", null, null,
                    FileChooserDescriptorFactory.createSingleFileDescriptor(),
                    TextComponentAccessor.TEXT_FIELD_WHOLE_TEXT
                )
                bindings.bind(model.configPath, TextProperty(childComponent))
                validatorPanel.registerValidator(model.configPath, object : Validator<String> {
                    override fun validate(value: String): Validator.Result =
                        MyWizardUtils.validateConfigPath(model.configPath.get())
                })
            })
        })
        // project save path
        contentPanel.add(JPanel().apply {
            layout = VerticalFlowLayout()
            add(JLabel("Project Save Path").apply {
                font = Font("Dialog", 1, 12)
            })
            add(TextFieldWithBrowseButton().apply {
                projectLocation = this
                val initialLocation = WizardUtils.getProjectLocationParent().path
                childComponent.text = initialLocation
                addBrowseFolderListener(
                    "Select Project Location", null, null,
                    FileChooserDescriptorFactory.createSingleFolderDescriptor(),
                    TextComponentAccessor.TEXT_FIELD_WHOLE_TEXT
                )
                bindings.bind(model.projectPath, TextProperty(childComponent))
            })
        })
    }

    override fun canGoForward(): ObservableBool {
        return validatorPanel.hasErrors().not()
    }

    override fun getComponent(): JComponent {
        return rootPanel
    }

    override fun createDependentSteps(): MutableCollection<out ModelWizardStep<*>> {
        return arrayListOf(MyWizardStep2(model))
    }

    companion object {
        const val MAX_MODULE_NAME_LENGTH = 30
        fun wrappedWithVScroll(innerPanel: JPanel): JBScrollPane {
            val sp = JBScrollPane(
                StudioWizardStepPanel(innerPanel),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
            )
            sp.border = BorderFactory.createEmptyBorder()
            return sp
        }
    }
}