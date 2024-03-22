package model.piece.state;

import static model.direction.Direction.N;
import static model.direction.Direction.NE;
import static model.direction.Direction.NW;
import static model.direction.Direction.S;
import static model.direction.Direction.SE;
import static model.direction.Direction.SW;

import java.util.ArrayList;
import java.util.List;
import model.direction.Direction;
import model.piece.Color;
import model.position.Position;
import model.position.Route;

public final class Pawn extends Role {
    private static final List<Direction> WHITE_DIRECTIONS = List.of(N, NE, NW);
    private static final List<Direction> BLACK_DIRECTIONS = List.of(S, SE, SW);
    private static final int INITIAL_WHITE_PAWN_RANK = 2;
    private static final int INITIAL_BLACK_PAWN_RANK = 7;

    private Pawn(Color color, List<Direction> directions) {
        super(color, directions);
    }

    public static Pawn from(Color color) {
        if (color == Color.WHITE) {
            return new Pawn(color, WHITE_DIRECTIONS);
        }
        return new Pawn(color, BLACK_DIRECTIONS);
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
