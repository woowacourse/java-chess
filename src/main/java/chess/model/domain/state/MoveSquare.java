package chess.model.domain.state;

import chess.model.domain.board.BoardSquare;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import util.NullChecker;

public class MoveSquare {

    private final Map<MoveOrder, BoardSquare> squares;

    public MoveSquare(String before, String after) {
        this(BoardSquare.of(before), BoardSquare.of(after));
    }

    public MoveSquare(BoardSquare boardSquareBefore, BoardSquare boardSquareAfter) {
        NullChecker.validateNotNull(boardSquareBefore, boardSquareAfter);

        Map<MoveOrder, BoardSquare> squares = new HashMap<>();
        squares.put(MoveOrder.BEFORE, boardSquareBefore);
        squares.put(MoveOrder.AFTER, boardSquareAfter);

        this.squares = Collections.unmodifiableMap(squares);
    }

    public BoardSquare get(MoveOrder moveOrder) {
        return squares.get(moveOrder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MoveSquare that = (MoveSquare) o;
        return Objects.equals(squares, that.squares);
    }

    @Override
    public int hashCode() {
        return Objects.hash(squares);
    }

    public boolean isJumpRank() {
        return squares.get(MoveOrder.AFTER).isJumpOneRank(squares.get(MoveOrder.BEFORE));
    }

    public BoardSquare getBetweenWhenJumpRank() {
        if (isJumpRank()) {
            return squares.get(MoveOrder.AFTER)
                .getBetweenWhenJumpOneRank(squares.get(MoveOrder.BEFORE));
        }
        throw new IllegalArgumentException("JUMP RANK가 아닙니다.");
    }
}
