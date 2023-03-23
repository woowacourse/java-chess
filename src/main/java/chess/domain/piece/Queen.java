package chess.domain.piece;

import chess.domain.move.Direction;
import chess.domain.color.Color;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static chess.domain.move.Direction.*;
import static chess.domain.position.File.*;
import static chess.domain.position.Rank.*;

public final class Queen extends Piece {
    private static final Set<Direction> directions = Set.of(UP, DOWN, LEFT, RIGHT, RIGHT_UP, RIGHT_DOWN, LEFT_UP, LEFT_DOWN);

    public Queen(final Color color, final Position position) {
        super(color, position);
    }

    public static List<Position> getInitialBlackPosition() {
        List<Position> positions = new ArrayList<>();
        positions.add(Position.of(D, EIGHT));
        return positions;
    }

    public static List<Position> getInitialWhitePosition() {
        List<Position> positions = new ArrayList<>();
        positions.add(Position.of(D, ONE));
        return positions;
    }

    @Override
    public String name() {
        String name = "q";
        if (super.team().equals(Color.WHITE)) {
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
