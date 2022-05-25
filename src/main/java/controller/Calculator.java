package controller;


import entity.Ticket;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Data
public class Calculator {
    private List<Ticket> ticketList;

    public Calculator(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public long getAverageTime() {
        List<Long> durations = getDuration();
        long sum = 0;
        for (Long dur : durations) {
            sum+= dur;
        }
        return sum / durations.size();
    }

    private List<Long> getDuration() {
        List<Long> durations = new ArrayList<>();
        for(Ticket ticket : ticketList) {
            LocalDateTime departureDateTime = LocalDateTime.of(ticket.getDeparture_date(), ticket.getDeparture_time());
            LocalDateTime arrivalDateTime = LocalDateTime.of(ticket.getArrival_date(), ticket.getArrival_time());
            long duration = Math.abs(ChronoUnit.MINUTES.between(arrivalDateTime, departureDateTime));
            durations.add(duration);
        }
        return durations;
    }

    public long getProcentil(double procentil) {
        List<Long> durations = getDuration();
        Collections.sort(durations);
        int range = (int) Math.ceil((procentil / 100.0) * (durations.size()));
        return durations.get(range);
    }
}
