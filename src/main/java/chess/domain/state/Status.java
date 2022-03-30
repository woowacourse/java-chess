package chess.domain.state;

import chess.domain.board.BoardCalculator;
import chess.domain.board.Result;
import chess.domain.piece.Piece;
import chess.domain.piece.notation.Color;
import chess.domain.position.Position;

import java.util.Map;
import java.util.Objects;

public final class Status {
    private final Map<Color, Double> whiteScore;
    private final Map<Color, Double> blackScore;
    private final Result result;

    public Status(final Color winnerColor, final Map<Position, Piece> board) {
        final var boardCalculator = new BoardCalculator(board);
        this.result = Result.from(winnerColor);
        this.whiteScore = boardCalculator.sumScore(Color.WHITE);
        this.blackScore = boardCalculator.sumScore(Color.BLACK);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return Objects.equals(whiteScore, status.whiteScore) && Objects.equals(blackScore, status.blackScore) && result == status.result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(whiteScore, blackScore, result);
    }

    public Map<Color, Double> getWhiteScore() {
        return whiteScore;
    }

    public Map<Color, Double> getBlackScore() {
        return blackScore;
    }

    public Result getResult() {
        return result;
    }
}
