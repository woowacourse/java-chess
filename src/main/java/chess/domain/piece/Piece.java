package chess.domain.piece;

import chess.domain.Spot;
import chess.domain.Team;

import java.util.Objects;

public abstract class Piece {
    private Team team;
    double score;
    PieceType pieceType;

    Piece(Team team) {
        this.team = team;
    }

    public boolean isEmpty() {
        return team.isEmpty();
    }

    public double score() {
        return score;
    }

    public boolean checkTeam(Team team) {
        return this.team.isSameTeam(team);
    }

    public boolean checkTeam(Piece piece) {
        return team.isSameTeam(piece.team);
    }

    public Team getTeam() {
        return team;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public abstract boolean isMovable(Spot startSpot, Spot endSpot);

    public abstract boolean isAttackable(Spot startSpot, Spot endSpot);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Double.compare(piece.score, score) == 0 &&
                team == piece.team &&
                pieceType == piece.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, score, pieceType);
    }
}

