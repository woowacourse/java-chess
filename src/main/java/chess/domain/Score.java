package chess.domain;

import chess.domain.board.Board;

public class Score {

    private final double blackScore;
    private final double whiteScore;

    private Score(double blackScore, double whiteScore) {
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
    }

    public static Score from(Board board) {
        return new Score(
                board.score(Side.BLACK),
                board.score(Side.WHITE));
    }

    public double whiteScore() {
        return whiteScore;
    }

    public double blackScore() {
        return blackScore;
    }
}
