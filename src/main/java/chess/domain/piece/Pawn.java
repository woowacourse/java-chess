package chess.domain.piece;

import chess.domain.team.Team;

import java.util.List;

import static chess.domain.piece.Direction.*;
import static chess.domain.team.Team.*;

public final class Pawn extends Piece {

    private boolean isFirst = true;

    public Pawn(final Team team) {
        super(team);
    }

    @Override
    public boolean movable(final Direction move) {
        if (team().equals(BLACK)) {
            return DOWN.equals(move);
        }
        return UP.equals(move);
    }

    @Override
    public boolean movableByCount(final int count) {
        if (isFirst) {
            isFirst = false;
            return count <= 2;
        }
        return count <= 1;
    }

    @Override
    public boolean isAttack(final Direction direction, final Team targetTeam) {
        if (isSameTeam(BLACK) && targetTeam.equals(WHITE)) {
            return List.of(LEFT_DOWN, RIGHT_DOWN).contains(direction);
        }

        if (isSameTeam(WHITE) && targetTeam.equals(BLACK)) {
            return List.of(LEFT_UP, RIGHT_UP).contains(direction);
        }

        return false;
    }
}
