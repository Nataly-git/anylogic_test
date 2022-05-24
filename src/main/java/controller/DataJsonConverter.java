package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.TicketsWrapper;
import lombok.Data;


import java.io.File;
import java.io.IOException;


@Data
public class DataJsonConverter {
    private String path;

    public DataJsonConverter(String path) {
        this.path = path;
    }

    public DataJsonConverter() {
    }

    public TicketsWrapper convertData() {

        ObjectMapper mapper = new ObjectMapper();
        TicketsWrapper tickets = null;
        try {
            tickets = mapper.readValue(new File(path), TicketsWrapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tickets;
    }
}
