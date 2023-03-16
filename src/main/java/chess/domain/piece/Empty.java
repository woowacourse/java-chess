package chess.domain.piece;

import chess.domain.Camp;
import chess.domain.Role;

public class Empty extends Piece {

    public Empty() {
        super(Camp.EMPTY, Role.EMPTY);
    }
}
