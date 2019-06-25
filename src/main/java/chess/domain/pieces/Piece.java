package chess.domain.pieces;

import chess.domain.position.Position;
import chess.domain.Team;

import java.util.Objects;

public abstract class Piece {
    protected Position position;
    protected Team team;

    public Piece(Position position, Team team) {
        this.position = position;
        this.team = team;
    }

    public boolean isSameTeamWith(Piece piece) {
        return this.team == piece.team;
    }

    public boolean isOurPiece(Team team) {
        return this.team == team;
    }

    public void move(Position position) {
        this.position = position;
    }

    public abstract boolean canMove(Position position);

    public abstract double getScore();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(position, piece.position) &&
                team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, team);
    }
}
