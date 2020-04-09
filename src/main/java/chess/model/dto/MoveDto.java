package chess.model.dto;

public class MoveDto {

    private final String source;
    private final String target;
    private final int gameId;

    public MoveDto(String source, String target, int gameId) {
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

    public int getGameId() {
        return gameId;
    }
}
