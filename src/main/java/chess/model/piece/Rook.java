package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import chess.model.piece.strategy.UnlimitedMovableStrategy;
import java.util.List;

public final class Rook extends Piece {

    public Rook(Color color, Square square) {
        super(color, square, new UnlimitedMovableStrategy(Direction.getRookDirection()));
    }

    @Override
    public Point getPoint() {
        return Point.ROOK;
    }
}
