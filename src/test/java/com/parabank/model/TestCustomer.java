package com.parabank.model;

import java.util.UUID;

public record TestCustomer(
        String firstName,
        String lastName,
        String street,
        String city,
        String state,
        String zipCode,
        String phone,
        String ssn,
        String username,
        String password) {

    public static TestCustomer unique() {
        String unique = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        return new TestCustomer("Taylor", "Automation", "1 Test Street", "Austin", "TX", "78701",
                "5125550100", unique.substring(0, 9), "qa" + unique, "TestPass123");
    }
}