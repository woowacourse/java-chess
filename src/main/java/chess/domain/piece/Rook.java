package chess.domain.piece;

import chess.domain.team.Team;

import java.util.List;

import static chess.domain.piece.Direction.DOWN;
import static chess.domain.piece.Direction.LEFT;
import static chess.domain.piece.Direction.RIGHT;
import static chess.domain.piece.Direction.UP;

public final class Rook extends Piece {
    private static final List<Direction> moves = List.of(
            RIGHT, LEFT, UP, DOWN
    );

    public Rook(final Team team) {
        super(team);
    }

    @Override
    public boolean movable(final Direction move, final Piece targetPiece) {
        return moves.contains(move);
    }

    @Override
    public boolean movableByCount(final int count) {
        return true;
    }
}
