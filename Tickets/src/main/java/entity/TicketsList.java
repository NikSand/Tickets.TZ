package entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

/**
 * Список, содержащий все билеты на самолет.
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class TicketsList {

    public TicketsList(List<TicketEntity> tickets) {
        this.tickets = tickets;
    }

    @JsonProperty("tickets")
    private List<TicketEntity> tickets;

    public void setTickets(List<TicketEntity> tickets) {
        this.tickets = tickets;
    }
}
