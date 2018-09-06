/*
 * Copyright (c) 2008-2013 Haulmont. All rights reserved.
 */

package com.company.touraddondemo.rules

import com.codeborne.selenide.Configuration
import com.company.touraddondemo.util.UiTestManager
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class WebBrowserReuseRule implements TestRule {
    @Override
    Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            void evaluate() throws Throwable {
                Configuration.holdBrowserOpen = true
                Configuration.reopenBrowserOnFail = false
                Configuration.baseUrl = UiTestManager.getBaseUrl()

                try {
                    base.evaluate()
                } finally {
                    Configuration.holdBrowserOpen = false
                }
            }
        }
    }
}