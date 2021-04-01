package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.piece.strategy.IterableMoveStrategy;
import java.util.List;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color, new IterableMoveStrategy());
        this.type = Type.ROOK;
    }

    @Override
    public List<Direction> direction() {
        return Direction.linearDirection();
    }
}
