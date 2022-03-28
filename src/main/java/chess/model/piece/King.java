package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import chess.model.piece.strategy.LimitedMovableStrategy;

public final class King extends Piece {
    public King(Color color, Square square) {
        super(color, square, new LimitedMovableStrategy(Direction.getRoyalDirection(), 1));
    }

    @Override
    public Point getPoint() {
        return Point.KING;
    }
}
