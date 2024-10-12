package com.stock.entry_exit_tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntryTrackerDTO {
    private Long id;
    private String stockSymbol;
    private Double entryPrice;
    private Long userId;
}
