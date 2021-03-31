package chess.domain.moveStrategy;

import chess.domain.piece.Color;
import chess.domain.location.Vector;
import chess.domain.location.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class SingleMove implements MoveStrategy {
    protected Color color;

    public SingleMove(Color color) {
        this.color = color;
    }

    @Override
    public List<List<Position>> movablePositions(Position position) {
        List<Position> positions = new ArrayList<>();
        for (Vector direction : directions()) {
            positions.add(position.move(direction));
        }
        return Collections.singletonList(positions);
    }

    public abstract List<Vector> directions();
}
