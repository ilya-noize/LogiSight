package com.example.logisight.exception;

import java.util.Date;

public record AppError(int status,
                       String message,
                       Date timestamp) {
}
