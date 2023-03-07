package com.flexpag.paymentscheduler.core.usecase;

import com.flexpag.paymentscheduler.core.domain.Schedule;
import com.flexpag.paymentscheduler.core.gateway.ScheduleGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateScheduling {

    private final ScheduleGateway scheduleGateway;

    public Schedule process(Schedule schedule) {
        return scheduleGateway.updateScheduling(schedule).toDomain();
    }

}