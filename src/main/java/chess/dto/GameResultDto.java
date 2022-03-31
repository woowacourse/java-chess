package chess.dto;

import chess.domain.piece.Color;

public class GameResultDto {

    private final Color winnerColor;

    private final double whiteScore;
    private final double blackScore;

    public GameResultDto(Color winner, double whiteScore, double blackScore) {
        this.winnerColor = winner;
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public Color winnerColor() {
        return winnerColor;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }

}
