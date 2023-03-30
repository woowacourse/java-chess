package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;

public class Knight extends Piece {
    private static final Knight WHITE = new Knight(Team.WHITE);
    private static final Knight BLACK = new Knight(Team.BLACK);

    private Knight(Team team) {
        super(Role.KNIGHT, team);
    }

    public static Knight of(Team team) {
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
        int xDistance = source.getXDistanceTo(target);
        int yDistance = source.getYDistanceTo(target);
        return isInKnightRange(xDistance, yDistance);
    }

    private boolean isInKnightRange(int xDistance, int yDistance) {
        return (xDistance == 2 && yDistance == 1) || (xDistance == 1 && yDistance == 2);
    }
}
