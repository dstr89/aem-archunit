package io.ecx.demo.core.servlets;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import io.ecx.demo.core.context.AppAemContextBuilder;
import io.ecx.demo.core.context.services.ServicesImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(AemContextExtension.class)
class SimpleServletTest {

    private static final AemContext context = new AppAemContextBuilder().registerService(ServicesImpl.SIMPLE_SERVLET).build();

    private static SimpleServlet simpleServlet;

    @BeforeAll
    static void setup() {
        simpleServlet = context.getService(SimpleServlet.class);
        context.requestPathInfo().setExtension("txt");
        context.create().resource("/content/test", "jcr:title", "Resource title");
        context.request().setResource(context.currentResource("/content/test"));
    }

    @Test
    @DisplayName("GIVEN a sample resource with title property set, WHEN the servlet is triggered, THEN it should return the corresponding response")
    void doGet() throws ServletException, IOException {
        simpleServlet.doGet(context.request(), context.response());
        assertEquals("Title = Resource title", context.response().getOutputAsString());
    }

}