package model.piece.state;

import static model.direction.Direction.N;
import static model.direction.Direction.S;

import java.util.ArrayList;
import java.util.List;
import model.direction.Direction;
import model.direction.ShiftPattern;
import model.piece.Color;
import model.position.Position;
import model.position.Route;

public final class Pawn extends Role {
    private static final int INITIAL_WHITE_PAWN_RANK = 2;
    private static final int INITIAL_BLACK_PAWN_RANK = 7;

    private Pawn(Color color, ShiftPattern shiftPattern) {
        super(color, shiftPattern);
    }

    public static Pawn from(Color color) {
        if (color == Color.WHITE) {
            return new Pawn(color, ShiftPattern.WHITE_PAWN_PATTERN);
        }
        return new Pawn(color, ShiftPattern.BLACK_PAWN_PATTERN);
    }

    @Override
    protected Route findMovingPatternRoute(Direction direction, Position movedPosition) {
        List<Position> sequentialPositions = new ArrayList<>();
        if ((movedPosition.rank() == INITIAL_WHITE_PAWN_RANK && direction == N) || (
                movedPosition.rank() == INITIAL_BLACK_PAWN_RANK && direction == S)) {
            movedPosition = movedPosition.getNextPosition(direction);
            sequentialPositions.add(movedPosition);
        }
        if (movedPosition.isAvailablePosition(direction)) {
            movedPosition = movedPosition.getNextPosition(direction);
            sequentialPositions.add(movedPosition);
        }
        return new Route(direction, sequentialPositions);
    }
}
