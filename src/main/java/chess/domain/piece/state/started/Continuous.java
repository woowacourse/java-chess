package chess.domain.piece.state.started;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import chess.domain.game.state.ChessBoard;
import chess.domain.game.state.position.Direction;
import chess.domain.game.state.position.Position;

public class Continuous {

    public List<Position> findMovablePositions(List<Direction> directions, Position source, ChessBoard board) {
        return directions.stream()
            .map(direction -> findContinuousPositions(direction, source, board))
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    }

    private List<Position> findContinuousPositions(Direction direction, Position source, ChessBoard board) {
        List<Position> list = new ArrayList<>();

        Position current = source;
        Position next = current.findNext(direction);

        while (!current.isBlocked(direction) && !board.isFilled(next)) {
            list.add(next);
            current = next;
            next = current.findNext(direction);
        }

        if (board.canKill(source, next)) {
            list.add(next);
        }

        return list;
    }
}
