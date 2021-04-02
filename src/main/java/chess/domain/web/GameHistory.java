package chess.domain.web;

import java.time.LocalDateTime;

public class GameHistory {
    private int gameHistoryId;
    private int gameId;
    private String command;
    private LocalDateTime createdTime;

    public GameHistory(int gameHistoryId, int gameId, String command, LocalDateTime createdTime) {
        this.gameHistoryId = gameHistoryId;
        this.gameId = gameId;
        this.command = command;
        this.createdTime = createdTime;
    }

    public GameHistory(int gameId, String command, LocalDateTime createdTime) {
        this.gameId = gameId;
        this.command = command;
        this.createdTime = createdTime;
    }

    public int getGameHistoryId() {
        return gameHistoryId;
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
