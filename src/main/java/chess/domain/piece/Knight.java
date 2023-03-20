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
        int xDistance = source.getXDistanceTo(target);
        int yDistance = source.getYDistanceTo(target);
        return isInKnightRange(xDistance, yDistance);
    }

    private boolean isInKnightRange(int xDistance, int yDistance) {
        return (xDistance == 2 && yDistance == 1) || (xDistance == 1 && yDistance == 2);
    }
}
