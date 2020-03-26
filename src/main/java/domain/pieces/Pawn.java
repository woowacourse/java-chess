package domain.pieces;

import domain.move.Direction;
import domain.point.Point;
import domain.team.Team;
import java.util.Map;

public class Pawn extends Piece {

    private static final String INITIAL = "P";

    public Pawn(Team team) {
        super(INITIAL, team);
    }

    @Override
    public boolean isMovable(Map<Point, Piece> pieces, Point from, Point to) {
        if (team.equals(Team.BLACK)) {
            return isBlackPawnMovable(pieces, from, to);
        }
        return isWhitePawnMovable(pieces, from, to);
    }

    private boolean isBlackPawnMovable(Map<Point, Piece> pieces, Point from, Point to) {
        for (Direction direction : Direction.getBlackPawnDirection()) {
            if (direction.isMovable(from.getRowDistance(to), from.getColumnDistance(to))) {
                return true;
            }
        }
        return false;
    }

    private boolean isWhitePawnMovable(Map<Point, Piece> pieces, Point from, Point to) {
        for (Direction direction : Direction.getWhitePawnDirection()) {
            if (direction.isMovable(from.getRowDistance(to), from.getColumnDistance(to))) {
                return true;
            }
        }
        return false;
    }
}
