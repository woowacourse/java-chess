package chess.domain.web;

import java.time.LocalDateTime;

public class GameHistory {
    private final int gameId;
    private final String command;
    private final LocalDateTime createdTime;
    private int id;

    public GameHistory(int id, int gameId, String command, LocalDateTime createdTime) {
        this.id = id;
        this.gameId = gameId;
        this.command = command;
        this.createdTime = createdTime;
    }

    public GameHistory(int gameId, String command, LocalDateTime createdTime) {
        this.gameId = gameId;
        this.command = command;
        this.createdTime = createdTime;
    }

    public int getGameId() {
        return gameId;
    }

    public String getCommand() {
        return command;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }
}
