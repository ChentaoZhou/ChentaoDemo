package com.volcengine.plugin.ui

import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBScrollPane
import java.awt.Dimension
import java.awt.Font
import java.awt.GridLayout
import javax.swing.*

class ExamplePanel(
    project: Project
) : JPanel() {

    var moduleComboBox = JComboBox<Module>()

    private val aarsPanel = JPanel()

    init {
        initComponents(project)
    }

    private fun initComponents(project: Project) {
        layout = null

        val lblTicket = JLabel("Label1").apply {
            setBounds(28, 20, 277, 30)
            font = Font("Dialog", Font.ROMAN_BASELINE, 18)
        }
        add(lblTicket)

        val comboBoxLabel = JLabel("Label2").apply {
            setBounds(28, 59, 168, 30)
        }
        add(comboBoxLabel)
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