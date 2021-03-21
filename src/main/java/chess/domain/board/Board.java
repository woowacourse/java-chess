package chess.domain.board;

import chess.domain.order.MoveOrder;
import chess.domain.order.MoveResult;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.*;
import java.util.stream.Collectors;

public class Board {
    private final Map<Position, Square> board;

    protected Board(Map<Position, Square> board) {
        this.board = board;
    }

    public Square findByPosition(Position position) {
        return this.board.get(position);
    }

    public Map<Position, Square> board() {
        return Collections.unmodifiableMap(board);
    }

    public MoveResult move(Position from, Position to) {
        Square fromSquare = this.findByPosition(from);
        return fromSquare.move(createMoveOrder(from, to));
    }

    public MoveOrder createMoveOrder(Position from, Position to) {
        return new MoveOrder(Direction.of(from, to), getRoute(from,to), board.get(from), board.get(to));
    }

    private List<Square> getRoute(Position from, Position to) {
        Direction direction = Direction.of(from, to);
        List<Position> route = new ArrayList<>();
        Position currentPosition = from.getNextPosition(direction);

        while (!currentPosition.equals(to)) {
            route.add(currentPosition);
            currentPosition = currentPosition.getNextPosition(direction);
        }
        return route.stream()
                .map(board::get)
                .collect(Collectors.toList());
    }

}
