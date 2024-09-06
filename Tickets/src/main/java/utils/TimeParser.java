package utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Вспомогательный класс, обеспечивающий конвертацию времени.
 */
public class TimeParser {
    /**
     * Конвертирует полученную строку в локальное время в 12 часовой мли 24 часовой формат.
     * @param time Строка, в которой записано время, и которую нужно конвертировать.
     * @return Возвращает локальное время в 12-часовом или 24-часовом форматах.
     */
    public LocalTime parseTime(String time) {
        DateTimeFormatter formatter24 = DateTimeFormatter.ofPattern("H:mm");
        DateTimeFormatter formatter12 = DateTimeFormatter.ofPattern("h:mm");

        try {
            // Сначала пробуем парсить 24-часовой формат.
            return LocalTime.parse(time, formatter24);
        } catch (DateTimeParseException e) {
            // Если не удалось, пробуем 12-часовой формат.
            try {
                return LocalTime.parse(time, formatter12);
            } catch (DateTimeParseException ex) {
                System.out.println("Некорректный формат времени: " + time);
                return null;
            }
        }
    }
}
