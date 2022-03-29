package chess.domain.piece;

import chess.domain.Position;
import chess.domain.player.Team;

import java.util.Objects;

public abstract class Piece {

    private final State state;

    protected Position position;

    public Piece(State state, Position position) {
        this.state = state;
        this.position = position;
    }

    public abstract Position move(Position currentPosition, Position destinationPosition, Team team);

    public boolean exist(final Position checkingPosition) {
        return position.equals(checkingPosition);
    }

    public Position capture(Position currentPosition, Position destinationPosition, Team team) {
        return move(currentPosition, destinationPosition, team);
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isSameFile(char file) {
        return position.isSameFile(file);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    public double getScore() {
        return state.getScore();
    }

    public char getName() {
        return state.getName();
    }

    public Position getPosition() {
        return position;
    }
}
