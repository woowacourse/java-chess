package chess.domain.order;

import chess.domain.board.Board;
import chess.domain.board.Square;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.List;

public class MoveOrder {
    private final Board board;
    private final Position from;
    private final Position to;

    public MoveOrder(Board board, Position from, Position to) {
        this.board = board;
        this.from = from;
        this.to = to;
    }

    public Direction getDirection() {
        return Direction.of(this.from, this.to);
    }

    public List<Square> getRoute() {
        return this.board.getRoute(from, to);
    }

    public Position getFromPosition() {
        return this.from;
    }

    public Position getToPosition() {
        return this.from;
    }

    public Square getFromSquare() {
        return board.findByPosition(from);
    }

    public Square getToSquare() {
        return board.findByPosition(to);
    }
}
