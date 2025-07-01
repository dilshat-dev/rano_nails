package com.rano.rano.repository;

import com.rano.rano.entity.VisitLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface VisitLogRepository extends JpaRepository<VisitLog, Long> {
    boolean existsByIpAddressAndVisitDate(String ipAddress, LocalDate visitDate);

    @Query("SELECT v.visitDate AS date, COUNT(v) AS count FROM VisitLog v GROUP BY v.visitDate ORDER BY v.visitDate DESC")
    List<Map<String, Object>> countVisitsByDay();
}
