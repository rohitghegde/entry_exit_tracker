package com.stock.entry_exit_tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockTrackerDTO {
    private Long id;
    private String stockSymbol;
    private Double exitPrice;
    private Long userId;
}
