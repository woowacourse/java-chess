package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;

public class Bishop extends Piece {
    private static final Bishop WHITE = new Bishop(Team.WHITE);
    private static final Bishop BLACK = new Bishop(Team.BLACK);

    private Bishop(Team team) {
        super(Role.BISHOP, team);
    }

    public static Bishop of(Team team) {
        if (team == Team.WHITE) {
            return WHITE;
        }
        if (team == Team.BLACK) {
            return BLACK;
        }
        throw new IllegalArgumentException("[ERROR] 올바르지 않는 팀 입니다.");
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return canMoveDiagonal(source, target);
    }
}
