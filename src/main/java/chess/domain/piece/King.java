package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;

public class King extends Piece {

    public King(Team team) {
        super(Role.KING, team);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return source.getDistanceTo(target) == 1;
    }
}
