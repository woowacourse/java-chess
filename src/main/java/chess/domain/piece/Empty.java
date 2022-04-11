package chess.domain.piece;

import chess.domain.direction.Direction;
import chess.domain.piece.movestrategy.MoveStrategy;
import java.util.ArrayList;
import java.util.List;

public final class Empty extends Piece {

    Empty(Symbol symbol, Team team) {
        super(symbol, team);
    }

    @Override
    public List<Direction> direction() {
        return new ArrayList<>();
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
