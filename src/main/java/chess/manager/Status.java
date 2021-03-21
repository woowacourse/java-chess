package chess.manager;

import chess.domain.piece.rule.Score;

public class Status {

    private final Score whiteScore;
    private final Score blackScore;

    public Status(final Score whiteScore, final Score blackScore) {
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
    }

    public double whiteScore() {
        return whiteScore.score();
    }

    public double blackScore() {
        return blackScore.score();
    }
}
