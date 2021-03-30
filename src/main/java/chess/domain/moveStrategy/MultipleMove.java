package chess.domain.moveStrategy;

import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.position.Position;

import java.util.List;
import java.util.stream.Collectors;

public abstract class MultipleMove implements MoveStrategy {
    protected Color color;

    public MultipleMove(Color color) {
        this.color = color;
    }

    @Override
    public List<List<Position>> movablePositions(Position position) {
        return directions().stream()
                           .map(direction -> position.positionsOfDirection(direction.column(), direction.row()))
                           .collect(Collectors.toList());
    }

    public abstract List<Direction> directions();
}
