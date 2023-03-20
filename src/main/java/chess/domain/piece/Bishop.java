package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;

public class Bishop extends Piece {

    public Bishop(Team team) {
        super(Role.BISHOP, team);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return canMoveDiagonal(source, target);
    }
}
