package chess.dto;

import chess.domain.piece.Color;

public class TurnUpdateRequest {

    private final Color color;
    private final int gameNumber;

    public TurnUpdateRequest(Color color, int gameNumber) {
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
