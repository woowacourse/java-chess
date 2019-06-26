package chess.domain.path;

import chess.domain.board.Direction;
import chess.domain.board.Square;
import chess.domain.board.Vector;
import chess.domain.board.Vectors;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class BlackPawnPath implements Path {
    private static BlackPawnPath instance = new BlackPawnPath();

    private BlackPawnPath() { }

    static BlackPawnPath getInstance() {
        return instance;
    }

    @Override
    public Vectors movableArea(Square source) {
        Vectors movableArea = new Vectors(new LinkedHashSet<>());
        movableArea.add(new Vector(source.moveDown(1), Direction.DOWN));
        movableArea.add(new Vector(source.moveDownRight(), Direction.DOWN_RIGHT));
        movableArea.add(new Vector(source.moveDownLeft(), Direction.DOWN_LEFT));
        movableArea.add(new Vector(source.moveDown(2), Direction.DOWN));

        return movableArea.removeSource(source);
    }
}
