package model.piece.state;

import model.direction.Direction;
import model.direction.ShiftPattern;
import model.piece.Color;
import model.position.Position;
import model.position.Route;
import model.shift.SingleShift;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class Pawn extends Role {
    private static int WHITE_INITIAL_RANK = 2;
    private static int BLACK_INITIAL_RANK = 7;

    public Pawn(Color color) {
        super(color, new SingleShift(matchShiftPatternBy(color)));
    }

    private static ShiftPattern matchShiftPatternBy(Color color) {
        if (color == Color.BLACK) {
            return ShiftPattern.BLACK_PAWN;
        }
        return ShiftPattern.WHITE_PAWN;
    }

    @Override
    public Set<Route> findPossibleAllRoute(Position position) {
        if (position.rank() == WHITE_INITIAL_RANK) {
            return routes(Direction.N, position);
        }
        if (position.rank() == BLACK_INITIAL_RANK) {
            return routes(Direction.S, position);
        }
        return super.findPossibleAllRoute(position);
    }

    public Set<Route> routes(Direction direction, Position position) {
        List<Position> sequentialPositions = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            position = position.getNextPosition(direction);
            sequentialPositions.add(position);
        }
        return Set.of(new Route(direction, sequentialPositions));
    }
}
