package chess.domain.piece;

import chess.domain.board.Square;

import java.util.List;

public class Empty extends Piece {

    public Empty(Color color) {
        super(color);
    }

    @Override
    public List<Square> findRoute(Square source, Square destination) {
        return null;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
