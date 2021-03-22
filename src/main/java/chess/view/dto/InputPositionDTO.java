package chess.view.dto;

public class InputPositionDTO {
    private final String from;
    private final String to;

    public InputPositionDTO(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String from() {
        return from;
    }

    public String to() {
        return to;
    }
}
