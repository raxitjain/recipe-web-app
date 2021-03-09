package com.ernstyoung.recipewebapp.services;

import com.ernstyoung.recipewebapp.models.UnitOfMeasure;

import java.util.Set;

public interface UomService {
    Set<UnitOfMeasure> listAllUoms();
}
