package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.piece.strategy.BlackPawnMovingStrategy;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.piece.strategy.WhitePawnMovingStrategy;
import chess.domain.position.Position;

public class Pawn extends Piece {

    private static final MovingStrategy WHITE_MOVING_STRATEGY = new WhitePawnMovingStrategy();
    private static final MovingStrategy BLACK_MOVING_STRATEGY = new BlackPawnMovingStrategy();

    public Pawn(Color color) {
        super(PieceType.PAWN, color);
    }

    @Override
    public void validateMove(Board board, Position sourcePosition, Position targetPosition) {
        if (this.isBlack()) {
            BLACK_MOVING_STRATEGY.validateMove(board, sourcePosition, targetPosition);
            return;
        }

        WHITE_MOVING_STRATEGY.validateMove(board, sourcePosition, targetPosition);
    }
}
