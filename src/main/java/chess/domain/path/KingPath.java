package chess.domain.path;

import chess.domain.board.Direction;
import chess.domain.board.Square;
import chess.domain.board.Vector;
import chess.domain.board.Vectors;

import java.util.HashSet;

public class KingPath implements Path {
    private static KingPath instance = new KingPath();

    private KingPath() { }

    static KingPath getInstance() {
        return instance;
    }

    @Override
    public Vectors movableArea(Square source) {
        Vectors movableArea = new Vectors(new HashSet<>());

        movableArea.add(new Vector(source.moveUp(1), Direction.UP));
        movableArea.add(new Vector(source.moveRight(1), Direction.RIGHT));
        movableArea.add(new Vector(source.moveDown(1), Direction.DOWN));
        movableArea.add(new Vector(source.moveLeft(1), Direction.LEFT));
        movableArea.add(new Vector(source.moveUpRight(), Direction.UP_RIGHT));
        movableArea.add(new Vector(source.moveDownRight(), Direction.DOWN_RIGHT));
        movableArea.add(new Vector(source.moveDownLeft(), Direction.DOWN_LEFT));
        movableArea.add(new Vector(source.moveUpLeft(), Direction.UP_LEFT));

        return movableArea.removeSource(source);
    }
}
