package chess.domain.piece.state.started;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.game.state.position.Direction;
import chess.domain.game.state.position.Position;
import chess.domain.piece.Piece;

public abstract class Continuous extends Started {

    public List<Position> findMovablePositions(List<Direction> directions, Position source, Map<Position, Piece> board) {
        return directions.stream()
            .map(direction -> findContinuousPositions(direction, source, board))
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }

    private List<Position> findContinuousPositions(Direction direction, Position source, Map<Position, Piece> board) {
        List<Position> list = new ArrayList<>();
        Position next = addPositions(direction, source, board, list);
        addKillablePosition(source, board, list, next);

        return list;
    }

    private void addKillablePosition(Position source, Map<Position, Piece> board, List<Position> list, Position next) {
        if (canKill(board, source, next)) {
            list.add(next);
        }
    }

    private Position addPositions(Direction direction, Position source, Map<Position, Piece> board,
        List<Position> list) {
        Position current = source;
        Position next = current.findNext(direction);

        while (!current.isBlocked(direction) && !isFilled(board, next)) {
            list.add(next);
            current = next;
            next = current.findNext(direction);
        }
        return next;
    }

}
