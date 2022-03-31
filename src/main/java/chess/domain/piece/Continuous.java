package chess.domain.piece;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import chess.domain.game.state.ChessBoard;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Position;

public class Continuous {

    public List<Position> getMovablePositions(List<Direction> directions, Position source, ChessBoard board) {
        return directions.stream()
            .map(direction -> getPositions(source, board, direction))
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }

    private List<Position> getPositions(Position source, ChessBoard board, Direction direction) {
        List<Position> movablePositions = new ArrayList<>();

        Position current = source;
        Position next = current.getNext(direction);

        while (!current.isBlocked(direction) && !board.isFilled(next)) {
            movablePositions.add(next);
            current = next;
            next = current.getNext(direction);
        }

        if (board.canKill(source, next)) {
            movablePositions.add(next);
        }

        return movablePositions;
    }
}
