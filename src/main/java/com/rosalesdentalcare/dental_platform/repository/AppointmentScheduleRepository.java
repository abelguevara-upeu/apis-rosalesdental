package com.rosalesdentalcare.dental_platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rosalesdentalcare.dental_platform.entity.AppointmentSchedule;

public interface AppointmentScheduleRepository extends JpaRepository<AppointmentSchedule, Long>{

}
