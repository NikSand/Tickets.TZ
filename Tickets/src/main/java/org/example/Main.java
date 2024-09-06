package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.TicketsList;
import service.TicketsService;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        
        final java.io.FileWriter writer;
        final String jsonFilePath = "tickets.json";
        final String resultFilePath = "result.txt";

        TicketsService service = new TicketsService();
        TicketsList ticketList = new TicketsList();

        ObjectMapper objectMapper = new ObjectMapper();

        // Чтение JSON-файла tickets.json.
        try {
            ticketList = objectMapper.readValue(new File(jsonFilePath), TicketsList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Запись результатов программы в текстовый файл result.txt.
        try {
            writer = new java.io.FileWriter(resultFilePath, false);

            for (String tickets : service.countMinimumFlightTime(ticketList)) {
                writer.append(tickets);
            }

            writer.append(service.differenceBetweenAVGPriceAndMedian(ticketList));

            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
