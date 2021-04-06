package chess.domain.piece.moveStrategy;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Direction;

import java.util.List;
import java.util.stream.Collectors;

public abstract class MoveStrategy {
    public List<Position> searchMovablePositions(Position target, List<Direction> directions) {
        return directions.stream()
                .flatMap(direction -> calculateBoardPosition(target, direction).stream())
                .collect(Collectors.toList());
    }

    abstract List<Position> calculateBoardPosition(Position target, Direction direction);

    public abstract boolean canMove(Position target, Position destination, Board board);
}
