package com.example.logisight.users.dto;

import java.util.Date;

public record AppError(int status,
                       String message,
                       Date timestamp) {
}
