package io.ecx.demo.core.context.mocks;

import org.apache.sling.xss.XSSAPI;

public class MockXSSAPI implements XSSAPI {

    @Override
    public Integer getValidInteger(final String s, final int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Long getValidLong(final String s, final long l) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Double getValidDouble(final String s, final double v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getValidDimension(final String s, final String s1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getValidHref(final String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getValidJSToken(final String s, final String s1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getValidStyleToken(final String s, final String s1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getValidCSSColor(final String s, final String s1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getValidMultiLineComment(final String s, final String s1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getValidJSON(final String s, final String s1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getValidXML(final String s, final String s1) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String encodeForHTML(final String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String encodeForHTMLAttr(final String s) {
        return s;
    }

    @Override
    public String encodeForXML(final String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String encodeForXMLAttr(final String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String encodeForJSString(final String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String encodeForCSSString(final String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String filterHTML(final String s) {
        throw new UnsupportedOperationException();
    }

}