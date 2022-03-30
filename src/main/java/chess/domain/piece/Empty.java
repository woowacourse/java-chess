package chess.domain.piece;

import chess.domain.Color;
import chess.domain.board.EmptyPoints;
import chess.domain.board.Route;

public class Empty extends Piece {

    public Empty() {
        super(Color.NONE, PieceType.EMPTY);
    }

    @Override
    public boolean move(Route route, EmptyPoints emptyPoints) {
        throw new UnsupportedOperationException("[ERROR] 빈 공간은 움직일 수 없습니다.");
    }
}
