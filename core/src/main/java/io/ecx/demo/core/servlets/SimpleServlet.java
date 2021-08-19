package io.ecx.demo.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;

import com.day.cq.commons.jcr.JcrConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component(service = { Servlet.class })
@SlingServletResourceTypes(resourceTypes = "testing-basic/components/page", methods = HttpConstants.METHOD_GET, extensions = "txt")
@ServiceDescription("Simple Demo Servlet")
public class SimpleServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = -7462544660247544539L;

    @Override
    protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) throws ServletException, IOException {
        try {
            final Resource resource = request.getResource();
            response.setContentType("text/plain");
            response.getWriter().write("Title = " + resource.getValueMap().get(JcrConstants.JCR_TITLE));
        } catch (final RuntimeException e) {
            log.error("Exception in doGet method", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
