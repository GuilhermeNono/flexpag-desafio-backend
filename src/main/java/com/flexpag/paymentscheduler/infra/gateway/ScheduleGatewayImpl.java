package com.flexpag.paymentscheduler.infra.gateway;

import com.flexpag.paymentscheduler.core.domain.Schedule;
import com.flexpag.paymentscheduler.core.gateway.ScheduleGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduleGatewayImpl implements ScheduleGateway {

    private final ScheduleRepository scheduleRepository;

    @Override
    @Transactional
    public List<ScheduleJPA> findAll() {
        return scheduleRepository.findAll();
    }

    @Override
    @Transactional
    public ScheduleJPA findById(long id) {
        return scheduleRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public ScheduleJPA createSchedule(Schedule schedule) {

        final ScheduleJPA scheduleJPA = new ScheduleJPA();

        scheduleJPA.setName(schedule.getName());
        scheduleJPA.setStatus("pending");
        scheduleJPA.setScheduling(schedule.getScheduling());
        scheduleJPA.setId(schedule.getId());

        return scheduleRepository.save(scheduleJPA);
    }

    @Override
    @Transactional
    public ScheduleJPA updateStatus(Schedule schedule) {

        final ScheduleJPA scheduleJPA = new ScheduleJPA();

        scheduleJPA.setName(schedule.getName());
        scheduleJPA.setStatus("paid");
        scheduleJPA.setScheduling(schedule.getScheduling());
        scheduleJPA.setId(schedule.getId());

        return scheduleRepository.save(scheduleJPA);
    }

    @Override
    @Transactional
    public ScheduleJPA updateScheduling(Schedule schedule) {

        final ScheduleJPA scheduleJPA = new ScheduleJPA();

        scheduleJPA.setName(schedule.getName());
        scheduleJPA.setStatus(schedule.getStatus());
        scheduleJPA.setScheduling(schedule.getScheduling());
        scheduleJPA.setId(schedule.getId());

        return scheduleRepository.save(scheduleJPA);
    }
}
