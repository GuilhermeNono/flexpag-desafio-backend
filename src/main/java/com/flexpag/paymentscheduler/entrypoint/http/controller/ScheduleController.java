package com.flexpag.paymentscheduler.entrypoint.http.controller;

import com.flexpag.paymentscheduler.core.domain.Schedule;
import com.flexpag.paymentscheduler.core.usecase.CreateSchedule;
import com.flexpag.paymentscheduler.core.usecase.FindAllSchedules;
import com.flexpag.paymentscheduler.core.usecase.FindScheduleById;
import com.flexpag.paymentscheduler.core.usecase.UpdateScheduling;
import com.flexpag.paymentscheduler.core.usecase.UpdateStatus;
import com.flexpag.paymentscheduler.entrypoint.http.response.ScheduleResponse;
import com.flexpag.paymentscheduler.infra.gateway.ScheduleJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/schedule")
public class ScheduleController {

    private final FindAllSchedules findAllSchedules;
    private final CreateSchedule createSchedule;
    private final UpdateStatus updateStatus;
    private final FindScheduleById findScheduleById;
    private final UpdateScheduling updateScheduling;

    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> getAll() {

        List<ScheduleResponse> scheduleList = new ArrayList<>();
        List<Schedule> schedules = findAllSchedules.process();

        for (Schedule schedule : schedules) {
            ScheduleResponse newSchedule = new ScheduleResponse();

            newSchedule.setScheduling(schedule.getScheduling());
            newSchedule.setName(schedule.getName());
            newSchedule.setStatus(schedule.getStatus());
            newSchedule.setId(schedule.getId());


            scheduleList.add(newSchedule);
        }


        return ResponseEntity.ok(scheduleList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ScheduleResponse> getById(@PathVariable(value = "id") long id){
        ScheduleJPA scheduleJPA = findScheduleById.process(id);

        ScheduleResponse scheduleResponse = new ScheduleResponse();

        scheduleResponse.setScheduling(scheduleJPA.getScheduling());
        scheduleResponse.setName(scheduleJPA.getName());
        scheduleResponse.setStatus(scheduleJPA.getStatus());
        scheduleResponse.setId(scheduleJPA.getId());

        return new ResponseEntity<>(scheduleResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody final Schedule schedule){

        if(schedule.getScheduling() == null || schedule.getName() == null){
            return ResponseEntity.badRequest().build();
        }

        final long id = createSchedule.process(schedule);

        final ScheduleResponse scheduleResponse = new ScheduleResponse();

        scheduleResponse.setId(id);

        return new ResponseEntity<>(scheduleResponse.getId(), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/update")
    public ResponseEntity<ScheduleResponse> updateScheduling(@RequestBody Schedule schedule) {

        ScheduleJPA scheduleJPA = findScheduleById.process(schedule.getId());

        if(scheduleJPA.getStatus().equals("paid")) {
            return ResponseEntity.badRequest().build();
        }

        Schedule schedule1 = updateScheduling.process(scheduleJPA.toDomain());
        ScheduleResponse scheduleResponse = new ScheduleResponse();

        scheduleResponse.setScheduling(schedule.getScheduling());
        scheduleResponse.setName(schedule.getName());
        scheduleResponse.setStatus(schedule.getStatus());
        scheduleResponse.setId(schedule.getId());

        return new ResponseEntity<>(scheduleResponse, HttpStatus.OK);

    }

    @PatchMapping(value = "/{id}/paid")
    public ResponseEntity<ScheduleResponse> updateStatus(@PathVariable(value = "id") long id){
        ScheduleJPA scheduleJpa = findScheduleById.process(id);

        Schedule schedule = updateStatus.process(scheduleJpa.toDomain());
        ScheduleResponse scheduleResponse = new ScheduleResponse();

        scheduleResponse.setScheduling(schedule.getScheduling());
        scheduleResponse.setName(schedule.getName());
        scheduleResponse.setStatus(schedule.getStatus());
        scheduleResponse.setId(schedule.getId());

        return new ResponseEntity<>(scheduleResponse, HttpStatus.OK);
    }

}
