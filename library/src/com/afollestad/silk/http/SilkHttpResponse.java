package com.afollestad.silk.http;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the response to an HTTP request.
 *
 * @author Aidan Follestad (afollestad)
 */
public class SilkHttpResponse {

    SilkHttpResponse(HttpResponse response) {
        mHeaders = new ArrayList<SilkHttpHeader>();
        for (Header header : response.getAllHeaders())
            mHeaders.add(new SilkHttpHeader(header));
    }

    private List<SilkHttpHeader> mHeaders;
    private HttpEntity mEntity;

    /**
     * Gets all headers with a specified name from the response.
     */
    public SilkHttpHeader[] getHeaders(String name) {
        List<SilkHttpHeader> headers = new ArrayList<SilkHttpHeader>();
        for (SilkHttpHeader h : mHeaders) {
            if (h.getName().equalsIgnoreCase(name))
                headers.add(h);
        }
        return headers.toArray(new SilkHttpHeader[headers.size()]);
    }

    /**
     * Gets the response entity.
     */
    public HttpEntity getContent() {
        return mEntity;
    }

    /**
     * Gets the response content as a string.
     */
    public String getContentString() throws Exception {
        return EntityUtils.toString(mEntity);
    }

    /**
     * Gets the response content as a string using the specified charset.
     */
    public String getContentString(String defaultCharset) throws Exception {
        return EntityUtils.toString(mEntity, defaultCharset);
    }

    /**
     * Gets the response content as a raw byte array.
     */
    public byte[] getContentBytes() throws Exception {
        return EntityUtils.toByteArray(mEntity);
    }
}