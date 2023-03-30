package chess.domain.piece;

import chess.domain.board.Square;

import java.util.Collections;
import java.util.List;

public class Empty extends Piece {

    public Empty(Color color) {
        super(color);
    }

    @Override
    public List<Square> findRoute(Square source, Square destination) {
        return Collections.emptyList();
    }

    @Override
    public String getType() {
        return "Empty";
    }
}
