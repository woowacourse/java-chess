package chess.model.dto;

public class SourceDto {

    private int gameId;
    private String source;

    public SourceDto(int gameId, String source) {
        this.gameId = gameId;
        this.source = source;
    }

    public int getGameId() {
        return gameId;
    }

    public String getSource() {
        return source;
    }
}
