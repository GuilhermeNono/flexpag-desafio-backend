package com.flexpag.paymentscheduler.core.usecase;

import com.flexpag.paymentscheduler.core.gateway.ScheduleGateway;
import com.flexpag.paymentscheduler.infra.gateway.ScheduleJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindScheduleById {

    private final ScheduleGateway scheduleGateway;

    public ScheduleJPA process(long id) {
        return scheduleGateway.findById(id);
    }

}
