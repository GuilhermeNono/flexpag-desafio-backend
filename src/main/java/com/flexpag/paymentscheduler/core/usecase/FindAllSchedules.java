package com.flexpag.paymentscheduler.core.usecase;

import com.flexpag.paymentscheduler.core.domain.Schedule;
import com.flexpag.paymentscheduler.core.gateway.ScheduleGateway;
import com.flexpag.paymentscheduler.infra.gateway.ScheduleJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindAllSchedules {

    private final ScheduleGateway scheduleGateway;

    public List<Schedule> process() {
        return scheduleGateway.findAll()
                .stream().map(ScheduleJPA::toDomain).collect(Collectors.toList());
    }

}
