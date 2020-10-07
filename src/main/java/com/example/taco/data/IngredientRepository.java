package com.example.taco.data;

import com.example.taco.domain.Ingredient;

import java.util.Collection;

public interface IngredientRepository {
    Collection<Ingredient> findAll();
    Ingredient findOne(String id);
    Ingredient save(Ingredient ingredient);
}