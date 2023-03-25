package chess.domain.piece;

import chess.domain.board.Position;
import chess.strategy.EmptyStrategy;

public class Empty extends Piece {

    public static final Empty INSTANCE = new Empty();

    private Empty() {
        super(Role.EMPTY, Team.NONE, new EmptyStrategy());
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return false;
    }
}
