package chess.domain.pieces;

import chess.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pawn extends Piece {

    private static final int ONE_STEP = 1;
    private static final int TWO_STEP = 2;

    public Pawn(Team team) {
        super(team, (team == Team.WHITE) ? Type.WHITE_PAWN : Type.BLACK_PAWN);
    }

    @Override
    public String getSymbol() {
        return team == Team.WHITE ? "&#9817;" : "&#9823;";
    }

    @Override
    public List<Point> move(Point start, Point end) {
        List<Point> points = new ArrayList<>();
        Navigator navigator = new Navigator(start, end);
        Direction foundDirection = navigator.getDirection(type.getDirections());
        if (isTwoStep(start, end, foundDirection)) {
            return Arrays.asList(start.move(foundDirection), end);
        }
        if (isOneStep(start, end, foundDirection)) {
            points.add(start.move(foundDirection));
        }
        return points;
    }

    @Override
    public List<Point> attack(Point start, Point end) {
        List<Point> points = new ArrayList<>();
        Point vector = start.makeVector(end);
        if (type.getDirections().contains(Direction.findDirection(vector))) {
            points.add(end);
        }
        return points;
    }

    private boolean isOneStep(Point start, Point end, Direction foundDirection) {
        return type.getDirections().contains(foundDirection) &&
                start.isStep(end, ONE_STEP);
    }

    private boolean isTwoStep(Point start, Point end, Direction foundDirection) {
        return foundDirection.equals(team.getDirection()) &&
                start.isSameY(team.getFirstIndex()) &&
                start.isStep(end, TWO_STEP);
    }
}
