package chess.web.service.dto;

public class MoveDto {
    private final String from;
    private final String to;

    public MoveDto(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

}
