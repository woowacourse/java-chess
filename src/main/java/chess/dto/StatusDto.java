package chess.dto;

import chess.domain.GameStatus;
import chess.domain.chesspiece.Color;

public class StatusDto {

    private final GameStatus gameStatus;
    private final Color currentTurn;

    private StatusDto(final GameStatus gameStatus, final Color currentTurn) {
        this.gameStatus = gameStatus;
        this.currentTurn = currentTurn;
    }

    public static StatusDto of(final String gameStatus, final String currentTurn) {
        return new StatusDto(
                GameStatus.from(gameStatus),
                Color.from(currentTurn)
        );
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Color getCurrentTurn() {
        return currentTurn;
    }
}
