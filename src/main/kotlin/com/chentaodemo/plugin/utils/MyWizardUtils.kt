package com.volcengine.plugin.utils

import com.android.tools.adtui.validation.Validator
import com.intellij.openapi.util.io.FileUtil
import com.volcengine.plugin.step.MyWizardStep1
import java.io.File
import java.util.regex.Pattern

object MyWizardUtils {
    private val VALID_PROJECT = Pattern.compile("^([a-zA-Z]++([_]?[a-zA-Z0-9]+)*)++$")
    private val VALID_PACKAGE = Pattern.compile("^([a-zA-Z]++([_.]?[a-zA-Z0-9]+)*)++$")

    fun validateProjectFullPath(projectPath: String, projectName: String): Validator.Result {
        if (projectName.isEmpty()) {
            return errorResult("Please enter a name for the project.")
        }
        val loc = File(FileUtil.toSystemDependentName(projectPath), projectName)
        if (loc.exists()) {
            return errorResult("Project location already exists: " + loc.path)
        }
        if (!VALID_PROJECT.matcher(projectName).matches()) {
            return errorResult(
                "Invalid project name: '$projectName' - must be a valid name (letter_with_underscores)."
            )
        }
        if (projectName.length > MyWizardStep1.MAX_MODULE_NAME_LENGTH) {
            return errorResult(
                "Invalid project name - must be less than " +
                        MyWizardStep1.MAX_MODULE_NAME_LENGTH +
                        " characters."
            )
        }
        return Validator.Result.OK
    }

    fun validatePackageName(packageName: String): Validator.Result {
        if (!VALID_PACKAGE.matcher(packageName).matches()) {
            return errorResult(
                "Invalid package name: '$packageName'."
            )
        }
        return Validator.Result.OK
    }

    fun validateConfigPath(configPath: String): Validator.Result {
        if (!File(configPath).exists()) {
            return errorResult("Config file location not exists")
        }
        return Validator.Result.OK
    }

    fun errorResult(message: String): Validator.Result {
        return Validator.Result(Validator.Severity.ERROR, message)
    }

}