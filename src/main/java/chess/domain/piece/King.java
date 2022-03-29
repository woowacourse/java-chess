package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.LengthBasedMovingStrategy;
import chess.domain.position.Position;
import java.util.List;

public class King extends Piece {

    private static final LengthBasedMovingStrategy MOVING_STRATEGY = new LengthBasedMovingStrategy(distance -> distance <= 2);

    public King(Color color) {
        super(PieceType.KING, color, null);
    }

    @Override
    public void validateMove(List<List<Piece>> board, Position source, Position target) {
        if (MOVING_STRATEGY.canMove(board, source, target)) {
            return;
        }

        throw new IllegalArgumentException();
    }
}
