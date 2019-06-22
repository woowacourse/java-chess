package chess.domain.direction;

import chess.domain.board.Board;
import chess.domain.direction.core.MoveStrategy;
import chess.domain.direction.core.Square;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Route {
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
        return squares.get(0);
    }

    public Square getTargetSquare() {
        return squares.get(squares.size() - 1);
    }

    public boolean canMove(Board board) {
        return moveStrategy.canMove(board.getTargetStatus(getSourceSquare(), getTargetSquare())) &&
                !isNonePieceInRoute(board);
    }

    private boolean isNonePieceInRoute(Board board) {
        // 막힌게 있니? 있어(true)
        return IntStream.rangeClosed(1, squares.size() - 2)
                .mapToObj(index -> board.hasPiece(squares.get(index)))
                .reduce(false, (a, b) -> a && b)
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(squares, route.squares);
    }

    @Override
    public int hashCode() {
        return Objects.hash(squares);
    }
}
