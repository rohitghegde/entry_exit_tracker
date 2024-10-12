package com.stock.entry_exit_tracker.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.entry_exit_tracker.dto.EntryTrackerDTO;
import com.stock.entry_exit_tracker.dto.ExitTrackerDTO;
import com.stock.entry_exit_tracker.service.EntryTrackerService;
import com.stock.entry_exit_tracker.service.ExitTrackerService;

@RestController
@RequestMapping("/api/trackers")
public class StockTrackerController {

    private final ExitTrackerService exitTrackerService;
    private final EntryTrackerService entryTrackerService;

    public StockTrackerController(ExitTrackerService exitTrackerService, EntryTrackerService entryTrackerService) {
        this.exitTrackerService = exitTrackerService;
        this.entryTrackerService = entryTrackerService;
    }

    // Exit Tracker CRUD operations
    @PostMapping("/exit")
    public ResponseEntity<String> createExitTracker(@RequestBody ExitTrackerDTO exitTrackerDTO) {
        return ResponseEntity.ok(exitTrackerService.createExitTracker(exitTrackerDTO));
    }

    @GetMapping("/exit")
    public ResponseEntity<List<ExitTrackerDTO>> getExitTrackers() {
        return ResponseEntity.ok(exitTrackerService.readExitTrackers());
    }

    @GetMapping("/exit/{id}")
    public ResponseEntity<ExitTrackerDTO> getExitTracker(@PathVariable Long id) {
        ExitTrackerDTO exitTracker = exitTrackerService.readExitTracker(id);
        return exitTracker != null ? ResponseEntity.ok(exitTracker) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/exit/{id}")
    public ResponseEntity<String> deleteExitTracker(@PathVariable Long id) {
        if (exitTrackerService.deleteExitTracker(id)) {
            return ResponseEntity.ok("Exit Tracker Deleted Successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/exit/{id}")
    public ResponseEntity<String> updateExitTracker(@PathVariable Long id, @RequestBody ExitTrackerDTO exitTrackerDTO) {
        String result = exitTrackerService.updateExitTracker(id, exitTrackerDTO);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    // Entry Tracker CRUD operations
    @PostMapping("/entry")
    public ResponseEntity<String> createEntryTracker(@RequestBody EntryTrackerDTO entryTrackerDTO) {
        return ResponseEntity.ok(entryTrackerService.createEntryTracker(entryTrackerDTO));
    }

    @GetMapping("/entry")
    public ResponseEntity<List<EntryTrackerDTO>> getEntryTrackers() {
        return ResponseEntity.ok(entryTrackerService.readEntryTrackers());
    }

    @GetMapping("/entry/{id}")
    public ResponseEntity<EntryTrackerDTO> getEntryTracker(@PathVariable Long id) {
        EntryTrackerDTO entryTracker = entryTrackerService.readEntryTracker(id);
        return entryTracker != null ? ResponseEntity.ok(entryTracker) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/entry/{id}")
    public ResponseEntity<String> deleteEntryTracker(@PathVariable Long id) {
        if (entryTrackerService.deleteEntryTracker(id)) {
            return ResponseEntity.ok("Entry Tracker Deleted Successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/entry/{id}")
    public ResponseEntity<String> updateEntryTracker(@PathVariable Long id, @RequestBody EntryTrackerDTO entryTrackerDTO) {
        String result = entryTrackerService.updateEntryTracker(id, entryTrackerDTO);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }
}
