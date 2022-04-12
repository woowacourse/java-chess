package chess.dto;

public class MoveRequestDto {
    private final String piece;
    private final String from;
    private final String to;
    private final String gameId;

    public MoveRequestDto(String piece, String from, String to, String gameId) {
        this.piece = piece;
        this.from = from;
        this.to = to;
        this.gameId = gameId;
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

    public String getGameId() {
        return gameId;
    }
}
