package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;

public class Queen extends Piece {

    public Queen(Team team) {
        super(Role.QUEEN, team);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return canMoveCross(source, target) || canMoveDiagonal(source, target);
    }
}
