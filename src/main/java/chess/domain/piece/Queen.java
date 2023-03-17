package chess.domain.piece;

import chess.domain.square.Direction;
import chess.domain.square.Square;

public class Queen extends Piece {

    public Queen(final Color color) {
        super(color);
    }

    @Override
    public Direction findDirection(Square current, Square destination) {

        return null;
    }
}
