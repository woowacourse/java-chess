package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.piece.strategy.IterableMoveStrategy;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color, new IterableMoveStrategy());
        this.type = Type.BISHOP;
    }

    @Override
    public List<Direction> direction() {
        return Direction.diagonalDirection();
    }
}
