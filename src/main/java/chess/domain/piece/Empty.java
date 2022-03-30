package chess.domain.piece;

import chess.domain.direction.Direction;
import chess.domain.piece.movestrategy.MoveStrategy;

public class Empty extends Piece {
    public Empty(Symbol symbol, Team team) {
        super(symbol, team);
    }

    @Override
    public boolean hasNotDirection(Direction direction) {
        return false;
    }

    @Override
    public MoveStrategy moveStrategy() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isExist() {
        return false;
    }
}
