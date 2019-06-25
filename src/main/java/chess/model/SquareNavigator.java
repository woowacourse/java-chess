package chess.model;

import chess.model.board.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SquareNavigator {
    private final Direction direction;
    private final Square beginSquare;
    private int distance;

    public SquareNavigator(Direction direction, Square beginSquare, int distance) {
        this.direction = direction;
        this.beginSquare = beginSquare;
        this.distance = distance;
    }

    public List<Square> findSquares(Board board) {
        if (!beginSquare.hasNext(direction))
            return new ArrayList<>();
        return calculateUnblockedSquares(board);
    }

    private List<Square> calculateUnblockedSquares(Board board) {
        List<Square> squares = new ArrayList<>();
        Square temp = beginSquare;
        while (temp.hasNext(direction) && distance-- > 0) {
            if (!board.isNullPiece(temp = temp.next(direction))) {
                if (!board.isSameSide(beginSquare, temp))
                    squares.add(temp);
                break;
            }
            squares.add(temp);
        }
        return squares;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SquareNavigator navigator = (SquareNavigator) o;
        return distance == navigator.distance &&
                direction == navigator.direction &&
                beginSquare.equals(navigator.beginSquare);
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, beginSquare, distance);
    }
}