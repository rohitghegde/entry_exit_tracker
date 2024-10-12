package com.stock.entry_exit_tracker.service;

import java.util.List;

import com.stock.entry_exit_tracker.dto.ExitTrackerDTO;

public interface ExitTrackerService {
    String createExitTracker(ExitTrackerDTO exitTrackerDTO);
    List<ExitTrackerDTO> readExitTrackers();
    ExitTrackerDTO readExitTracker(Long id);
    boolean deleteExitTracker(Long id);
    String updateExitTracker(Long id, ExitTrackerDTO exitTrackerDTO);
}
