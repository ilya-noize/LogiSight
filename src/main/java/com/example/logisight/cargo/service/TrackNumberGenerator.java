package com.example.logisight.cargo.service;

import java.util.UUID;

/**
 * Утилитарный класс для генерации Трек-номера
 * <p>
 * Алгоритм работает следующим образом:
 * <p>
 * Генерируем UUID
 * Преобразуем его в строку и удаляем дефисы
 * Берем первые 16 символов для компактности
 * Добавляем префикс “TR” для идентификации трек-номера
 * Рассчитываем контрольную цифру по алгоритму:
 * Преобразуем каждый символ в число (hex система)
 * Суммируем все числа
 * Берем остаток от деления на 16
 * Добавляем контрольную цифру в конец номера
 * Пример вывода: TR1234567890ABCDEF8
 */
public class TrackNumberGenerator {

    private TrackNumberGenerator() {
    }

    private static final String PREFIX = "TR";
    private static final int DIGIT = 16;

    // Метод для генерации трек-номера
    public static String generateTrackNumber() {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        // Удаляем дефисы и берем первые 16 символов
        String baseTrack = uuidString.replace("-", "").substring(0, DIGIT);

        // Добавляем префикс TRK для идентификации
        String trackNumber = PREFIX + baseTrack;

        // Добавляем контрольную цифру
        trackNumber += calculateCheckDigit(baseTrack);

        return trackNumber;
    }

    // Метод расчета контрольной цифры
    private static char calculateCheckDigit(String baseTrack) {
        int sum = 0;
        for (int i = 0; i < baseTrack.length(); i++) {
            int digit = Character.digit(baseTrack.charAt(i), DIGIT);
            sum += digit;
        }
        return Character.forDigit(sum % DIGIT, DIGIT); // Используем hex систему
    }
}