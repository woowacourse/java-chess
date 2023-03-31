package chess.dto;

import chess.domain.Color;

public class GameDto {
    private final long gameId;
    private final boolean isEnd;
    private final Color lastPlayer;

    public GameDto(final long gameId, final boolean isEnd, final Color lastPlayer) {
        this.gameId = gameId;
        this.isEnd = isEnd;
        this.lastPlayer = lastPlayer;
    }

    public long getGameId() {
        return gameId;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public Color getLastPlayer() {
        return lastPlayer;
    }
}
