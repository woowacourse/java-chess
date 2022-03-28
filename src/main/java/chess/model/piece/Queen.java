package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import chess.model.piece.strategy.UnlimitedMovableStrategy;

public final class Queen extends Piece {
    public Queen(Color color, Square square) {
        super(color, square, new UnlimitedMovableStrategy(Direction.getRoyalDirection()));
    }

    @Override
    public String getLetter() {
        return "q";
    }

    @Override
    public Point getPoint() {
        return Point.QUEEN;
    }
}
