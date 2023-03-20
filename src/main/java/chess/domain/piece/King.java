package chess.domain.piece;

import chess.domain.team.Team;

import java.util.List;

import static chess.domain.piece.Direction.*;

public final class King extends Piece {
    private static final List<Direction> moves = List.of(
            RIGHT, LEFT, UP, DOWN,
            RIGHT_UP, RIGHT_DOWN, LEFT_UP, LEFT_DOWN
    );

    public King(final Team team) {
        super(team);
    }

    @Override
    public boolean movable(final Direction move) {
        return moves.contains(move);
    }

    @Override
    public boolean movableByCount(final int count) {
        return count <= 1;
    }
}
