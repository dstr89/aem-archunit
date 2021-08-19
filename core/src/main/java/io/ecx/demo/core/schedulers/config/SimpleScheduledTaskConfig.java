package io.ecx.demo.core.schedulers.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "A scheduled task", description = "Simple demo for cron-job like task with properties")
public @interface SimpleScheduledTaskConfig {

    @AttributeDefinition(name = "Cron-job expression") String expression() default "*/30 * * * * ?";

    @AttributeDefinition(name = "Concurrent task", description = "Whether or not to schedule this task concurrently") boolean concurrent() default false;

    @AttributeDefinition(name = "A parameter", description = "Can be configured in /system/console/configMgr") String myParameter() default "dummy value";

}
