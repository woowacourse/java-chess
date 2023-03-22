package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;

public class King extends Piece {
    private static final King WHITE = new King(Team.WHITE);
    private static final King BLACK = new King(Team.BLACK);

    private King(Team team) {
        super(Role.KING, team);
    }

    public static King of(Team team) {
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
        return source.getDistanceTo(target) == 1;
    }
}
