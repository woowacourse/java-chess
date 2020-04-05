package dto;

import chess.result.ChessScores;
import chess.score.Score;

public class ChessGameScoresDTO {
    private final Score whiteScore;
    private final Score blackScore;

    public ChessGameScoresDTO(ChessScores chessScores) {
        whiteScore = chessScores.getWhiteScore();
        blackScore = chessScores.getBlackScore();
    }
}
