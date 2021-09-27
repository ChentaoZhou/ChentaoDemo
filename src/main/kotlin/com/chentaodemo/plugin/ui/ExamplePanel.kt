package com.volcengine.plugin.ui

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.openapi.module.Module
import com.intellij.openapi.module.ModuleManager
import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBScrollPane
import java.awt.Dimension
import java.awt.Font
import java.awt.GridLayout
import javax.swing.*

class ExamplePanel(
    project: Project,
    baselineVersion: String,
    aarInfoDialog: ExampleDialogWrapper
) : JPanel() {

    val checkBoxList = ArrayList<JCheckBox>()
    var moduleComboBox = JComboBox<Module>()

    private val aarsPanel = JPanel()

    init {
        initComponents(project, baselineVersion)
    }

    private fun initComponents(project: Project, baselineVersion: String) {
        layout = null
        val notificationGroup = NotificationGroup("AarPanel", NotificationDisplayType.BALLOON)

        val lblTicket = JLabel("选择您要添加的veMARS服务").apply {
            setBounds(28, 20, 277, 30)
            font = Font("Dialog", Font.ROMAN_BASELINE, 18)
        }
        add(lblTicket)

        val comboBoxLabel = JLabel("请选择您要加入依赖的模块").apply {
            setBounds(28, 59, 168, 30)
        }
        add(comboBoxLabel)

        val modules = ModuleManager.getInstance(project).modules
        var appModule = modules[0]

        moduleComboBox = JComboBox(modules)
        moduleComboBox.selectedItem = appModule
        moduleComboBox.setBounds(196, 59, 215, 30)
        add(moduleComboBox)

        aarsPanel.layout = GridLayout(0, 1)
        aarsPanel.setBounds(25, 101, 430, 260)

        val scrollPane =
            JBScrollPane(aarsPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER)
        scrollPane.setBounds(25, 101, 430, 260)
        add(scrollPane)
    }

    override fun getPreferredSize(): Dimension = Dimension(500, 395)
}