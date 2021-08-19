package io.ecx.demo.core.listeners;

import java.util.Collections;

import org.apache.sling.api.SlingConstants;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.service.event.Event;

import io.ecx.demo.core.context.AppAemContextBuilder;
import io.ecx.demo.core.context.services.ServicesImpl;
import io.ecx.demo.core.context.services.ServicesMock;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(AemContextExtension.class)
class SimpleResourceListenerTest {

    private static final AemContext context = new AppAemContextBuilder()
                                                .registerService(ServicesMock.RESOURCE_RESOLVER_FACTORY_SERVICE)
                                                .registerInjectActivateService(ServicesImpl.SIMPLE_SERVICE)
                                                .registerInjectActivateService(ServicesImpl.SIMPLE_RESOURCE_LISTENER)
                                                .build();

    private static final String EVENT_TOPIC = "event/topic";
    private static final String PATH = "/content/test";
    private static final String RESOURCE_PATH = "/content/sample/event";

    private static SimpleResourceListener simpleResourceListener;
    private static ResourceResolver resourceResolver;

    @BeforeAll
    static void setup() throws LoginException {
        simpleResourceListener = context.getService(SimpleResourceListener.class);
        resourceResolver = context.getService(ResourceResolverFactory.class)
                                  .getServiceResourceResolver(Collections.singletonMap(ResourceResolverFactory.SUBSERVICE, "service-user"));
    }

    @Test
    @DisplayName("GIVEN a sample resource event with the correct topic, WHEN is occurred, THEN the event listener should catch the corresponding event and create a resource with corresponding properties")
    void testHandleEvent() {
        final Event resourceEvent = new Event(EVENT_TOPIC, Collections.singletonMap(SlingConstants.PROPERTY_PATH, PATH));
        simpleResourceListener.handleEvent(resourceEvent);

        final Resource resource = resourceResolver.getResource(RESOURCE_PATH);
        assertAll(
          () -> assertNotNull(resource),
          () -> assertEquals(EVENT_TOPIC, resource.getValueMap().get("topic", String.class)),
          () -> assertEquals(PATH, resource.getValueMap().get("path", String.class))
        );
    }

}

