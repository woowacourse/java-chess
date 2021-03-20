package chess.manager;

import chess.domain.piece.Score;

public class Status {

    private final Score whiteScore;
    private final Score blackScore;

    public Status(Score whiteScore, Score blackScore){
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
