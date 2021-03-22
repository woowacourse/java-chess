package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.movestrategy.MoveStrategy;
import chess.domain.piece.team.Color;
import chess.domain.piece.team.Symbol;

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
        throw new IllegalStateException();
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
        throw new IllegalStateException();
    }
}
