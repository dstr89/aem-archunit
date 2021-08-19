package io.ecx.demo.core.listeners;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.SlingConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import io.ecx.demo.core.services.SimpleService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component(service = { SimpleResourceListener.class, EventHandler.class }, property = {
  EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/*" })
@ServiceDescription("Demo to listen on changes in the resource tree")
public class SimpleResourceListener implements EventHandler {

    @Reference
    private SimpleService simpleService;

    public void handleEvent(final Event event) {
        try {
            log.debug("Resource event: {} at: {}", event.getTopic(), event.getProperty(SlingConstants.PROPERTY_PATH));

            final String path = "/content/sample/event";
            final Map<String, Object> properties = new HashMap<>();
            properties.put("topic", event.getTopic());
            properties.put("path", event.getProperty(SlingConstants.PROPERTY_PATH).toString());

            this.simpleService.getOrCreateResource(path, properties);
        } catch (final RuntimeException e) {
            log.error("Exception in handling event", e);
        }
    }

}