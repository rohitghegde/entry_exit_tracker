package com.stock.entry_exit_tracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.stock.entry_exit_tracker.dto.EntryTrackerDTO;
import com.stock.entry_exit_tracker.entity.EntryTrackerEntity;
import com.stock.entry_exit_tracker.repository.EntryTrackerRepository;

@Service
public class EntryTrackerServiceImpl implements EntryTrackerService {

    private final EntryTrackerRepository entryTrackerRepository;

    public EntryTrackerServiceImpl(EntryTrackerRepository entryTrackerRepository) {
        this.entryTrackerRepository = entryTrackerRepository;
    }

    @Override
    public String createEntryTracker(EntryTrackerDTO entryTrackerDTO) {
        EntryTrackerEntity entryTrackerEntity = mapToEntity(entryTrackerDTO);
        entryTrackerRepository.save(entryTrackerEntity);
        return "Entry Tracker Saved Successfully";
    }

    @Override
    public List<EntryTrackerDTO> readEntryTrackers() {
        List<EntryTrackerEntity> entryTrackerEntities = entryTrackerRepository.findAll();
        return mapToDTOList(entryTrackerEntities);
    }

    @Override
    public EntryTrackerDTO readEntryTracker(Long id) {
        Optional<EntryTrackerEntity> optionalEntryTracker = entryTrackerRepository.findById(id);
        return optionalEntryTracker.map(this::mapToDTO).orElse(null);
    }

    @Override
    public boolean deleteEntryTracker(Long id) {
        if (entryTrackerRepository.existsById(id)) {
            entryTrackerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public String updateEntryTracker(Long id, EntryTrackerDTO entryTrackerDTO) {
        Optional<EntryTrackerEntity> optionalEntryTracker = entryTrackerRepository.findById(id);
        if (optionalEntryTracker.isPresent()) {
            EntryTrackerEntity existingEntryTracker = optionalEntryTracker.get();

            if (entryTrackerDTO.getStockSymbol() != null) {
                existingEntryTracker.setStockSymbol(entryTrackerDTO.getStockSymbol());
            }
            if (entryTrackerDTO.getEntryPrice() != null) {
                existingEntryTracker.setEntryPrice(entryTrackerDTO.getEntryPrice());
            }
            if (entryTrackerDTO.getUserId() != null) {
                existingEntryTracker.setUserId(entryTrackerDTO.getUserId());
            }

            entryTrackerRepository.save(existingEntryTracker);
            return "Entry Tracker Updated Successfully";
        } else {
            return null;
        }
    }

    // Utility methods for mapping between Entity and DTO
    private EntryTrackerDTO mapToDTO(EntryTrackerEntity entryTrackerEntity) {
        EntryTrackerDTO entryTrackerDTO = new EntryTrackerDTO();
        BeanUtils.copyProperties(entryTrackerEntity, entryTrackerDTO);
        return entryTrackerDTO;
    }

    private EntryTrackerEntity mapToEntity(EntryTrackerDTO entryTrackerDTO) {
        EntryTrackerEntity entryTrackerEntity = new EntryTrackerEntity();
        BeanUtils.copyProperties(entryTrackerDTO, entryTrackerEntity);
        return entryTrackerEntity;
    }

    private List<EntryTrackerDTO> mapToDTOList(List<EntryTrackerEntity> entryTrackerEntities) {
        List<EntryTrackerDTO> entryTrackers = new ArrayList<>();
        for (EntryTrackerEntity entryTrackerEntity : entryTrackerEntities) {
            entryTrackers.add(mapToDTO(entryTrackerEntity));
        }
        return entryTrackers;
    }
}
