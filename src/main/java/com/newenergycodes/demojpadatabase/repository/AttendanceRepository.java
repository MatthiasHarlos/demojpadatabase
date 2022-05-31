package com.newenergycodes.demojpadatabase.repository;

import com.newenergycodes.demojpadatabase.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
