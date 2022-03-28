package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import chess.model.piece.strategy.LimitedMovableStrategy;

public final class Knight extends Piece{
    public Knight(Color color, Square square) {
        super(color, square, new LimitedMovableStrategy(Direction.getKnightDirection(), 1));
    }

    @Override
    public String getLetter() {
        return "n";
    }

    @Override
    public Point getPoint() {
        return Point.KNIGHT;
    }
}
