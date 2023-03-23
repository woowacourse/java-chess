package chess.domain.piece;

import chess.domain.team.Team;

import java.util.List;

import static chess.domain.piece.Direction.DOWN;
import static chess.domain.piece.Direction.LEFT_DOWN;
import static chess.domain.piece.Direction.LEFT_UP;
import static chess.domain.piece.Direction.RIGHT_DOWN;
import static chess.domain.piece.Direction.RIGHT_UP;
import static chess.domain.piece.Direction.UP;
import static chess.domain.team.Team.BLACK;
import static chess.domain.team.Team.WHITE;

public final class Pawn extends Piece {

    private boolean isFirst = true;

    public Pawn(final Team team) {
        super(team);
    }

    @Override
    public boolean movable(final Direction move, final Piece piece) {
        if (team().equals(BLACK) && DOWN.equals(move) && piece.isEmpty()) {
            return true;
        }

        if (team().equals(WHITE) && UP.equals(move) && piece.isEmpty()) {
            return true;
        }

        return false;
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
    public boolean isAttack(final Direction direction, final Piece targetPiece) {
        if (isSameTeam(BLACK) && targetPiece.team().equals(WHITE)) {
            return List.of(LEFT_DOWN, RIGHT_DOWN).contains(direction);
        }

        if (isSameTeam(WHITE) && targetPiece.team().equals(BLACK)) {
            return List.of(LEFT_UP, RIGHT_UP).contains(direction);
        }

        return false;
    }
}
