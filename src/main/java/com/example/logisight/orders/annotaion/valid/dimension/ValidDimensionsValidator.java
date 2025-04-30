package com.example.logisight.orders.annotaion.valid.dimension;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidDimensionsValidator implements ConstraintValidator<ValidDimensions, String> {

    @Override
    public boolean isValid(String dimensions, ConstraintValidatorContext constraintValidatorContext) {
        if (dimensions == null) {
            return true; // или false, если null не допускается
        }

        String[] parts = dimensions.split("x");
        return parts.length == 3 &&
                parts[0].matches("\\d+") &&
                parts[1].matches("\\d+") &&
                parts[2].matches("\\d+");
    }
}
