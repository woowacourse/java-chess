package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.color.Color;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static chess.domain.color.Color.*;
import static chess.domain.move.Direction.*;
import static chess.domain.position.File.*;
import static chess.domain.position.Rank.*;

public final class Rook extends Piece {
    private static final Set<Direction> directions = Set.of(UP, DOWN, LEFT, RIGHT);

    public Rook(final Color color, final Position position) {
        super(color, position);
    }

    public static List<Position> getInitialBlackPosition() {
        List<Position> positions = new ArrayList<>();
        positions.add(Position.of(A, EIGHT));
        positions.add(Position.of(H, EIGHT));
        return positions;
    }

    public static List<Position> getInitialWhitePosition() {
        List<Position> positions = new ArrayList<>();
        positions.add(Position.of(A, ONE));
        positions.add(Position.of(H, ONE));
        return positions;
    }

    @Override
    public String name() {
        String name = "r";
        if (super.color().equals(WHITE)) {
            return name;
        }
        return name.toUpperCase();
    }

    @Override
    public boolean movable(final Direction direction) {
        return directions.contains(direction);
    }

    @Override
    public boolean movableByCount(final int count) {
        return true;
    }
}
