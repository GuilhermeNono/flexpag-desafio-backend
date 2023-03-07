package com.flexpag.paymentscheduler.entrypoint.http.response;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleResponse implements Serializable {

    private long id;
    private String status;
    private LocalDateTime scheduling;
    private String name;

}
