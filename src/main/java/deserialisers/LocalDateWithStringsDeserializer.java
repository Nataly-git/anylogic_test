package deserialisers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class LocalDateWithStringsDeserializer extends LocalDateDeserializer {

    private static final long serialVersionUID = 1L;

    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if (jp.isExpectedStartArrayToken()) {
            return parseArray(jp, ctxt);
        }
        return super.deserialize(jp, ctxt);
    }

    private LocalDate parseArray(JsonParser jp, DeserializationContext ctxt) throws JsonParseException, IOException {
        int day = getNextValue(jp);
        System.out.println(day);
        int month = getNextValue(jp);
        System.out.println(month);
        int year = getNextValue(jp);
        System.out.println(year);
        if (jp.nextToken() != JsonToken.END_ARRAY) {
            throw ctxt.wrongTokenException(jp, JsonToken.END_ARRAY, "after LocalDate ints");
        }
        String date = day + "." + month + "." + year;
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yy"));
    }

    private int getNextValue(JsonParser jp) throws IOException, JsonParseException {
        jp.nextToken();
        return new Integer(jp.getText());
    }
}
