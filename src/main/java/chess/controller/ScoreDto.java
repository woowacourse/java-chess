package chess.controller;

import chess.chessgame.PlayerScore;

public class ScoreDto {

    private final PlayerScore whiteScore;
    private final PlayerScore blackScore;

    public ScoreDto(final PlayerScore whiteScore, final PlayerScore blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public double getWhiteScore() {
        return whiteScore.getPlayerScore();
    }

    public double getBlackScore() {
        return blackScore.getPlayerScore();
    }
}
