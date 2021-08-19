package io.ecx.demo.core.context.mocks;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;

public class MockScheduler implements Scheduler {

    @Override
    public boolean schedule(final Object o, final ScheduleOptions scheduleOptions) {
        return true;
    }

    @Override
    public boolean unschedule(final String s) {
        return true;
    }

    @Override
    public ScheduleOptions NOW() {
        return null;
    }

    @Override
    public ScheduleOptions NOW(final int i, final long l) {
        return null;
    }

    @Override
    public ScheduleOptions AT(final Date date) {
        return null;
    }

    @Override
    public ScheduleOptions AT(final Date date, final int i, final long l) {
        return null;
    }

    @Override
    public ScheduleOptions EXPR(final String s) {
        return null;
    }

    @Override
    public void addJob(final String s, final Object o, final Map<String, Serializable> map, final String s1, final boolean b) throws Exception {
        // do nothing
    }

    @Override
    public void addPeriodicJob(final String s, final Object o, final Map<String, Serializable> map, final long l, final boolean b) throws Exception {
        // do nothing
    }

    @Override
    public void addPeriodicJob(final String s, final Object o, final Map<String, Serializable> map, final long l, final boolean b, final boolean b1) throws Exception {
        // do nothing
    }

    @Override
    public void fireJob(final Object o, final Map<String, Serializable> map) throws Exception {
        // do nothing
    }

    @Override
    public boolean fireJob(final Object o, final Map<String, Serializable> map, final int i, final long l) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void fireJobAt(final String s, final Object o, final Map<String, Serializable> map, final Date date) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean fireJobAt(final String s, final Object o, final Map<String, Serializable> map, final Date date, final int i, final long l) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeJob(final String s) throws NoSuchElementException {
        // do nothing
    }

}