package com.example.taco.data;

import com.example.taco.domain.Order;
import com.example.taco.domain.Taco;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Repository
public class JdbcOrderRepository implements OrderRepository {
    private final NamedParameterJdbcTemplate jdbc;
    private final ObjectMapper objectMapper;

    @Autowired
    public JdbcOrderRepository(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        objectMapper = new ObjectMapper();
    }

    @Override
    public Order save(Order order) {
        order.setPlacedAt(new Date());
        String newOrderId = saveOrderDetails(order);
        order.setId(newOrderId);
        for (Taco taco : order.getTacos()) {
            addTacoToOrder(order, taco);
        }
        return order;
    }

    private String saveOrderDetails(Order order) {
        String key = UUID.randomUUID().toString();
        @SuppressWarnings("unchecked")
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("key", key)
                .addValues(objectMapper.convertValue(order, Map.class));
        jdbc.update(
                "insert into springApp (key, value) values (:key, {" +
                        "'_type': 'order', " +
                        "'delivery': { 'name': :deliveryName, 'street': :deliveryStreet, 'city': :deliveryCity, " +
                                      "'state': :deliveryState, 'zip': :deliveryZip }, " +
                        "'placedAt': :placedAt, " +
                        "'creditCard': { 'number': :ccNumber, 'expiration': :ccExpiration, 'CVV': :ccCVV }, " +
                        "'tacos': []" +
                    "})",
                namedParameters);
        return key;
    }

    private void addTacoToOrder(Order order, Taco taco) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("tacoKey", taco.getId())
                .addValue("orderKey", order.getId());
        jdbc.update("update springApp " +
                        "set tacos=array_append(ifmissingornull(tacos, []), :tacoKey)" +
                        "where meta().id=:orderKey;",
                namedParameters);
    }
}
