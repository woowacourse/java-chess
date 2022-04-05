package chess.web.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MoveDto {

    private final String source;
    private final String destination;

    @JsonCreator
    public MoveDto(@JsonProperty("source") String source,
                   @JsonProperty("destination") String destination) {
        this.source = source;
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }
}
