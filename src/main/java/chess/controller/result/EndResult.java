package chess.controller.result;

import chess.domain.Score;

public class EndResult implements Result {

    private final Score score;

    public EndResult(final Score score) {
        this.score = score;
    }

    public Score getScore() {
        return score;
    }
}
