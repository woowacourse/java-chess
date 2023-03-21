package chess.domain.piece;

import chess.domain.team.Team;

import java.util.List;

import static chess.domain.piece.Direction.*;

public final class Knight extends Piece {

    private static final List<Direction> moves = List.of(
            RIGHT_RIGHT_UP, RIGHT_RIGHT_DOWN, RIGHT_UP_UP, RIGHT_DOWN_DOWN,
            LEFT_LEFT_UP, LEFT_LEFT_DOWN, LEFT_UP_UP, LEFT_DOWN_DOWN
    );

    public Knight(final Team team) {
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
