package entity;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonAutoDetect
public class TicketsWrapper {
    private List<Ticket> tickets;

    @JsonCreator
    public TicketsWrapper(@JsonProperty("tickets")List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
