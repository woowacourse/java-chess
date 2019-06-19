package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.Point;

import java.util.List;
import java.util.Objects;

public abstract class AbstractRangeMovePiece implements Piece {
    final ChessTeam team;
    private final String name;
    private final List<Direction> directions;

    public AbstractRangeMovePiece(String name, ChessTeam team, List<Direction> directions) {
        this.name = name;
        this.team = team;
        this.directions = directions;
    }

    @Override
    public Direction move(Point p1, Point p2) {
        Direction vector = p1.direction(p2).vector();
        if (directions.contains(vector)) {
            return vector;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Direction attack(Point p1, Point p2) {
        return move(p1, p2);
    }

    @Override
    public boolean isAlly(Piece piece) {
        if (piece == null) throw new IllegalArgumentException();
        if (piece instanceof AbstractSingleMovePiece) {
            return this.team == ((AbstractSingleMovePiece) piece).team;
        }
        return this.team == ((AbstractRangeMovePiece) piece).team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractRangeMovePiece that = (AbstractRangeMovePiece) o;
        return team == that.team &&
                name.equals(that.name) &&
                Objects.equals(directions, that.directions);
    }

    @Override
    public boolean isTurn(ChessTeam team) {
        return this.team == team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, name, directions);
    }

    @Override
    public String toString() {
        return name;
    }
}
