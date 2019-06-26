package chess.domain.path;

import chess.domain.board.Direction;
import chess.domain.board.Square;
import chess.domain.board.Vector;
import chess.domain.board.Vectors;

import java.util.HashSet;
import java.util.Set;

public class BishopPath implements Path {
    private static final BishopPath instance = new BishopPath();

    private BishopPath() {}

    static BishopPath getInstance() {
        return instance;
    }

    @Override
    public Vectors movableArea(Square source) {
        Vectors movableArea = new Vectors(new HashSet<>());
        for (Square square : source.moveUpRightToEnd()) {
            movableArea.add(new Vector(square, Direction.UP_RIGHT));
        }
        for (Square square : source.moveUpLeftToEnd()) {
            movableArea.add(new Vector(square, Direction.UP_LEFT));
        }
        for (Square square : source.moveDownRightToEnd()) {
            movableArea.add(new Vector(square, Direction.DOWN_RIGHT));
        }
        for (Square square : source.moveDownLeftToEnd()) {
            movableArea.add(new Vector(square, Direction.DOWN_LEFT));
        }
        return movableArea;
    }
}
