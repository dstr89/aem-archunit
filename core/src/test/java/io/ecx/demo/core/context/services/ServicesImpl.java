package io.ecx.demo.core.context.services;

import java.util.HashMap;
import java.util.Map;
import io.ecx.demo.core.filters.SimpleFilter;
import io.ecx.demo.core.listeners.SimpleResourceListener;
import io.ecx.demo.core.schedulers.SimpleScheduledTask;
import io.ecx.demo.core.services.*;
import io.ecx.demo.core.services.impl.*;
import io.ecx.demo.core.servlets.SimpleServlet;

public class ServicesImpl {
    public static final OsgiService<SimpleFilter> SIMPLE_FILTER = new OsgiService<SimpleFilter>(SimpleFilter.class) {
        @Override
        public SimpleFilter getService() {
            return new SimpleFilter();
        }
    };

    public static final OsgiService<SimpleResourceListener> SIMPLE_RESOURCE_LISTENER = new OsgiService<SimpleResourceListener>(SimpleResourceListener.class) {
        @Override
        public SimpleResourceListener getService() {
            return new SimpleResourceListener();
        }
    };

    public static final OsgiService<SimpleScheduledTask> SIMPLE_SCHEDULED_TASK = new OsgiService<SimpleScheduledTask>(SimpleScheduledTask.class) {
        @Override
        public SimpleScheduledTask getService() {
            return new SimpleScheduledTask();
        }

        @Override
        public Map<String, Object> getConfig() {
            final Map<String, Object> config = new HashMap<>();
            config.put("scheduler.expression", "*/30 * * * * ?");
            config.put("scheduler.concurrent", "{Boolean}false");
            config.put("myParameter", "dummy value");
            return config;
        }
    };

    public static final OsgiService<SimpleServlet> SIMPLE_SERVLET = new OsgiService<SimpleServlet>(SimpleServlet.class) {
        @Override
        public SimpleServlet getService() {
            return new SimpleServlet();
        }
    };

    public static final OsgiService<SimpleService> SIMPLE_SERVICE = new OsgiService<SimpleService>(SimpleService.class) {
        @Override
        public SimpleService getService() {
            return new SimpleServiceImpl();
        }
    };

    private ServicesImpl() {
    }

}