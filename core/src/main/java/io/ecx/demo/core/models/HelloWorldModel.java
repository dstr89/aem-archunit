package io.ecx.demo.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.day.cq.wcm.api.PageManagerFactory;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import static org.apache.sling.api.resource.ResourceResolver.PROPERTY_RESOURCE_TYPE;

@Slf4j
@Model(adaptables = Resource.class)
public class HelloWorldModel {

    @ValueMapValue(name = PROPERTY_RESOURCE_TYPE, injectionStrategy = InjectionStrategy.OPTIONAL)
    @Default(values = "No resourceType")
    protected String resourceType;

    @SlingObject
    private Resource currentResource;

    @OSGiService
    private PageManagerFactory pageManagerFactory;

    @Getter
    private String message;

    @PostConstruct
    protected void init() {
        try {
            String currentPagePath = this.pageManagerFactory.getPageManager(currentResource.getResourceResolver()).getContainingPage(currentResource).getPath();
            message = String.format("Hello World!%nResource type is: %s%nCurrent page is: %s%n",resourceType,currentPagePath);
        } catch (RuntimeException e) {
            log.error("Exception in handling event", e);
        }
    }

}
