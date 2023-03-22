package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;

public class Rook extends Piece {
    private static final Rook WHITE = new Rook(Team.WHITE);
    private static final Rook BLACK = new Rook(Team.BLACK);

    private Rook(Team team) {
        super(Role.ROOK, team);
    }

    public static Rook of(Team team) {
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
        return canMoveCross(source, target);
    }
}
