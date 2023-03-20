package chess.domain.piece;

import chess.domain.Position;
import chess.domain.Role;
import chess.domain.Team;

public class Empty extends Piece {
    public static final Empty INSTANCE = new Empty();

    private Empty() {
        super(Role.EMPTY, Team.NONE);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return false;
    }
}
