package chess.domain.piece;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public final class Pawn extends Piece {

    public Pawn(Color color) {
        super(PieceName.PAWN, color);
    }

    @Override
    public boolean isMovableRoute(List<Position> routeFromStartToEnd) {
        Position start = routeFromStartToEnd.get(0);
        Position end = routeFromStartToEnd.get(routeFromStartToEnd.size() - 1);
        List<Position> movablePositions = getMovablePositions(start);
        return movablePositions.contains(end);
    }

    private List<Position> getMovablePositions(Position start) {
        if (start.isBlackPawnInitialRow() || start.isWhitePawnInitialRow()) {
            return getInitialMovablePositions(start);
        }
        return getNonInitialMovablePosition(start);
    }

    private List<Position> getInitialMovablePositions(Position start) {
        if (isBlack()) {
            return new ArrayList<>(List.of(
                    start.moveDown(),
                    start.moveDown().moveDown(),
                    start.moveDownLeft(),
                    start.moveDownRight()));
        }
        return new ArrayList<>(List.of(
                start.moveUp(),
                start.moveUp().moveUp(),
                start.moveUpLeft(),
                start.moveUpRight()));
    }

    private List<Position> getNonInitialMovablePosition(Position start) {
        if (isBlack()) {
            return new ArrayList<>(List.of(
                    start.moveDown(),
                    start.moveDownLeft(),
                    start.moveDownRight()));
        }
        return new ArrayList<>(List.of(
                start.moveUp(),
                start.moveUpLeft(),
                start.moveUpRight()));
    }
}
