package chess.dto;

import chess.domain.GameStatus;
import chess.domain.chesspiece.Color;

public class RoomDto {

    private final String name;
    private final GameStatus gameStatus;
    private final Color currentTurn;

    private RoomDto(final String name, final GameStatus gameStatus, final Color currentTurn) {
        this.name = name;
        this.gameStatus = gameStatus;
        this.currentTurn = currentTurn;
    }

    public static RoomDto from(final String name, final String gameStatus, final String currentTurn) {
        return new RoomDto(
                name,
                GameStatus.from(gameStatus),
                Color.from(currentTurn)
        );
    }

    public String getName() {
        return name;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Color getCurrentTurn() {
        return currentTurn;
    }
}
