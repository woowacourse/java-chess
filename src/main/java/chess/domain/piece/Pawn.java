package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;

public class Pawn extends Piece {

    public Pawn(Team team) {
        super(Role.PAWN, team);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        if (source.getY() == target.getY()) {
            return false;
        }
        int distance = source.getDistanceTo(target);
        if (this.team == Team.WHITE) {
            if (distance == 2 && source.getY() == 1) {
                return true;
            }
            if(source.getY() > target.getY()) {
                return false;
            }
        }
        if (this.team == Team.BLACK) {
            if (distance == 2 && source.getY() == 6) {
                return true;
            }

            if(source.getY() < target.getY()) {
                return false;
            }
        }
        return distance == 1;
    }
}
