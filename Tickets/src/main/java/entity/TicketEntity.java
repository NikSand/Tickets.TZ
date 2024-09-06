package entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Билет на самолет.
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class TicketEntity {

    private String origin;
    private String origin_name;
    private String destination;
    private String destination_name;
    private String departure_date;
    private String departure_time;
    private String arrival_date;
    private String arrival_time;
    private String carrier;
    private int stops;
    private int price;

    public TicketEntity(
            String origin, String origin_name, String destination,
            String destination_name, String departure_date,
            String departure_time, String arrival_date,
            String arrival_time, String carrier, int stops, int price) {
        this.origin = origin;
        this.origin_name = origin_name;
        this.destination = destination;
        this.destination_name = destination_name;
        this.departure_date = departure_date;
        this.departure_time = departure_time;
        this.arrival_date = arrival_date;
        this.arrival_time = arrival_time;
        this.carrier = carrier;
        this.stops = stops;
        this.price = price;
    }


    @Override
    public String toString() {
        return "TicketsEntity{" +
                "origin='" + origin + '\'' +
                ", origin_name='" + origin_name + '\'' +
                ", destination='" + destination + '\'' +
                ", destination_name='" + destination_name + '\'' +
                ", departure_date='" + departure_date + '\'' +
                ", departure_time='" + departure_time + '\'' +
                ", arrival_date='" + arrival_date + '\'' +
                ", arrival_time='" + arrival_time + '\'' +
                ", carrier='" + carrier + '\'' +
                ", stops=" + stops +
                ", price=" + price +
                '}';
    }
}
