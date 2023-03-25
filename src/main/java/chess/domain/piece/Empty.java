package chess.domain.piece;

import chess.domain.board.Position;

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
