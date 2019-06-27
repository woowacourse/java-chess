package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.MoveVector;
import chess.domain.Point;

import java.util.List;
import java.util.Objects;

public abstract class Piece {
    private final ChessTeam team;
    private final List<MoveVector> directions;
    private PieceInfo info;

    Piece(ChessTeam team, PieceInfo info, List<MoveVector> directions) {
        this.team = team;
        this.info = info;
        this.directions = directions;
    }

    public boolean isKing() {
        return false;
    }

    public Direction move(MoveVector vector) {
        if (directions.contains(vector)) {
            return vector.getDirection();
        }
        throw new IllegalArgumentException();
    }

    public abstract Direction move(Point p1, Point p2);

    public Direction attack(Point p1, Point p2) {
        return move(p1, p2);
    }

    public boolean isAlly(Piece piece) {
        if (piece == null) throw new IllegalArgumentException();
        return this.team == piece.team;
    }

    public boolean isTurn(ChessTeam team) {
        return this.team == team;
    }

    public double score() {
        return info.score();
    }

    @Override
    public String toString() {
        return team.color() + "," + info.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return team == piece.team &&
                Objects.equals(directions, piece.directions) &&
                info == piece.info;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, directions, info);
    }
}
