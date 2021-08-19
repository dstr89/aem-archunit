package io.ecx.demo.core.services.impl;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ResourceUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import io.ecx.demo.core.services.SimpleService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component(service = SimpleService.class, immediate = true)
public class SimpleServiceImpl implements SimpleService {

    private static final String SERVICE_USER = "service-user"; //TODO: create system user in AEM

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Override
    public Optional<Resource> getOrCreateResource(final String path, final Map<String, Object> resourceProperties) {
        try (final ResourceResolver resourceResolver = this.resourceResolverFactory.getServiceResourceResolver(this.getAuthenticationInfo())) {
            return Optional.of(ResourceUtil.getOrCreateResource(resourceResolver, path, resourceProperties, "", true));
        } catch (final LoginException | PersistenceException ex) {
            log.error("Exception during creating resource " + ex);
        }
        return Optional.empty();
    }

    private Map<String, Object> getAuthenticationInfo() {
        return Collections.singletonMap(ResourceResolverFactory.SUBSERVICE, SERVICE_USER);
    }

}