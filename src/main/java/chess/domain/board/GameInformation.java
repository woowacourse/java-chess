package chess.domain.board;

import chess.domain.piece.Color;

public class GameInformation {
    private final int gameId;
    private final Color curentTurnColor;

    public GameInformation(int gameId, Color curentTurnColor) {
        this.gameId = gameId;
        this.curentTurnColor = curentTurnColor;
    }
}
