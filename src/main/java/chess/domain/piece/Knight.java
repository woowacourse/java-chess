package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.piece.strategy.NonIterableMoveStrategy;
import java.util.List;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color, new NonIterableMoveStrategy());
        this.type = Type.KNIGHT;
    }

    public Knight(Color color, char piece) {
        super(color, piece);
    }

    @Override
    public List<Direction> direction() {
        return Direction.knightDirection();
    }
}
