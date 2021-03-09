package com.ernstyoung.recipewebapp.services;

import com.ernstyoung.recipewebapp.models.UnitOfMeasure;
import com.ernstyoung.recipewebapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UomServiceImpl implements UomService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public UomServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public Set<UnitOfMeasure> listAllUoms() {
        Set<UnitOfMeasure> uomList = new HashSet<>();
        unitOfMeasureRepository.findAll().forEach(uomList::add);
        return uomList;
    }
}
