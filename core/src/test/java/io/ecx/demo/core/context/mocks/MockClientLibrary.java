package io.ecx.demo.core.context.mocks;

import java.util.Map;
import java.util.Set;

import com.adobe.granite.ui.clientlibs.ClientLibrary;
import com.adobe.granite.ui.clientlibs.LibraryType;

public class MockClientLibrary implements ClientLibrary {

    private final String path;
    private final boolean allowProxy;

    public MockClientLibrary(final String path, final boolean allowProxy) {
        this.path = path;
        this.allowProxy = allowProxy;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public String getIncludePath(final LibraryType libraryType) {
        return this.path;
    }

    @Override
    public String getIncludePath(final LibraryType libraryType, boolean b) {
        return this.path;
    }

    @Override
    public Set<LibraryType> getTypes() {
        return null;
    }

    @Override
    public String getThemeName() {
        return null;
    }

    @Override
    public String getThemeLibId() {
        return null;
    }

    @Override
    public String[] getCategories() {
        return new String[0];
    }

    @Override
    public String[] getEmbeddedCategories() {
        return new String[0];
    }

    @Override
    public String[] getDependentCategories() {
        return new String[0];
    }

    @Override
    public String[] getChannels() {
        return new String[0];
    }

    @Override
    public Map<String, ? extends ClientLibrary> getDependencies(final boolean b) {
        return null;
    }

    @Override
    public Map<String, ? extends ClientLibrary> getEmbedded(final LibraryType libraryType) {
        return null;
    }

    @Override
    public boolean allowProxy() {
        return this.allowProxy;
    }

}