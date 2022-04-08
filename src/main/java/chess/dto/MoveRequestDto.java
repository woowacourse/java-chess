package chess.dto;

import spark.Request;

public class MoveRequestDto {
    private final String from;
    private final String to;

    public MoveRequestDto(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public MoveRequestDto(Request request) {
        this(request.queryParams("from"), request.queryParams("to"));
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
