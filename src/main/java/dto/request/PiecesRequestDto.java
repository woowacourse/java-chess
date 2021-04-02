package dto.request;

public class PiecesRequestDto {

    private final int gameId;
    private final String source;
    private final String target;

    public PiecesRequestDto(int gameId, String source, String target) {
        this.gameId = gameId;
        this.source = source;
        this.target = target;
    }

    public String getTarget() {
        return target;
    }

    public String getSource() {
        return source;
    }

    public int getGameId() {
        return gameId;
    }
}
