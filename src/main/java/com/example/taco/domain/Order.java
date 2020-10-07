package com.example.taco.domain;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Order {
    private String id;

    private Date placedAt;

    @NotBlank(message = "Name is required")
    private String deliveryName;

    @NotBlank(message = "Street is required")
    private String deliveryStreet;

    @NotBlank(message = "City is required")
    private String deliveryCity;

    @NotBlank(message = "State is required")
    private String deliveryState;

    @NotBlank(message = "Zip code is required")
    private String deliveryZip;

    @Digits(integer = 16, fraction = 0, message = "Invalid CreditCardNumber")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message = "Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    private List<Taco> tacos = new ArrayList<>();

    public Order() {
    }

    public void addDesign(Taco design) {
        this.tacos.add(design);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPlacedAt() {
        return this.placedAt;
    }

    public void setPlacedAt(Date placedAt) {
        this.placedAt = placedAt;
    }

    public @NotBlank(message = "Name is required") String getDeliveryName() {
        return this.deliveryName;
    }

    public void setDeliveryName(@NotBlank(message = "Name is required") String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public @NotBlank(message = "Street is required") String getDeliveryStreet() {
        return this.deliveryStreet;
    }

    public void setDeliveryStreet(@NotBlank(message = "Street is required") String deliveryStreet) {
        this.deliveryStreet = deliveryStreet;
    }

    public @NotBlank(message = "City is required") String getDeliveryCity() {
        return this.deliveryCity;
    }

    public void setDeliveryCity(@NotBlank(message = "City is required") String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public @NotBlank(message = "State is required") String getDeliveryState() {
        return this.deliveryState;
    }

    public void setDeliveryState(@NotBlank(message = "State is required") String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public @NotBlank(message = "Zip code is required") String getDeliveryZip() {
        return this.deliveryZip;
    }

    public void setDeliveryZip(@NotBlank(message = "Zip code is required") String deliveryZip) {
        this.deliveryZip = deliveryZip;
    }

    public @Digits(integer = 16, fraction = 0, message = "Invalid CreditCardNumber") String getCcNumber() {
        return this.ccNumber;
    }

    public void setCcNumber(@Digits(integer = 16, fraction = 0, message = "Invalid CreditCardNumber") String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message = "Must be formatted MM/YY") String getCcExpiration() {
        return this.ccExpiration;
    }

    public void setCcExpiration(@Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message = "Must be formatted MM/YY") String ccExpiration) {
        this.ccExpiration = ccExpiration;
    }

    public @Digits(integer = 3, fraction = 0, message = "Invalid CVV") String getCcCVV() {
        return this.ccCVV;
    }

    public void setCcCVV(@Digits(integer = 3, fraction = 0, message = "Invalid CVV") String ccCVV) {
        this.ccCVV = ccCVV;
    }

    public List<Taco> getTacos() {
        return this.tacos;
    }

    public void setTacos(List<Taco> tacos) {
        this.tacos = tacos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(placedAt, order.placedAt) &&
                Objects.equals(deliveryName, order.deliveryName) &&
                Objects.equals(deliveryStreet, order.deliveryStreet) &&
                Objects.equals(deliveryCity, order.deliveryCity) &&
                Objects.equals(deliveryState, order.deliveryState) &&
                Objects.equals(deliveryZip, order.deliveryZip) &&
                Objects.equals(ccNumber, order.ccNumber) &&
                Objects.equals(ccExpiration, order.ccExpiration) &&
                Objects.equals(ccCVV, order.ccCVV) &&
                Objects.equals(tacos, order.tacos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, placedAt, deliveryName, deliveryStreet, deliveryCity, deliveryState, deliveryZip,
                ccNumber, ccExpiration, ccCVV, tacos);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", placedAt=" + placedAt +
                ", deliveryName='" + deliveryName + '\'' +
                ", deliveryStreet='" + deliveryStreet + '\'' +
                ", deliveryCity='" + deliveryCity + '\'' +
                ", deliveryState='" + deliveryState + '\'' +
                ", deliveryZip='" + deliveryZip + '\'' +
                ", ccNumber='" + ccNumber + '\'' +
                ", ccExpiration='" + ccExpiration + '\'' +
                ", ccCVV='" + ccCVV + '\'' +
                ", tacos=" + tacos +
                '}';
    }
}