package chess.domain.piece;

import chess.domain.square.Direction;
import chess.domain.square.Square;

public class Rook extends Piece {

    public Rook(final Color color) {
        super(color);
    }

    @Override
    public Direction findDirection(Square current, Square destination) {

        return null;
    }
}
