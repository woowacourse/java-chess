package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.EmptyPoints;
import chess.domain.board.Route;

public class Empty extends Piece {

    private static class EmptyHelper {
        public static final Piece INSTANCE = new Empty();
    }

    private Empty() {
        super(Color.NONE, PieceType.EMPTY);
    }

    public static Piece getInstance() {
        return EmptyHelper.INSTANCE;
    }

    @Override
    public boolean move(Route route, EmptyPoints emptyPoints) {
        throw new UnsupportedOperationException("[ERROR] 빈 공간은 움직일 수 없습니다.");
    }
}
