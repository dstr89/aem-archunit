package io.ecx.demo.core.services;

import java.util.Map;
import java.util.Optional;

import org.apache.sling.api.resource.Resource;

public interface SimpleService {

    Optional<Resource> getOrCreateResource(String path, Map<String, Object> resourceProperties);

}