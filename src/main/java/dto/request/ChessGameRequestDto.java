package dto.request;

public class ChessGameRequestDto {

    private final int gameId;
    private final boolean isRestart;
    private final boolean isPlaying;

    public ChessGameRequestDto(int gameId, boolean isRestart, boolean isPlaying) {
        this.gameId = gameId;
        this.isRestart = isRestart;
        this.isPlaying = isPlaying;
    }

    public boolean isRestart() {
        return isRestart;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public int getGameId() {
        return gameId;
    }
}
