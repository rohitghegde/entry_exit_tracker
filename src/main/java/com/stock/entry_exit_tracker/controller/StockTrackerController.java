package com.stock.entry_exit_tracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.entry_exit_tracker.dto.StockTrackerDTO;
import com.stock.entry_exit_tracker.service.StockTrackerService;

@RestController
@RequestMapping("/api/trackers")
public class StockTrackerController {

    private final StockTrackerService stockTrackerService;

    public StockTrackerController(StockTrackerService stockTrackerService) {
        this.stockTrackerService = stockTrackerService;
    }

    @PostMapping
    public String createStockTracker(@RequestBody StockTrackerDTO stockTrackerDTO) {
        return stockTrackerService.createStockTracker(stockTrackerDTO);
    }

    @GetMapping
    public List<StockTrackerDTO> getStockTrackers() {
        return stockTrackerService.readStockTrackers();
    }

    @GetMapping("/{id}")
    public StockTrackerDTO getStockTracker(@PathVariable Long id) {
        return stockTrackerService.readStockTracker(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteStockTracker(@PathVariable Long id) {
        return stockTrackerService.deleteStockTracker(id);
    }

    @PutMapping("/{id}")
    public String updateStockTracker(@PathVariable Long id, @RequestBody StockTrackerDTO stockTrackerDTO) {
    String result = stockTrackerService.updateStockTracker(id, stockTrackerDTO);
    if (result == null) {
        return "Stock Tracker with ID " + id + " not found.";
    }
    return result;
}

}
