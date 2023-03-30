package dto;

import domain.chessGame.ScoreCalculator;

public class ChessGameScoreDto {

    private final double blackScore;
    private final double whiteScore;

    public ChessGameScoreDto(ScoreCalculator scoreCalculator) {
        this.blackScore = scoreCalculator.getBlackScore();
        this.whiteScore = scoreCalculator.getWhiteScore();
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }
}
