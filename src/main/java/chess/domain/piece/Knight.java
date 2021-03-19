package chess.domain.piece;

import chess.domain.board.Direction;
import java.util.List;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color, false);
        this.type = Type.KNIGHT;
    }

    @Override
    public List<Direction> direction() {
        return Direction.knightDirection();
    }
}
