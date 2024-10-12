package com.stock.entry_exit_tracker.service;

import java.util.List;

import com.stock.entry_exit_tracker.dto.EntryTrackerDTO;

public interface EntryTrackerService {
    String createEntryTracker(EntryTrackerDTO entryTrackerDTO);
    List<EntryTrackerDTO> readEntryTrackers();
    EntryTrackerDTO readEntryTracker(Long id);
    boolean deleteEntryTracker(Long id);
    String updateEntryTracker(Long id, EntryTrackerDTO entryTrackerDTO);
}
