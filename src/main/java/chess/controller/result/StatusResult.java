package chess.controller.result;

import chess.domain.Score;

public class StatusResult implements Result {

    private final Score score;

    public StatusResult(final Score score) {
        this.score = score;
    }

    public Score getScore() {
        return score;
    }
}
