package com.flexpag.paymentscheduler.core.domain;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class Schedule {

    long id;
    String name;
    LocalDateTime scheduling;
    String status;

}
