package io.ecx.demo.core.services.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import io.ecx.demo.core.context.AppAemContextBuilder;
import io.ecx.demo.core.context.services.ServicesImpl;
import io.ecx.demo.core.context.services.ServicesMock;
import io.ecx.demo.core.services.SimpleService;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(AemContextExtension.class)
class SimpleServiceTest {

    private static final AemContext context = new AppAemContextBuilder()
                                                .registerService(ServicesMock.RESOURCE_RESOLVER_FACTORY_SERVICE)
                                                .registerInjectActivateService(ServicesImpl.SIMPLE_SERVICE)
                                                .build();

    private static SimpleService simpleService;

    @BeforeAll
    static void setup() {
        simpleService = context.getService(SimpleService.class);
    }

    @Test
    @DisplayName("GIVEN a sample resource properties, WHEN service method is executed, THEN the service should return a resource with corresponding properties")
    void getOrCreateResourceTest() {
        final String path = "/content/sample";
        final Map<String, Object> properties = new HashMap<>();
        properties.put("property", "property value");
        properties.put("property2", "property value 2");

        final Optional<Resource> resource = simpleService.getOrCreateResource(path, properties);

        assertAll(
          () -> assertNotNull(resource.get()),
          () -> assertEquals("/content/sample", resource.get().getPath()),
          () -> assertEquals("property value", resource.get().getValueMap().get("property", String.class)),
          () -> assertEquals("property value 2", resource.get().getValueMap().get("property2", String.class))
        );
    }

}