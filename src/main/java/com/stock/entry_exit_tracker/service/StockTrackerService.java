package com.stock.entry_exit_tracker.service;

import java.util.List;

import com.stock.entry_exit_tracker.dto.StockTrackerDTO;

public interface StockTrackerService {
    String createStockTracker(StockTrackerDTO stockTrackerDTO);
    List<StockTrackerDTO> readStockTrackers();
    StockTrackerDTO readStockTracker(Long id);
    boolean deleteStockTracker(Long id);
    String updateStockTracker(Long id, StockTrackerDTO stockTrackerDTO);
}
