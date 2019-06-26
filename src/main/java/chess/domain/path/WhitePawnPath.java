package chess.domain.path;

import chess.domain.board.Direction;
import chess.domain.board.Square;
import chess.domain.board.Vector;
import chess.domain.board.Vectors;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class WhitePawnPath implements Path {
    private static WhitePawnPath instance = new WhitePawnPath();

    private WhitePawnPath() { }

    static WhitePawnPath getInstance() {
        return instance;
    }

    @Override
    public Vectors movableArea(Square source) {
        Vectors movableArea = new Vectors(new LinkedHashSet<>());
        movableArea.add(new Vector(source.moveUp(1), Direction.UP));
        movableArea.add(new Vector(source.moveUpRight(), Direction.UP_RIGHT));
        movableArea.add(new Vector(source.moveUpLeft(), Direction.UP_LEFT));
        movableArea.add(new Vector(source.moveUp(2), Direction.UP));

        return movableArea.removeSource(source);
    }
}
