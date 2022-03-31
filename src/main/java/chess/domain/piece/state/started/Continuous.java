package chess.domain.piece.state.started;

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
        List<Position> positions = new ArrayList<>();

        Position current = source;
        Position next = current.getNext(direction);

        while (!current.isBlocked(direction) && !board.isFilled(next)) {
            positions.add(next);
            current = next;
            next = current.getNext(direction);
        }

        if (board.canKill(source, next)) {
            positions.add(next);
        }

        return positions;
    }
}
