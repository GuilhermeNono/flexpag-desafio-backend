package com.flexpag.paymentscheduler.infra.gateway;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<ScheduleJPA, Long> {
}
