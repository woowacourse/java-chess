package chess.domain.piece;

import chess.domain.Role;
import chess.domain.Team;

public class Empty extends Piece {
    public static final Empty INSTANCE = new Empty();

    private Empty() {
        super(Role.EMPTY, Team.EMPTY);
    }
}
