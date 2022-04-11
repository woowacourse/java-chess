package chess.dto;

import spark.Request;

public class MoveRequest {
    private final String from;
    private final String to;

    public MoveRequest(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public MoveRequest(Request request) {
        this(request.queryParams("from"), request.queryParams("to"));
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
