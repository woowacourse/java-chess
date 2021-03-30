package chess.domain.moveStrategy;

import chess.domain.piece.Direction;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class SingleMove implements MoveStrategy {
    @Override
    public List<List<Position>> movablePositions(Position position) {
        List<Position> positions = new ArrayList<>();
        for (Direction direction : directions()) {
            positions.add(position.move(direction.column(),direction.row()));
        }
        return Collections.singletonList(positions);
    }

    public abstract List<Direction> directions();
}
