package com.example.logisight.orders.model;

/**
 * Enum для статуса заказа
 * <ul>
 *     <li>CREATED - заказ создан</li>
 *     <li>IN_PROGRESS - заказ оплачен и передан на доставку</li>
 *     <li>DELIVERED - заказ доставлен</li>
 *     <li>RETURNED - заказ возвращён в место отправления</li>
 *     <li>CANCELLED - заказ отменён</li>
 * </ul>
 * <p>
 *
 **/
public enum OrderStatus {
    CREATED,
    IN_PROGRESS,
    DELIVERED,
    RETURNED,
    CANCELLED
}
