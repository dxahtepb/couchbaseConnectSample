package com.example.taco.controller;

import com.example.taco.data.IngredientRepository;
import com.example.taco.data.TacoRepository;
import com.example.taco.domain.Ingredient;
import com.example.taco.domain.Order;
import com.example.taco.domain.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@SessionAttributes("order")
@RequestMapping("/design")
public class DesignTacoController {
    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @GetMapping
    public String showDesignForm(Model model) {
        addIngredientsAttributes(model);
        return "design";
    }

    @ModelAttribute(name = "design")
    public Taco design() {
        return new Taco();
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @PostMapping
    public String processDesignForm(@Validated @ModelAttribute("design") Taco design, Errors errors,
                                    @ModelAttribute Order order,
                                    Model model) {
        if (errors.hasErrors()) {
            addIngredientsAttributes(model);
            return "design";
        }

        log.info("Processing design: " + design);
        Taco saved = tacoRepository.save(design);
        log.info("Saved design: " + saved);
        order.addDesign(design);
        log.info("Current order: " + order);

        return "redirect:/orders/current";
    }

    private void addIngredientsAttributes(Model model) {
        Map<String, List<Ingredient>> ingredients = ingredientRepository.findAll().stream()
                .collect(Collectors.groupingBy(ingredient -> ingredient.getType().toString().toLowerCase()));
        model.addAllAttributes(ingredients);
    }
}