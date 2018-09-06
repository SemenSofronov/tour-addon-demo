/*
 * Copyright (c) 2008-2013 Haulmont. All rights reserved.
 */

package com.company.touraddondemo.util;

import com.company.touraddondemo.jmx.UiTestSupport;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Objects;

/**
 * @deprecated Use masquerade connector and JMX {@link UiTestSupport} instead.
 */
@Deprecated
public class ServerSideHelper {

    private static final String UNLOCK_ALL_URL = "/dispatch/uitest/unlockAll";
    private static final String RELOAD_DYNAMIC_ATTRIBUTES = "/dispatch/uitest/reload_dynamic_attributes";
    private static final String RELOAD_ENTITY_LOG = "/dispatch/uitest/reload_entity_log";

    private String baseUrl;

    public ServerSideHelper(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void unlockAll() throws IOException {
        HttpClient httpClient = new DefaultHttpClient();

        try {
            HttpPost httpPost = new HttpPost(baseUrl + UNLOCK_ALL_URL);
            HttpResponse httpResponse = httpClient.execute(httpPost);

            StringWriter unlockResponse = new StringWriter();
            IOUtils.copy(httpResponse.getEntity().getContent(), unlockResponse);

            if (!Objects.equals(unlockResponse.toString(), "OK")) {
                throw new IllegalStateException("Could not unlock entity");
            }
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
    }

    public void reloadDynamicAttributesCache() throws IOException {
        HttpClient httpClient = new DefaultHttpClient();

        try {
            HttpPost httpPost = new HttpPost(baseUrl + RELOAD_DYNAMIC_ATTRIBUTES);
            HttpResponse httpResponse = httpClient.execute(httpPost);

            StringWriter response = new StringWriter();
            IOUtils.copy(httpResponse.getEntity().getContent(), response);

            if (!Objects.equals(response.toString(), "OK")) {
                throw new IllegalStateException("Could not reload dynamic attributes");
            }
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
    }

    public void reloadEntityLog() throws IOException {
        HttpClient httpClient = new DefaultHttpClient();

        try {
            HttpPost httpPost = new HttpPost(baseUrl + RELOAD_ENTITY_LOG);
            HttpResponse httpResponse = httpClient.execute(httpPost);

            StringWriter response = new StringWriter();
            IOUtils.copy(httpResponse.getEntity().getContent(), response);

            if (!Objects.equals(response.toString(), "OK")) {
                throw new IllegalStateException("Could not reload entity log config");
            }
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
    }
}