package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.Result;
import chess.domain.piece.Color;

import java.util.Map;
import java.util.Objects;

public final class Status {
    private final Map<Color, Double> whiteScore;
    private final Map<Color, Double> blackScore;
    private final Result result;

    public Status(final Board board) {
        this.whiteScore = board.sumScore(Color.WHITE);
        this.blackScore = board.sumScore(Color.BLACK);
        this.result = Result.from(board);
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
}
