package chess.dto;

public class MoveResultDto {
    private final String piece;
    private final String from;
    private final String to;
    private final boolean result;

    public MoveResultDto(String piece, String from, String to, boolean result) {
        this.piece = piece;
        this.from = from;
        this.to = to;
        this.result = result;
    }

    public String getPiece() {
        return piece;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public boolean isResult() {
        return result;
    }
}

