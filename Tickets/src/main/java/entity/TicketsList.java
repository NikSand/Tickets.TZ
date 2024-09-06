package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/**
 * Список, содержащий все билеты на самолет.
 */
public class TicketsList {


    public TicketsList() {

    }

    public TicketsList(List<TicketEntity> tickets) {
        this.tickets = tickets;
    }



    @JsonProperty("tickets")
    private List<TicketEntity> tickets;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketsList that = (TicketsList) o;
        return Objects.equals(tickets, that.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tickets);
    }

    public List<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketEntity> tickets) {
        this.tickets = tickets;
    }
}
