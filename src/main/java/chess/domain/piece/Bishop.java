package chess.domain.piece;

import chess.domain.team.Team;

import java.util.List;

import static chess.domain.piece.Direction.*;

public final class Bishop extends Piece {

    private static final List<Direction> moves = List.of(RIGHT_UP, RIGHT_DOWN, LEFT_UP, LEFT_DOWN);

    public Bishop(final Team team) {
        super(team);
    }

    @Override
    public boolean movable(final Direction direction) {
        return moves.contains(direction);
    }

    @Override
    public boolean movableByCount(final int count) {
        return true;
    }
}
