package chess.domain.board;

import chess.domain.piece.Color;

public class GameInformation {
    private final int gameId;
    private Color curentTurnColor;

    public GameInformation(int gameId, Color curentTurnColor) {
        this.gameId = gameId;
        this.curentTurnColor = curentTurnColor;
    }

    public int getGameId() {
        return gameId;
    }

    public Color getCurentTurnColor() {
        return curentTurnColor;
    }

    public void convertTurn() {
        curentTurnColor = curentTurnColor.convertTurn();
    }
}
