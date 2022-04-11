package chess.dto;

import chess.domain.piece.Color;

public class TurnSaveRequest {

    private final Color color;
    private final int gameNumber;

    public TurnSaveRequest(Color color, int gameNumber) {
        this.color = color;
        this.gameNumber = gameNumber;
    }

    public Color getColor() {
        return color;
    }

    public int getGameNumber() {
        return gameNumber;
    }
}
