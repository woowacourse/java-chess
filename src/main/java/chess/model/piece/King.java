package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import chess.model.piece.strategy.LimitedMovableStrategy;

public final class King extends Piece {

    private static final int KING_MOVE_LIMIT = 1;

    public King(Color color, Square square) {
        super(color, square, new LimitedMovableStrategy(Direction.getRoyalDirection(), KING_MOVE_LIMIT));
    }

    @Override
    public Point getPoint() {
        return Point.KING;
    }
}
