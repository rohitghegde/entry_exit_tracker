package com.stock.entry_exit_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stock.entry_exit_tracker.entity.EntryTrackerEntity;

@Repository
public interface EntryTrackerRepository extends JpaRepository<EntryTrackerEntity, Long> {
}
