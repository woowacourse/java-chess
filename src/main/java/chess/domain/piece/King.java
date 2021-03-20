package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;

import java.util.List;

public class King extends Piece {

    public King(Color color) {
        super(color,false);
        this.type = Type.KING;
    }

    @Override
    public List<Direction> direction() {
        return Direction.everyDirection();
    }
}
