package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import chess.model.piece.strategy.UnlimitedMovableStrategy;

public final class Bishop extends Piece {
    public Bishop(Color color, Square square) {
        super(color, square, new UnlimitedMovableStrategy(Direction.getBishopDirection()));
    }

    @Override
    public Point getPoint() {
        return Point.BISHOP;
    }
}
