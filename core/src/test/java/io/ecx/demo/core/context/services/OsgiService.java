package io.ecx.demo.core.context.services;

import java.util.Map;
import java.util.HashMap;

public abstract class OsgiService<T> {

    private final Class<T> typeParameterClass;

    public OsgiService(final Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    public Class<T> getServiceClass() {
        return this.typeParameterClass;
    }

    public abstract T getService();

    public Map<String, Object> getConfig() {
        return new HashMap<>();
    }

}