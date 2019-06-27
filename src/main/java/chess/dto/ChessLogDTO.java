package chess.dto;

public class ChessLogDTO {
    private String from;
    private String to;

    public ChessLogDTO(String from, String to) {
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
