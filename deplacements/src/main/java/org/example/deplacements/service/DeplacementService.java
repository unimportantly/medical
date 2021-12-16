package org.example.deplacements.service;

import org.example.deplacements.dto.DeplacementDTO;
import org.example.deplacements.repository.DeplacementRepository;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class DeplacementService {

    private DeplacementRepository deplacementRepository;
    private ModelMapper modelMapper;

    public List<DeplacementDTO> getAllDeplacements() {
        List<DeplacementDTO> deplacementDTOList = new ArrayList<>();
        deplacementRepository.findAll().forEach(deplacement ->
                deplacementDTOList.add(this.modelMapper.map(deplacement, DeplacementDTO.class)));
        return deplacementDTOList;
    }
}
