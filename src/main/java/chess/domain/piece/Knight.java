package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;

public class Knight extends Piece {

    public Knight(Team team) {
        super(Role.KNIGHT, team);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        int absX = Math.abs(source.getX() - target.getX());
        int absY = Math.abs(source.getY() - target.getY());
        return isInKnightRange(absX, absY);
    }

    private static boolean isInKnightRange(int absX, int absY) {
        return (absX == 2 && absY == 1) || (absX == 1 && absY == 2);
    }
}
