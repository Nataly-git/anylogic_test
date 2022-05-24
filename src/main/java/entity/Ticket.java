package entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import deserialisers.LocalDateWithStringsDeserializer;
import deserialisers.LocalTimeWithStringsDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@JsonAutoDetect
@NoArgsConstructor
public class Ticket {
    public String origin;
    public String origin_name;
    public String destination;
    public String destination_name;

    @JsonDeserialize(using = LocalDateWithStringsDeserializer.class)
    @JsonFormat(pattern="dd.MM.yy")
    public LocalDate departure_date;

    @JsonFormat(pattern = "H:mm")
    @JsonDeserialize(using = LocalTimeWithStringsDeserializer.class)
    public LocalTime departure_time;

    @JsonFormat(pattern="dd.MM.yy")
    @JsonDeserialize(using = LocalDateWithStringsDeserializer.class)
    public LocalDate arrival_date;

    @JsonFormat(pattern = "H:mm")
    @JsonDeserialize(using = LocalTimeWithStringsDeserializer.class)
    public LocalTime arrival_time;
    public String carrier;
    public Integer stops;
    public Integer price;
}
