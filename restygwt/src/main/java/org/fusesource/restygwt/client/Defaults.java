/**
 * Copyright (C) 2009-2010 the original author or authors.
 * See the notice.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.fusesource.restygwt.client;


import org.fusesource.restygwt.client.dispatcher.DispatcherFactoryDefault;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;

/**
 * Provides ability to set the default date format and service root (defaults to
 * GWT.getModuleBaseURL()).
 *
 *
 * @author <a href="http://www.acuedo.com">Dave Finch</a>
 *
 */
public class Defaults {

    public static IDispatcherFactory dispatcherFactory = new DispatcherFactoryDefault();

   // private static IDispatcher dispatcher = new DefaultDispatcher();

    private static String serviceRoot = GWT.getModuleBaseURL();
    private static String dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    // patch TNY: timeout ms,
    // if >-1, used in Method class to set timeout
    private static int requestTimeout = -1;


    public static String getServiceRoot() {
        return serviceRoot;
    }

    /**
     * sets the URL prepended to the value of Path annotations.
     *
     * @param serviceRoot
     */
    public static void setServiceRoot(String serviceRoot) {
        // GWT.getModuleBaseURL() is guaranteed to end with a slash, so should any custom service root
        if (!serviceRoot.endsWith("/")) {
            serviceRoot += "/";
        }
        Defaults.serviceRoot = serviceRoot;
    }

    public static String getDateFormat() {
        return dateFormat;
    }

    /**
     * Sets the format used when encoding and decoding Dates.
     *
     * @param dateFormat
     */
    public static void setDateFormat(String dateFormat) {
        Defaults.dateFormat = dateFormat;
    }

    public static final int getRequestTimeout() {
        return requestTimeout;
    }

    public static final void setRequestTimeout(int requestTimeout) {
        Defaults.requestTimeout = requestTimeout;
    }

    /**
     *
     * Set a Factory that produces instances of Dispatchers.
     * Here is the central place where you can change the behavior.
     *
     * Examples:
     * - DefaultDispatcher (contacts services, no fuzz attached)
     * - DispatcherRetrying (retries x times)
     * - DispatcherRetryingCaching (Retries and caches)
     *
     * @param dispatcherFactoryToSet
     */
    public static void setDispatcherFactory(IDispatcherFactory dispatcherFactoryToSet) {
        dispatcherFactory = dispatcherFactoryToSet;
    }

    /**
     * Return a dispatcher. Generated by a dispatcherFactory.
     * In DefaultMode DispatcherFactoryDefault is used.
     *
     * @return
     */
    public static IDispatcher getDispatcher(RequestBuilder requestBuilder, RequestCallback requestCallback) {

        return dispatcherFactory.get(requestBuilder, requestCallback);
    }
}
