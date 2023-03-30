package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;

public class Queen extends Piece {
    private static final Queen WHITE = new Queen(Team.WHITE);
    private static final Queen BLACK = new Queen(Team.BLACK);

    private Queen(Team team) {
        super(Role.QUEEN, team);
    }

    public static Queen of(Team team) {
        if (team == Team.WHITE) {
            return WHITE;
        }
        if (team == Team.BLACK) {
            return BLACK;
        }
        throw new IllegalArgumentException(INVALID_TEAM_EXCEPTION_MESSAGE);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return canMoveCross(source, target) || canMoveDiagonal(source, target);
    }
}
