package com.rano.rano.repository;

import com.rano.rano.entity.ButtonClick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ButtonClickRepository extends JpaRepository<ButtonClick, Long> {

    @Query("SELECT new map(b.label as label, b.clickDate as date, COUNT(b) as count) " +
            "FROM ButtonClick b GROUP BY b.label, b.clickDate ORDER BY b.clickDate DESC")
    List<Map<String, Object>> countClicksByDayAndLabel();

    @Query("SELECT new map(b.label as label, COUNT(b) as total) " +
            "FROM ButtonClick b GROUP BY b.label ORDER BY total DESC")
    List<Map<String, Object>> countTotalClicksByLabel();

}
