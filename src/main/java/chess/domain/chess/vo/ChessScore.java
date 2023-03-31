package chess.domain.chess.vo;

import chess.domain.piece.Score;

import java.util.Objects;

public class ChessScore {
    private final Score whiteScore;
    private final Score blackScore;

    public ChessScore(final Score whiteScore, final Score blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ChessScore chessScore = (ChessScore) o;
        return Objects.equals(whiteScore, chessScore.whiteScore) && Objects.equals(blackScore, chessScore.blackScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(whiteScore, blackScore);
    }

    public Score getWhiteScore() {
        return whiteScore;
    }

    public Score getBlackScore() {
        return blackScore;
    }
}
