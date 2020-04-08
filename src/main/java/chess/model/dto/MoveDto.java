package chess.model.dto;

public class MoveDto {

    private final String source;
    private final String target;
    private final String gameId;

    public MoveDto(String source, String target, String gameId) {
        this.source = source;
        this.target = target;
        this.gameId = gameId;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public String getGameId() {
        return gameId;
    }
}
