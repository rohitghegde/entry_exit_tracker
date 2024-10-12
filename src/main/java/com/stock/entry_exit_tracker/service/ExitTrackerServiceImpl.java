package com.stock.entry_exit_tracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.stock.entry_exit_tracker.dto.ExitTrackerDTO;
import com.stock.entry_exit_tracker.entity.ExitTrackerEntity;
import com.stock.entry_exit_tracker.repository.ExitTrackerRepository;

@Service
public class ExitTrackerServiceImpl implements ExitTrackerService {

    private final ExitTrackerRepository exitTrackerRepository;

    public ExitTrackerServiceImpl(ExitTrackerRepository exitTrackerRepository) {
        this.exitTrackerRepository = exitTrackerRepository;
    }

    @Override
    public String createExitTracker(ExitTrackerDTO exitTrackerDTO) {
        ExitTrackerEntity exitTrackerEntity = new ExitTrackerEntity();
        BeanUtils.copyProperties(exitTrackerDTO, exitTrackerEntity);
        exitTrackerRepository.save(exitTrackerEntity);
        return "Exit Tracker Saved Successfully";
    }

    @Override
    public List<ExitTrackerDTO> readExitTrackers() {
        List<ExitTrackerEntity> exitTrackerEntities = exitTrackerRepository.findAll();
        List<ExitTrackerDTO> exitTrackers = new ArrayList<>();

        for (ExitTrackerEntity exitTrackerEntity : exitTrackerEntities) {
            ExitTrackerDTO exitTrackerDTO = new ExitTrackerDTO();
            BeanUtils.copyProperties(exitTrackerEntity, exitTrackerDTO);
            exitTrackers.add(exitTrackerDTO);
        }
        return exitTrackers;
    }

    @Override
    public ExitTrackerDTO readExitTracker(Long id) {
        Optional<ExitTrackerEntity> optionalExitTracker = exitTrackerRepository.findById(id);
        if (optionalExitTracker.isPresent()) {
            ExitTrackerDTO exitTrackerDTO = new ExitTrackerDTO();
            BeanUtils.copyProperties(optionalExitTracker.get(), exitTrackerDTO);
            return exitTrackerDTO;
        }
        return null;
    }

    @Override
    public boolean deleteExitTracker(Long id) {
        if (exitTrackerRepository.existsById(id)) {
            exitTrackerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public String updateExitTracker(Long id, ExitTrackerDTO exitTrackerDTO) {
        Optional<ExitTrackerEntity> optionalExitTracker = exitTrackerRepository.findById(id);
        if (optionalExitTracker.isPresent()) {
            ExitTrackerEntity existingExitTracker = optionalExitTracker.get();

            if (exitTrackerDTO.getStockSymbol() != null) {
                existingExitTracker.setStockSymbol(exitTrackerDTO.getStockSymbol());
            }
            if (exitTrackerDTO.getExitPrice() != null) {
                existingExitTracker.setExitPrice(exitTrackerDTO.getExitPrice());
            }
            if (exitTrackerDTO.getUserId() != null) {
                existingExitTracker.setUserId(exitTrackerDTO.getUserId());
            }

            exitTrackerRepository.save(existingExitTracker);
            return "Exit Tracker Updated Successfully";
        } else {
            return null;
        }
    }
}
