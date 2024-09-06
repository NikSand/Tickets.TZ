package service;

import entity.TicketsList;
import entity.TicketEntity;
import utils.TimeParser;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Получение минимального времени полета между городами
 * Владивосток и Тель-Авив для каждого
 * авиаперевозчика и разницы между средней ценой и медианой для
 * полета между городами Владивосток и Тель-Авив.
 */
public class TicketsService {

    private final TimeParser timeParser = new TimeParser();
    private List<String> timeFlightList = new ArrayList<>();
    private TicketsList sortedTicketsList = new TicketsList();

    private final String originName = "Владивосток";
    private final String destinationName = "Тель-Авив";
    private final int hoursCoefficient = 24;
    private final int minutesCoefficient = 60;

    /**
     * Находит минимальное время полета между городами
     * Владивосток и Тель-Авив для каждого
     * авиаперевозчика.
     * @param ticketsList Список билетов на самолет.
     * @return Строку, в которой записано время полета
     * для каждого перевозчика и минимальное время перелета.
     */
    public List<String> countMinimumFlightTime (TicketsList ticketsList) {
        TicketEntity firstTicket = new TicketEntity();

        for (int i = 0; i < ticketsList.getTickets().size(); i++) {
            if (ticketsList.getTickets().get(i).getOrigin_name().equals(originName)
                    & ticketsList.getTickets().get(i).getDestination_name().equals(destinationName)) {
                firstTicket = ticketsList.getTickets().get(i);

                break;
            }
        }

        LocalTime firstDepartureTime = timeParser.parseTime(firstTicket.getDeparture_time());
        LocalTime firstArrivalTime = timeParser.parseTime(firstTicket.getArrival_time());

        // Минимальное время перелета.
        Duration munimumFlightDuration = Duration.between(firstDepartureTime, firstArrivalTime);

        for (int i = 0; i < ticketsList.getTickets().size(); i++) {

            TicketEntity currentTicket = ticketsList.getTickets().get(i);

            if (currentTicket.getOrigin_name().equals(originName)
                    & currentTicket.getDestination_name().equals(destinationName)) {
                LocalTime departureTime = timeParser.parseTime(currentTicket.getDeparture_time());
                LocalTime arrivalTime = timeParser.parseTime(currentTicket.getArrival_time());

                // Время перелета для каждого перевозчика.
                Duration duration = Duration.between(departureTime, arrivalTime);

                if (munimumFlightDuration.toHours() > duration.toHours()) {
                    munimumFlightDuration = duration;
                } else if (munimumFlightDuration.toMinutes() > duration.toMinutes()) {
                    munimumFlightDuration = duration;
                }

                String timeCountResult = "Время полета у перевозчика " + (i + 1)
                        + " = " + duration.toHours() % hoursCoefficient
                        + " часов и " + duration.toMinutes() % minutesCoefficient
                        + " минут.\n";

                System.out.printf(timeCountResult);

                timeFlightList.add(timeCountResult);
            }
        }
        System.out.println(" ");
        System.out.println("Минимальное время полета Москва - Тель-Авив = "
                + munimumFlightDuration.toHours() % hoursCoefficient + " часов "
                + munimumFlightDuration.toMinutes() % minutesCoefficient + " минут.");

        timeFlightList.add("Минимальное время полета Москва - Тель-Авив = "
                + munimumFlightDuration.toHours() % hoursCoefficient + " часов "
                + munimumFlightDuration.toMinutes() % minutesCoefficient + " минут.");

        return timeFlightList;
    }

    /**
     * Находит разницу между средней ценой и медианой для
     * полета между городами Владивосток и Тель-Авив.
     * @param ticketsList Список билетов на самолет.
     * @return Строку, в которой записана разница между
     * средней ценой и медианой для
     * полета между городами Владивосток и Тель-Авив.
     */
    public String differenceBetweenAVGPriceAndMedian (TicketsList ticketsList) {

        double avgFlightPrice = 0;
        double medianFlightPrice = 0;
        int flightCount = 0;
        int sortedTicketsListSize = 0;

        for (int i = 0; i < ticketsList.getTickets().size(); i++) {

            TicketEntity currentTicket = ticketsList.getTickets().get(i);

            if (currentTicket.getOrigin_name().equals(originName)
                    & currentTicket.getDestination_name().equals(destinationName)) {
                avgFlightPrice += ticketsList.getTickets().get(i).getPrice();
                flightCount++;
            }
        }

        // Рассчет средней цены билета.
        avgFlightPrice /= flightCount;

        sortedTicketsList = sortTickedList(ticketsList);

        sortedTicketsListSize = sortedTicketsList.getTickets().size();

        // Рассчет медианы, в зависимости от четности/не четности количества билетов.
        if (sortedTicketsListSize % 2 == 0) {
            medianFlightPrice = sortedTicketsList.getTickets().get(sortedTicketsListSize / 2).getPrice();

        } else {
            medianFlightPrice = ((double) (sortedTicketsList.getTickets().get((sortedTicketsListSize + 1) / 2).getPrice()
                    + sortedTicketsList.getTickets().get((sortedTicketsListSize - 1) / 2).getPrice()) / 2);
        }

        String result = "Разница между средней ценой: " + avgFlightPrice + " и значением медианы: "
                + medianFlightPrice + " = " + (avgFlightPrice - medianFlightPrice);

        System.out.println("Средняя цена = " + avgFlightPrice);
        System.out.println("Значение медианы = " + medianFlightPrice);
        System.out.println(result);

        return result;
    }

    /**
     * Сортирует список билетов по стоимости перелета.
     * @param ticketsList Список билетов на самолет.
     * @return Отсортированный список билетов на самолет.
     */
    public TicketsList sortTickedList(TicketsList ticketsList) {

        List<TicketEntity> sortedList = new ArrayList<>();

        for (int i = 0; i < ticketsList.getTickets().size(); i++) {
            if (ticketsList.getTickets().get(i).getOrigin_name().equals(originName)
                    & ticketsList.getTickets().get(i).getDestination_name().equals(destinationName)) {
                sortedList.add(ticketsList.getTickets().get(i));
            }
        }

        // Компаратор.
        sortedList.sort((o1, o2) -> {
            if (o1.getPrice() > o2.getPrice()) {
                return 1;
            } else if (o1.getPrice() < o2.getPrice()) {
                return -1;
            } else {
                return 0;
            }
        });

        ticketsList.setTickets(sortedList);

        return ticketsList;
    }
}
