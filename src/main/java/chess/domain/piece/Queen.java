package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.piece.strategy.IterableMoveStrategy;
import java.util.List;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color, new IterableMoveStrategy());
        this.type = Type.QUEEN;
    }

    public Queen(Color color, char piece) {
        super(color, piece);
    }

    @Override
    public List<Direction> direction() {
        return Direction.everyDirection();
    }
}
