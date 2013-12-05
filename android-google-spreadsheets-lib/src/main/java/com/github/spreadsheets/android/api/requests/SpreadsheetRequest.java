package com.github.spreadsheets.android.api.requests;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.google.api.client.xml.XmlNamespaceDictionary;
import com.google.api.client.xml.XmlObjectParser;

public abstract class SpreadsheetRequest<T> extends Request<T> {
    private static final XmlNamespaceDictionary DICTIONARY = new XmlNamespaceDictionary()
            .set("", "http://www.w3.org/2005/Atom")
            .set("gd", "http://schemas.google.com/g/2005")
            .set("gs", "http://schemas.google.com/spreadsheets/2006")
            .set("gsx", "http://schemas.google.com/spreadsheets/2006/extended")
            .set("openSearch", "http://a9.com/-/spec/opensearch/1.1/")
            .set("xml", "http://www.w3.org/XML/1998/namespace")
            .set("app", "http://www.w3.org/2007/app");
    private static final String PROTOCOL_CONTENT_TYPE = "application/atom+xml; charset=UTF-8";

    private final Response.Listener<T> mListener;
    final XmlObjectParser parser = new XmlObjectParser(DICTIONARY);

    SpreadsheetRequest(int method, String url, Listener<T> listener, ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mListener = listener;
    }

//    SpreadsheetRequest(int method, String url, Object object, Listener<T> listener,
//                                                                    ErrorListener errorListener ) {
//        super(method, url, errorListener);
//        this.mListener = listener;
//    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    abstract protected Response<T> parseNetworkResponse(NetworkResponse response);

//    @Override
//    abstract public byte[] getBody() throws AuthFailureError;

    @Override
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }
}
