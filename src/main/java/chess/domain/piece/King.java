package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.piece.strategy.NonIterableMoveStrategy;
import java.util.List;

public class King extends Piece {

    public King(Color color) {
        super(color, new NonIterableMoveStrategy());
        this.type = Type.KING;
    }

    @Override
    public List<Direction> direction() {
        return Direction.everyDirection();
    }
}
