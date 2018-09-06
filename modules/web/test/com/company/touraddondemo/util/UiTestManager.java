/*
 * Copyright (c) 2008-2013 Haulmont. All rights reserved.
 */

package com.company.touraddondemo.util;

import com.company.touraddondemo.jmx.UiTestSupport;
import groovy.sql.Sql;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class UiTestManager {

    private static final String BASE_URL = "http://localhost:8080/app";
    private static final String DB_CONNECTION_URL = "jdbc:postgresql://localhost/touraddondemo";

    private static SingleConnectionDataSource cachedDatasource;

    /**
     * @deprecated Use standard selenide.baseUrl system property
     */
    @Deprecated
    public static String getBaseUrl() {
        return System.getProperty("cuba.testui.baseUrl", BASE_URL);
    }

    /**
     * @deprecated Use masquerade connector and JMX {@link UiTestSupport} instead.
     */
    @Deprecated
    public static String getServiceUrl() {
        return System.getProperty("cuba.testui.serviceUrl", BASE_URL);
    }


    public static Sql getDb() throws SQLException {
        if (cachedDatasource == null) {
            String dbUrl = System.getProperty("cuba.testui.dbUrl", DB_CONNECTION_URL);
            String user = System.getProperty("cuba.testui.dbUser", "postgres");
            String password = System.getProperty("cuba.testui.dbPassword", "root");

            cachedDatasource = new SingleConnectionDataSource(dbUrl, user, password);
        }

        return new Sql(cachedDatasource);
    }

    public static void executeSql(String sql) throws SQLException {
        Sql db = getDb();
        try {
            db.execute(sql);
        } finally {
            db.close();
        }
    }

    public static void executeSqlScript(String sqlPath) throws SQLException, IOException {
        InputStream sqlStream = UiTestManager.class.getResourceAsStream(sqlPath);
        String sql = IOUtils.toString(sqlStream, StandardCharsets.UTF_8.name());

        Sql db = getDb();
        try {
            db.execute(sql);
        } finally {
            db.close();
        }
    }

    public static void unlockAll() throws IOException {
        new ServerSideHelper(getServiceUrl()).unlockAll();
    }

    public static void reloadDynamicAttributesCache() throws IOException {
        new ServerSideHelper(getServiceUrl()).reloadDynamicAttributesCache();
    }

    public static void reloadEntityLog() throws IOException {
        new ServerSideHelper(getServiceUrl()).reloadEntityLog();
    }
}