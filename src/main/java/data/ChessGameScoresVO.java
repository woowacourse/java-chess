package data;

import chess.result.ChessScores;
import chess.score.Score;

public class ChessGameScoresVO {
    private final Score whiteScore;
    private final Score blackScore;

    public ChessGameScoresVO(ChessScores chessScores) {
        whiteScore = chessScores.getWhiteScore();
        blackScore = chessScores.getBlackScore();
    }

    public Score getWhiteScore() {
        return whiteScore;
    }

    public Score getBlackScore() {
        return blackScore;
    }
}
