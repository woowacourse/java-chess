package chess.dto;

import chess.domain.piece.Color;

public class GameResultDto {

    private final Color winner;
    private final double whiteScore;
    private final double blackScore;

    public GameResultDto(Color winner, double whiteScore, double blackScore) {
        this.winner = winner;
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public Color getWinner() {
        return winner;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }
}
