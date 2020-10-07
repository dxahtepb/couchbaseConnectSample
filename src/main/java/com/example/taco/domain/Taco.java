package com.example.taco.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Taco {
    private String id;

    private Date createdAt;

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @NotNull(message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

    public Taco() {
    }

    public String getId() {
        return this.id;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public @NotNull @Size(min = 5, message = "Name must be at least 5 characters long") String getName() {
        return this.name;
    }

    public @NotNull(message = "You must choose at least 1 ingredient") List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setName(@NotNull @Size(min = 5, message = "Name must be at least 5 characters long") String name) {
        this.name = name;
    }

    public void setIngredients(@NotNull(message = "You must choose at least 1 ingredient") List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Taco taco = (Taco) o;
        return Objects.equals(id, taco.id) &&
                Objects.equals(createdAt, taco.createdAt) &&
                Objects.equals(name, taco.name) &&
                Objects.equals(ingredients, taco.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, name, ingredients);
    }

    @Override
    public String toString() {
        return "Taco{" +
                "id='" + id + '\'' +
                ", createdAt=" + createdAt +
                ", name='" + name + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}
