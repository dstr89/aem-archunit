package io.ecx.demo.core.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.ecx.demo.core.context.AppAemContextBuilder;
import io.ecx.demo.core.context.services.ServicesImpl;
import io.ecx.demo.core.context.services.ServicesMock;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(AemContextExtension.class)
class SimpleFilterTest {

    private static final AemContext context = new AppAemContextBuilder()
                                                .registerService(ServicesMock.FILTER_CHAIN)
                                                .registerService(ServicesImpl.SIMPLE_FILTER)
                                                .build();

    private static final String RESOURCE_PATH = "/content/test";
    private static final String SELECTOR_STRING = "selectors";

    private static SimpleFilter simpleFilter;
    private static FilterChain filterChain;

    @BeforeAll
    static void setup() {
        simpleFilter = context.getService(SimpleFilter.class);
        filterChain = context.getService(FilterChain.class);
    }

    @Test
    @DisplayName("GIVEN the request, WHEN is executed, THEN request should be filtered and contain corresponding header")
    void testDoFilter() throws ServletException, IOException {
        context.requestPathInfo().setResourcePath(RESOURCE_PATH);
        context.requestPathInfo().setSelectorString(SELECTOR_STRING);

        simpleFilter.doFilter(context.request(), context.response(), filterChain);

        assertEquals("true", context.response().getHeader("filtered"));
    }

}