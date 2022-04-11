package chess.dto;

public class MoveDto {

    private final String gameId;
    private final String from;
    private final String to;

    public MoveDto(final String gameId, final String from, final String to) {
        this.gameId = gameId;
        this.from = from;
        this.to = to;
    }

    public Long getGameId() {
        return Long.valueOf(gameId);
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
