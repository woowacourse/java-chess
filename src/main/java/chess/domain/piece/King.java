package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.king.KingDefaultMovingStrategy;
import chess.domain.position.Position;
import java.util.List;

public class King extends Piece {

    private static final KingDefaultMovingStrategy MOVING_STRATEGY = new KingDefaultMovingStrategy();

    public King(Color color) {
        super(PieceType.KING, color, null);
    }

    @Override
    public void validateMove(List<List<Piece>> board, Position sourcePosition, Position targetPosition) {
        if (MOVING_STRATEGY.canMove(board, sourcePosition, targetPosition)) {
            return;
        }

        throw new IllegalArgumentException();
    }
}
