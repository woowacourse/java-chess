package data;

import chess.score.Score;

import java.util.List;

public class ChessGameScoresVO {
    private final Score whiteScore;
    private final Score blackScore;

    public ChessGameScoresVO(List<Score> scores) {
        this.whiteScore = scores.get(0);
        this.blackScore = scores.get(1);
    }

    public Score getWhiteScore() {
        return whiteScore;
    }

    public Score getBlackScore() {
        return blackScore;
    }
}
