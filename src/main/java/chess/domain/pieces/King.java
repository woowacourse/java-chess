package chess.domain.pieces;

import chess.domain.Direction;
import chess.domain.Point;
import chess.domain.Team;
import chess.domain.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class King extends Piece {
    public King(Team team) {
        super(team, Type.KING);
    }

    @Override
    public List<Point> move(Point start, Point end) {
        List<Point> points = new ArrayList<>();
        Point vector = start.makeVector(end);
        if (type.getDirections().contains(Direction.findDirection(vector))) {
            points.add(end);
        }
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        King king = (King) o;
        return team == king.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}
