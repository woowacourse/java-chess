package chess.domain.direction;

import chess.domain.board.Board;
import chess.domain.direction.core.MoveStrategy;
import chess.domain.direction.core.Square;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Route {
    private static final int FIRST = 0;
    private static final int LAST_MINUS = 1;
    private static final int START_RANGE = 1;
    private static final int MINUS_END_RANGE = 2;

    private List<Square> squares;
    private MoveStrategy moveStrategy;

    public Route(List<Square> squares, MoveStrategy moveStrategy) {
        this.squares = squares;
        this.moveStrategy = moveStrategy;
    }

    public int size() {
        return squares.size();
    }

    public Square getSourceSquare() {
        return squares.get(FIRST);
    }

    public Square getTargetSquare() {
        return squares.get(squares.size() - LAST_MINUS);
    }

    public boolean canMove(Board board) {
        return moveStrategy.canMove(board.getTargetStatus(getSourceSquare(), getTargetSquare())) &&
                !isPieceInRoute(board);
    }

    private boolean isPieceInRoute(Board board) {
        return IntStream.rangeClosed(START_RANGE, squares.size() - MINUS_END_RANGE)
                .mapToObj(index -> board.hasPiece(squares.get(index)))
                .reduce(false, (a, b) -> a || b)
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;
        Route route = (Route) o;
        return Objects.equals(squares, route.squares) &&
                moveStrategy == route.moveStrategy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(squares, moveStrategy);
    }
}
