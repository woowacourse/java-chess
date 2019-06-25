package chess.domain.pieces;

import chess.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    protected final Team team;
    protected final Type type;

    public Piece(Team team, Type type) {
        this.team = team;
        this.type = type;
    }

    public List<Point> move(Point start, Point end) {
        List<Point> points = new ArrayList<>();
        Navigator navigator = new Navigator(start, end);
        Direction direction = navigator.getDirection(type.getDirections());
        Point point = start;
        if (direction.equals(Direction.NOT_FIND)) return points;
        while (!point.equals(end)) {
            point = point.move(direction);
            points.add(point);
        }
        return points;
    }

    public List<Point> attack(Point start, Point end) {
        return move(start, end);
    }

    public boolean isSameTeam(Piece endPiece) {
        return this.team == endPiece.team;
    }

    public boolean isSameTeam(Team team) {
        return this.team == team;
    }

    public double getScore() {
        return type.getScore();
    }

    public boolean isSameType(Type type) {
        return this.type == type;
    }

    public Type getType() {
        return type;
    }

    abstract public String getSymbol();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return team == piece.team &&
                type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, type);
    }
}
