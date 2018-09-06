/*
 * Copyright (c) 2008-2013 Haulmont. All rights reserved.
 */

package com.company.touraddondemo

import com.codeborne.selenide.junit.ScreenShooter
import com.company.touraddondemo.rules.WebBrowserReuseRule
import org.junit.Rule
import org.junit.rules.TestRule
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.bridge.SLF4JBridgeHandler

import java.util.logging.Level
import java.util.logging.LogManager

abstract class BaseTest {

    // route all logging to SLF4J
    static {
        LogManager.getLogManager().reset()

        SLF4JBridgeHandler.removeHandlersForRootLogger()
        SLF4JBridgeHandler.install()

        java.util.logging.Logger.getLogger("global").setLevel(Level.FINEST)
    }

    @Deprecated
    Logger log = LoggerFactory.getLogger(getClass())

    @Rule
    public TestRule screenShotFailedTests = ScreenShooter.failedTests()

    @Rule
    public TestRule reuseWebBrowser = new WebBrowserReuseRule()
}