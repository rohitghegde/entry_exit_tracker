package com.stock.entry_exit_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stock.entry_exit_tracker.entity.ExitTrackerEntity;

@Repository
public interface ExitTrackerRepository extends JpaRepository<ExitTrackerEntity, Long> {
}
