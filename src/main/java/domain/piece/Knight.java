package domain.piece;

import domain.ChessVector;
import domain.Direction;
import domain.Square;
import domain.Team;

import java.util.List;
import java.util.Objects;

public class Knight extends Piece {
    private static final List<ChessVector> SQUARE_VECTORS = List.of(
            new ChessVector(1, 2), new ChessVector(1, -2), new ChessVector(-1, 2), new ChessVector(-1, -2),
            new ChessVector(2, 1), new ChessVector(2, -1), new ChessVector(-2, 1), new ChessVector(-2, -1));

    public Knight(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        if (source.next2(Direction.EAST).next2(Direction.EAST).next2(Direction.NORTH).equals(target)) {
            return true;
        }
        if (source.next2(Direction.EAST).next2(Direction.EAST).next2(Direction.SOUTH).equals(target)) {
            return true;
        }
        if (source.next2(Direction.WEST).next2(Direction.WEST).next2(Direction.NORTH).equals(target)) {
            return true;
        }
        if (source.next2(Direction.WEST).next2(Direction.WEST).next2(Direction.SOUTH).equals(target)) {
            return true;
        }
        if (source.next2(Direction.NORTH).next2(Direction.NORTH).next2(Direction.EAST).equals(target)) {
            return true;
        }
        if (source.next2(Direction.NORTH).next2(Direction.NORTH).next2(Direction.WEST).equals(target)) {
            return true;
        }
        if (source.next2(Direction.SOUTH).next2(Direction.SOUTH).next2(Direction.EAST).equals(target)) {
            return true;
        }
        if (source.next2(Direction.SOUTH).next2(Direction.SOUTH).next2(Direction.WEST).equals(target)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final Knight piece)) {
            return false;
        }
        return this.team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, Knight.class);
    }
}
