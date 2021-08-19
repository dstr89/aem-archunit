package io.ecx.demo.core.context.mocks;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;

import org.apache.sling.api.SlingHttpServletRequest;

import com.adobe.granite.ui.clientlibs.ClientLibrary;
import com.adobe.granite.ui.clientlibs.HtmlLibrary;
import com.adobe.granite.ui.clientlibs.HtmlLibraryManager;
import com.adobe.granite.ui.clientlibs.LibraryType;

public class MockHtmlLibraryManager implements HtmlLibraryManager {

    @Override
    public void writeJsInclude(final SlingHttpServletRequest slingHttpServletRequest, final Writer writer, final String... strings) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeJsInclude(final SlingHttpServletRequest slingHttpServletRequest, final Writer writer, final boolean b, final String... strings) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeCssInclude(final SlingHttpServletRequest slingHttpServletRequest, final Writer writer, final String... strings) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeCssInclude(final SlingHttpServletRequest slingHttpServletRequest, final Writer writer, final boolean b, final String... strings) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeThemeInclude(final SlingHttpServletRequest slingHttpServletRequest, final Writer writer, final String... strings) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeIncludes(final SlingHttpServletRequest slingHttpServletRequest, final Writer writer, final String... strings) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public HtmlLibrary getLibrary(final LibraryType libraryType, final String s) {
        return null;
    }

    @Override
    public HtmlLibrary getLibrary(final SlingHttpServletRequest slingHttpServletRequest) {
        return null;
    }

    @Override
    public boolean isMinifyEnabled() {
        return false;
    }

    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    @Override
    public boolean isGzipEnabled() {
        return false;
    }

    @Override
    public Map<String, ClientLibrary> getLibraries() {
        return null;
    }

    @Override
    public Collection<ClientLibrary> getLibraries(final String[] strings, final LibraryType libraryType, final boolean b, final boolean b1) {
        final List<ClientLibrary> libraries = new ArrayList<>();

        Arrays.stream(strings).forEach(library -> {
            libraries.add(new MockClientLibrary(library, true));
        });

        return libraries;
    }

    @Override
    public Collection<ClientLibrary> getThemeLibraries(final String[] strings, final LibraryType libraryType, final String s, final boolean b) {
        return null;
    }


    @Override
    public void invalidateOutputCache() throws RepositoryException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void ensureCached() throws IOException, RepositoryException {
        throw new UnsupportedOperationException();
    }

}