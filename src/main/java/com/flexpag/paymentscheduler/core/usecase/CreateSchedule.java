package com.flexpag.paymentscheduler.core.usecase;

import com.flexpag.paymentscheduler.core.domain.Schedule;
import com.flexpag.paymentscheduler.core.gateway.ScheduleGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateSchedule {

    private final ScheduleGateway scheduleGateway;

    public long process(Schedule schedule) {
        return scheduleGateway.createSchedule(schedule).getId();
    }

}
