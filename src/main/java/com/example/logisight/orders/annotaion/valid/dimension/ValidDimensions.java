package com.example.logisight.orders.annotaion.valid.dimension;

import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Constraint(validatedBy = ValidDimensionsValidator.class)
public @interface ValidDimensions {
    String message() default "Некорректный формат размеров (ожидается формат: XxXxX)";
}
