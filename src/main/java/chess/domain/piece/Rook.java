package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;

public class Rook extends Piece {

    public Rook(Team team) {
        super(Role.ROOK, team);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return canMoveCross(source, target);
    }
}
