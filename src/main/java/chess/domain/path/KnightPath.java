package chess.domain.path;

import chess.domain.board.Direction;
import chess.domain.board.Square;
import chess.domain.board.Vector;
import chess.domain.board.Vectors;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class KnightPath implements Path {
    private static KnightPath instance = new KnightPath();

    private KnightPath() { }

    static KnightPath getInstance() {
        return instance;
    }

    @Override
    public Vectors movableArea(Square source) {
        Vectors movableArea = new Vectors(new HashSet<>());
        movableArea.add(new Vector(source.moveUp(1).moveUpRight(), Direction.NONE));
        movableArea.add(new Vector(source.moveRight(1).moveUpRight(), Direction.NONE));
        movableArea.add(new Vector(source.moveRight(1).moveDownRight(), Direction.NONE));
        movableArea.add(new Vector(source.moveDown(1).moveDownRight(), Direction.NONE));
        movableArea.add(new Vector(source.moveDown(1).moveDownLeft(), Direction.NONE));
        movableArea.add(new Vector(source.moveLeft(1).moveDownLeft(), Direction.NONE));
        movableArea.add(new Vector(source.moveLeft(1).moveUpLeft(), Direction.NONE));
        movableArea.add(new Vector(source.moveUp(1).moveUpLeft(), Direction.NONE));


        movableArea.remove(new Vector(source.moveUpRight(), Direction.NONE));
        movableArea.remove(new Vector(source.moveUpLeft(), Direction.NONE));
        movableArea.remove(new Vector(source.moveDownRight(), Direction.NONE));
        movableArea.remove(new Vector(source.moveDownLeft(), Direction.NONE));
        movableArea.remove(new Vector(source, Direction.NONE));

        return movableArea.removeSameLines(source);
    }
}
