/*
 * Copyright (c) 2008-2013 Haulmont. All rights reserved.
 */

package com.company.touraddondemo.rules

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Checks exceptions in app.log
 */
class LogErrorsVerifier implements TestRule {
    @Override
    Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            void evaluate() throws Throwable {
                // todo remember log position

                base.evaluate()

                // todo check errors
            }
        }
    }
}