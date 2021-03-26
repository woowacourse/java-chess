package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.movestrategy.CommonMoveStrategy;
import chess.domain.movestrategy.MoveStrategy;

import java.util.ArrayList;
import java.util.List;

public class Empty extends Piece {
    private Empty(Color color) {
        super(color, Symbol.EMPTY);
    }

    public static Empty create() {
        return new Empty(Color.EMPTY);
    }

    @Override
    public List<List<Position>> vectors(Position position) {
        return new ArrayList<>();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isNotEmpty() {
        return false;
    }

    @Override
    public MoveStrategy moveStrategy() {
        return new CommonMoveStrategy();
    }

    @Override
    public double score() {
        throw new IllegalStateException();
    }
}
