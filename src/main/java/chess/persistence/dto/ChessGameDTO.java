package chess.persistence.dto;

import java.util.Objects;

public class ChessGameDTO {
    private int gameId;
    private boolean gameStatus;
    private String lastUser;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setGameStatus(boolean gameStatus) {
        this.gameStatus = gameStatus;
    }

    public boolean getGameStatus() {
        return gameStatus;
    }

    public String getLastUser() {
        return lastUser;
    }

    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessGameDTO that = (ChessGameDTO) o;
        return gameId == that.gameId &&
                gameStatus == that.gameStatus &&
                Objects.equals(lastUser, that.lastUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, gameStatus, lastUser);
    }
}
