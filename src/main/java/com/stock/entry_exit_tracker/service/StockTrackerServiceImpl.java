package com.stock.entry_exit_tracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.stock.entry_exit_tracker.dto.StockTrackerDTO;
import com.stock.entry_exit_tracker.entity.StockTrackerEntity;
import com.stock.entry_exit_tracker.repository.StockTrackerRepository;

@Service
public class StockTrackerServiceImpl implements StockTrackerService {


    private final StockTrackerRepository stockTrackerRepository;
    
    public StockTrackerServiceImpl(StockTrackerRepository stockTrackerRepository) {
        this.stockTrackerRepository = stockTrackerRepository;

    }
    
    //create
    @Override
    public String createStockTracker(StockTrackerDTO stockTrackerDTO) {
        StockTrackerEntity stockTrackerEntity = new StockTrackerEntity();
        BeanUtils.copyProperties(stockTrackerDTO, stockTrackerEntity);
        stockTrackerRepository.save(stockTrackerEntity);
        return "Saved Successfully";
    }

    //read all
    @Override
    public List<StockTrackerDTO> readStockTrackers() {
        List<StockTrackerEntity> stockTrackerEntities = stockTrackerRepository.findAll();
        List<StockTrackerDTO> stockTrackers = new ArrayList<>();

        for (StockTrackerEntity stockTrackerEntity : stockTrackerEntities) {
            StockTrackerDTO stockTrackerDTO = new StockTrackerDTO();
            BeanUtils.copyProperties(stockTrackerEntity, stockTrackerDTO);
            stockTrackers.add(stockTrackerDTO);
        }
        return stockTrackers;
    }

    //read with respect to id
    @Override
    public StockTrackerDTO readStockTracker(Long id) {
        Optional<StockTrackerEntity> optionalStockTracker = stockTrackerRepository.findById(id);
        if (optionalStockTracker.isPresent()) {
            StockTrackerDTO stockTrackerDTO = new StockTrackerDTO();
            BeanUtils.copyProperties(optionalStockTracker.get(), stockTrackerDTO);
            return stockTrackerDTO;
        }
        return null;
    }

    //delete 
    @Override
    public boolean deleteStockTracker(Long id) {
        if (stockTrackerRepository.existsById(id)) {
            stockTrackerRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    //update
    @Override
    public String updateStockTracker(Long id, StockTrackerDTO stockTrackerDTO) {
    Optional<StockTrackerEntity> optionalStockTracker = stockTrackerRepository.findById(id);
    if (optionalStockTracker.isPresent()) {
        StockTrackerEntity existingStockTracker = optionalStockTracker.get();
        
        if (stockTrackerDTO.getStockSymbol() != null) {
            existingStockTracker.setStockSymbol(stockTrackerDTO.getStockSymbol());
        }
        if (stockTrackerDTO.getExitPrice() != null) {
            existingStockTracker.setExitPrice(stockTrackerDTO.getExitPrice());
        }
        if (stockTrackerDTO.getUserId() != null) {
            existingStockTracker.setUserId(stockTrackerDTO.getUserId());
        }

        stockTrackerRepository.save(existingStockTracker);
        return "Updated Successfully";
    } else {
        return null; 
    }
}
}
