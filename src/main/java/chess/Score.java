package chess;

import chess.piece.Color;
import java.math.BigDecimal;

public class Score {

    private final BigDecimal whiteScore;
    private final BigDecimal blackScore;

    public Score(BigDecimal whiteScore, BigDecimal blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public static Score from(ChessBoard chessBoard) {
        return new Score(chessBoard.getScore(Color.WHITE), chessBoard.getScore(Color.BLACK));
    }

    public BigDecimal getWhiteScore() {
        return whiteScore;
    }

    public BigDecimal getBlackScore() {
        return blackScore;
    }
}
