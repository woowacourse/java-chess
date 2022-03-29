package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.LengthBasedMovingStrategy;
import chess.domain.position.Position;
import java.util.List;

public class Knight extends Piece {

    private static final LengthBasedMovingStrategy MOVING_STRATEGY = new LengthBasedMovingStrategy(number -> number == 5);

    public Knight(Color color) {
        super(PieceType.KNIGHT, color, null);
    }

    @Override
    public void validateMove(List<List<Piece>> board, Position source, Position target) {
        if (MOVING_STRATEGY.canMove(board, source, target)) {
            return;
        }

        throw new IllegalArgumentException();
    }
}
