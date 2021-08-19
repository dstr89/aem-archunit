package io.ecx.demo.core.context.services;

import javax.servlet.FilterChain;

import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.commons.scheduler.Scheduler;
import org.apache.sling.settings.SlingSettingsService;
import org.apache.sling.testing.resourceresolver.MockResourceResolverFactory;
import org.apache.sling.xss.XSSAPI;

import com.adobe.granite.ui.clientlibs.HtmlLibraryManager;
import com.day.cq.commons.Externalizer;
import com.day.cq.wcm.api.PageManagerFactory;

import io.wcm.testing.mock.aem.MockPageManagerFactory;
import io.ecx.demo.core.context.mocks.*;

public class ServicesMock {

    public static final OsgiService<Externalizer> EXTERNALIZER = new OsgiService<Externalizer>(Externalizer.class) {
        @Override
        public Externalizer getService() {
            return new MockExternalizer();
        }
    };

    public static final OsgiService<SlingSettingsService> SLING_SETTINGS = new OsgiService<SlingSettingsService>(SlingSettingsService.class) {
        @Override
        public SlingSettingsService getService() {
            return new MockSlingSettingsService();
        }
    };

    public static final OsgiService<FilterChain> FILTER_CHAIN = new OsgiService<FilterChain>(FilterChain.class) {
        @Override
        public FilterChain getService() {
            return new MockFilterChain();
        }
    };

    public static final OsgiService<ResourceResolverFactory> RESOURCE_RESOLVER_FACTORY_SERVICE = new OsgiService<ResourceResolverFactory>(ResourceResolverFactory.class) {
        @Override
        public ResourceResolverFactory getService() {
            return new MockResourceResolverFactory();
        }
    };


    public static final OsgiService<PageManagerFactory> PAGE_MANAGER_FACTORY = new OsgiService<PageManagerFactory>(PageManagerFactory.class)
    {
        @Override
        public PageManagerFactory getService()
        {
            return new MockPageManagerFactory();
        }
    };

    public static final OsgiService<XSSAPI> XSSAPI_SERVICE = new OsgiService<XSSAPI>(XSSAPI.class) {
        @Override
        public XSSAPI getService() {
            return new MockXSSAPI();
        }
    };

    public static final OsgiService<HtmlLibraryManager> HTML_LIBRARY_MANAGER = new OsgiService<HtmlLibraryManager>(HtmlLibraryManager.class) {
        @Override
        public HtmlLibraryManager getService() {
            return new MockHtmlLibraryManager();
        }
    };

    public static final OsgiService<Scheduler> SCHEDULER = new OsgiService<Scheduler>(Scheduler.class) {
        @Override
        public Scheduler getService() {
            return new MockScheduler();
        }
    };

    private ServicesMock() {}

}