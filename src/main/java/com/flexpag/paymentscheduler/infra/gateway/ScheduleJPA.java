package com.flexpag.paymentscheduler.infra.gateway;

import com.flexpag.paymentscheduler.core.domain.Schedule;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedule")
@Getter
@Setter
public class ScheduleJPA implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String status;

    @Column
    private String name;

    @Column
    private LocalDateTime scheduling;

    public Schedule toDomain(){
        return new Schedule(this.getId(), this.getName(), this.getScheduling(), this.getStatus());
    }

}

