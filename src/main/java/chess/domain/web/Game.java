package chess.domain.web;

import java.time.LocalDateTime;
import java.util.Objects;

public class Game {
    private int gameId;
    private int userId;
    private boolean isEnd;
    private LocalDateTime createdTime;

    public Game(int gameId, int userId, boolean isEnd, LocalDateTime createdTime) {
        this.gameId = gameId;
        this.userId = userId;
        this.isEnd = isEnd;
        this.createdTime = createdTime;
    }

    public Game(int userId, boolean isEnd, LocalDateTime createdTime) {
        this.gameId = -1;
        this.userId = userId;
        this.isEnd = isEnd;
        this.createdTime = createdTime;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Game)) {
            return false;
        }
        Game game = (Game) o;
        return gameId == game.gameId && userId == game.userId && isEnd == game.isEnd
            && Objects.equals(createdTime, game.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, userId, isEnd, createdTime);
    }
}
