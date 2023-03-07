package com.flexpag.paymentscheduler.core.gateway;

import com.flexpag.paymentscheduler.core.domain.Schedule;
import com.flexpag.paymentscheduler.infra.gateway.ScheduleJPA;

import java.util.List;

public interface ScheduleGateway {

    List<ScheduleJPA> findAll();

    ScheduleJPA findById(long id);

    ScheduleJPA createSchedule(Schedule schedule);

    ScheduleJPA updateStatus(Schedule schedule);

    ScheduleJPA updateScheduling(Schedule schedule);

}
