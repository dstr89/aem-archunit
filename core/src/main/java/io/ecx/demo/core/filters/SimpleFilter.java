package io.ecx.demo.core.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.servlets.annotations.SlingServletFilter;
import org.apache.sling.servlets.annotations.SlingServletFilterScope;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.osgi.service.component.propertytypes.ServiceRanking;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component(service = Filter.class)
@SlingServletFilter(scope = SlingServletFilterScope.REQUEST)
@ServiceRanking(-700)
@ServiceDescription("Demo to filter incoming requests")
public class SimpleFilter implements Filter {

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain) throws IOException, ServletException {
        try {
            final SlingHttpServletRequest slingRequest = (SlingHttpServletRequest) request;
            log.debug("request for {}, with selector {}", slingRequest.getRequestPathInfo().getResourcePath(),
              slingRequest.getRequestPathInfo().getSelectorString());

            final SlingHttpServletResponse slingResponse = (SlingHttpServletResponse) response;
            slingResponse.setHeader("filtered", "true");
        } catch (final RuntimeException e) {
            log.error("Exception in request filter", e);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void init(final FilterConfig filterConfig) {
        //do nothing
    }

    @Override
    public void destroy() {
        //do nothing
    }

}