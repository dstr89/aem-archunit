package io.ecx.demo.core.context;

import org.apache.sling.models.impl.injectors.OSGiServiceInjector;
import org.apache.sling.models.spi.Injector;
import org.apache.sling.testing.mock.sling.ResourceResolverType;

import io.ecx.demo.core.context.services.OsgiService;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.wcmio.sling.MockSlingExtensions;

public class AppAemContextBuilder {

    private final AemContext aemContext;

    public AppAemContextBuilder() {
        this.aemContext = new AemContext(ResourceResolverType.RESOURCERESOLVER_MOCK);
    }

    public AppAemContextBuilder(final ResourceResolverType resourceResolverType) {
        this.aemContext = new AemContext(resourceResolverType);
    }

    public AemContext build() {
        return this.aemContext;
    }

    public AppAemContextBuilder loadResource(final String classPathResource, final String destinationPath) {
        this.aemContext.load().json(classPathResource, destinationPath);
        return this;
    }

    public <T> AppAemContextBuilder registerService(final OsgiService<T> service) {
        this.aemContext.registerService(service.getServiceClass(), service.getService(), service.getConfig());
        return this;
    }

    public <T> AppAemContextBuilder registerInjectActivateService(final OsgiService<T> service) {
        final T serviceImpl = service.getService();
        this.aemContext.registerInjectActivateService(serviceImpl, service.getConfig());
        this.aemContext.registerService(service.getServiceClass(), serviceImpl);
        return this;
    }

    public AppAemContextBuilder registerOSGiServiceInjector() {
        final OSGiServiceInjector osgiServiceInjector = new OSGiServiceInjector();
        osgiServiceInjector.activate(this.aemContext.componentContext());
        this.aemContext.registerService(Injector.class, osgiServiceInjector);
        return this;
    }

    public AppAemContextBuilder setRequestContext() {
        MockSlingExtensions.setRequestContext(this.aemContext, this.aemContext.request());
        return this;
    }

}