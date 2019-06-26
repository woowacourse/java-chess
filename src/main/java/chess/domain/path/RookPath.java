package chess.domain.path;

import chess.domain.board.Direction;
import chess.domain.board.Square;
import chess.domain.board.Vector;
import chess.domain.board.Vectors;

import java.util.HashSet;
import java.util.Set;

public class RookPath implements Path {
    private static RookPath instance = new RookPath();

    private RookPath() {}

    static RookPath getInstance() {
        return instance;
    }

    @Override
    public Vectors movableArea(Square source) {
        Vectors movableArea = new Vectors(new HashSet<>());
        for (Square square : source.moveUpToEnd()) {
            movableArea.add(new Vector(square, Direction.UP));
        }
        for (Square square : source.moveDownToEnd()) {
            movableArea.add(new Vector(square, Direction.DOWN));
        }
        for (Square square : source.moveLeftToEnd()) {
            movableArea.add(new Vector(square, Direction.LEFT));
        }
        for (Square square : source.moveRightToEnd()) {
            movableArea.add(new Vector(square, Direction.RIGHT));
        }
        return movableArea;
    }
}
