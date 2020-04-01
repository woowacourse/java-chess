package chess;

import chess.piece.Team;

public class Scores {
    private final double whiteScore;
    private final double blackScore;

    public Scores(Board board) {
        this.whiteScore = board.calculateScoreOf(Team.WHITE);
        this.blackScore = board.calculateScoreOf(Team.BLACK);
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }
}
