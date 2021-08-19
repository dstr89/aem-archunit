package io.ecx.demo.core.schedulers;

import java.util.Collections;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.ecx.demo.core.context.AppAemContextBuilder;
import io.ecx.demo.core.context.services.ServicesImpl;
import io.ecx.demo.core.context.services.ServicesMock;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(AemContextExtension.class)
class SimpleScheduledTaskTest {

    private static final AemContext context = new AppAemContextBuilder()
                                                .registerService(ServicesMock.RESOURCE_RESOLVER_FACTORY_SERVICE)
                                                .registerService(ServicesMock.SCHEDULER)
                                                .registerInjectActivateService(ServicesImpl.SIMPLE_SERVICE)
                                                .registerInjectActivateService(ServicesImpl.SIMPLE_SCHEDULED_TASK)
                                                .build();

    private static final String RESOURCE_PATH = "/content/sample/scheduledtask";

    private static SimpleScheduledTask simpleScheduledTask;
    private static ResourceResolver resourceResolver;

    @BeforeAll
    static void setup() throws LoginException {
        simpleScheduledTask = context.getService(SimpleScheduledTask.class);
        simpleScheduledTask.run();
        resourceResolver = context.getService(ResourceResolverFactory.class)
                                  .getServiceResourceResolver(Collections.singletonMap(ResourceResolverFactory.SUBSERVICE, "service-user"));
    }

    @Test
    @DisplayName("GIVEN the scheduler config, WHEN the scheduler is activated and running, THEN the scheduler should create a resource with corresponding property")
    void testRun() {
        final Resource resource = resourceResolver.getResource(RESOURCE_PATH);

        assertAll(
          () -> assertNotNull(resource),
          () -> assertEquals("dummy value", resource.getValueMap().get("parameter", String.class))
        );
    }

}
