package io.ecx.demo.core.models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import io.ecx.demo.core.context.AppAemContextBuilder;
import io.ecx.demo.core.context.services.ServicesMock;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(AemContextExtension.class)
class HelloWorldModelTest {

    private static final AemContext context = new AppAemContextBuilder()
                                                .registerService(ServicesMock.PAGE_MANAGER_FACTORY)
                                                .loadResource("/content/testPage/testPage.json", "/content/testPage")
                                                .build();

    @BeforeAll
    static void setup() {
        context.addModelsForPackage("io.ecx.demo.core.models");
    }

    @Test
    @DisplayName("GIVEN a content page, WHEN resource is adapted to the model, THEN the model should have the corresponding message")
    void testResourceType() {
        final HelloWorldModel helloWorldModel = context.currentResource("/content/testPage/jcr:content").adaptTo(HelloWorldModel.class);

        final String resourceType = "demo-site/components/page";
        final String currentPagePath = "/content/testPage";
        final String message = String.format("Hello World!\nResource type is: %s\nCurrent page is: %s\n", resourceType, currentPagePath);

        assertAll(() -> assertNotNull(helloWorldModel), () -> assertEquals(message, helloWorldModel.getMessage()));
    }

}