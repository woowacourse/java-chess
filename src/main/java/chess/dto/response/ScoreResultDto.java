package chess.dto.response;

import chess.domain.game.ChessGame;
import chess.domain.game.score.ScoreResult;
import chess.domain.piece.PieceColor;

public class ScoreResultDto {
    private final double whiteScore;
    private final double blackScore;

    private ScoreResultDto(double whiteScore, double blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public static ScoreResultDto from(ChessGame chessGame) {
        ScoreResult scoreResult = chessGame.getStatus();

        double whiteScore = scoreResult.getScoreByPieceColor(PieceColor.WHITE).getValue();
        double blackScore = scoreResult.getScoreByPieceColor(PieceColor.BLACK).getValue();

        return new ScoreResultDto(whiteScore, blackScore);
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }

    @Override
    public String toString() {
        return "ScoreResultDto{" +
                "whiteScore=" + whiteScore +
                ", blackScore=" + blackScore +
                '}';
    }
}
