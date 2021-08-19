package io.ecx.demo.core.schedulers;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;

import io.ecx.demo.core.schedulers.config.SimpleScheduledTaskConfig;
import io.ecx.demo.core.services.SimpleService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component(service = Runnable.class, immediate = true)
@Designate(ocd = SimpleScheduledTaskConfig.class)
public class SimpleScheduledTask implements Runnable {

    @Reference
    private SimpleService simpleService;

    @Reference
    private Scheduler scheduler;

    private SimpleScheduledTaskConfig config;

    @Override
    public void run() {
        try {
            log.debug("SimpleScheduledTask is now running, myParameter='{}'", this.config.myParameter());

            final String path = "/content/sample/scheduledtask";
            final Map<String, Object> properties = new HashMap<>();
            properties.put("parameter", this.config.myParameter());

            this.simpleService.getOrCreateResource(path, properties);
        } catch (final RuntimeException e) {
            log.error("Exception in run method", e);
        }
    }

    @Activate
    protected void activate(final SimpleScheduledTaskConfig config) {
        this.config = config;
        this.scheduleTask();
    }

    @Modified
    public void modified(final SimpleScheduledTaskConfig config) {
        this.config = config;
        this.unScheduleTask();
        this.scheduleTask();
    }

    @Deactivate
    private void deactivate() {
        this.unScheduleTask();
    }

    private void scheduleTask() {
        try {
            log.info("Scheduling expression: {}", this.config.expression());
            final ScheduleOptions options = this.scheduler
                                              .EXPR(this.config.expression())
                                              .name(this.getClass().getName())
                                              .canRunConcurrently(this.config.concurrent());
            this.scheduler.schedule(this, options);
        } catch (final RuntimeException e) {
            log.error("Unable to schedule a job", e);
        }
    }

    private void unScheduleTask() {
        try {
            if (this.scheduler != null) {
                log.info("Removing scheduled job: {}", this.getClass().getName());
                this.scheduler.unschedule(this.getClass().getName());
            }
        } catch (final RuntimeException e) {
            log.error("Unable to un schedule a job", e);
        }
    }

}
