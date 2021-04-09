package chess.domain.dto;

import chess.domain.web.Game;
import java.time.LocalDateTime;

public class GameDto {
    private int gameId;
    private int userId;
    private boolean isEnd;
    private LocalDateTime createdTime;

    public GameDto(Game game) {
        this.gameId = game.getGameId();
        this.userId = game.getUserId();
        this.isEnd = game.isEnd();
        this.createdTime = game.getCreatedTime();
    }

    public int getGameId() {
        return gameId;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }
}
