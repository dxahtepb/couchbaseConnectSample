package com.example.taco;

import com.example.taco.controller.OrderController;
import com.example.taco.data.IngredientRepository;
import com.example.taco.data.OrderRepository;
import com.example.taco.data.TacoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IngredientRepository ingredientRepository;

    @MockBean
    private TacoRepository designRepository;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void testShowOrderForm() throws Exception {
        mockMvc.perform(get("/orders/current"))
                .andExpect(status().isOk())
                .andExpect(view().name("orderForm"));
    }

    @Test
    public void testProcessOrderForm() throws Exception {
        mockMvc.perform(post("/orders")
                    .content(getValidFormContent())
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().stringValues("Location", "/"));
    }

    private static String getValidFormContent() {
        return "deliveryName=name"
                + "&deliveryStreet=asdqwet"
                + "&deliveryCity=asdqwe"
                + "&deliveryState=asdqwe"
                + "&deliveryZip=asdqwe"
                + "&ccNumber=4556297186634749"
                + "&ccExpiration=01/20"
                + "&ccCVV=123";
    }
}
