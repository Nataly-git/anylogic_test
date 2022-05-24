package deserialisers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class LocalTimeWithStringsDeserializer extends LocalTimeDeserializer {
    private static final long serialVersionUID = 1L;

    @Override
    public LocalTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        if (jp.isExpectedStartArrayToken()) {
            return parseArray(jp, ctxt);
        }
        return super.deserialize(jp, ctxt);
    }

    private LocalTime parseArray(JsonParser jp, DeserializationContext ctxt) throws JsonParseException, IOException {
        int hour = getNextValue(jp);
        int minute = getNextValue(jp);
        if (jp.nextToken() != JsonToken.END_ARRAY) {
            throw ctxt.wrongTokenException(jp, JsonToken.END_ARRAY, "after LocalDate ints");
        }
        return LocalTime.of(hour, minute);
    }

    private int getNextValue(JsonParser jp) throws IOException, JsonParseException {
        jp.nextToken();
        return new Integer(jp.getText());
    }
}
