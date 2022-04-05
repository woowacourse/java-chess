package chess.dto;

import chess.domain.Status;
import chess.domain.piece.Color;

public class ScoreDTO {
    private final double blackScore;
    private final double whiteScore;

    private ScoreDTO(double blackScore, double whiteScore) {
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
    }

    public static ScoreDTO of(Status status) {
        return new ScoreDTO(status.calculateScore(Color.BLACK), status.calculateScore(Color.WHITE));
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }
}
