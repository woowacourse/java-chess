package chess.dto;

import chess.domain.piece.Color;

public class GameResultDto {

    private final double whiteScore;
    private final double blackScore;

    public GameResultDto(double whiteScore, double blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public Color winnerColor() {
        if (whiteScore > blackScore) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    public double whiteScore() {
        return whiteScore;
    }

    public double blackScore() {
        return blackScore;
    }
}
