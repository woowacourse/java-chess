package chess.domain.piece;

import chess.domain.board.Direction;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color, true);
        this.type = Type.BISHOP;
    }

    @Override
    public List<Direction> direction() {
        return Direction.diagonalDirection();
    }
}
