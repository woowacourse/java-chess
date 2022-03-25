package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.LengthBasedMovingStrategy;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.position.Position;
import java.util.List;

public class King extends Piece {

    private static final String NOTATION = "K";

    private final MovingStrategy movingStrategy;

    public King(Color color) {
        super(color);
        this.movingStrategy = new LengthBasedMovingStrategy(number -> number > 2);
    }

    public void validateMove(List<List<Piece>> board, Position sourcePosition, Position targetPosition) {
        movingStrategy.validateMove(board, sourcePosition, targetPosition);
    }

    @Override
    public String getNotation() {
        if (isBlack()) {
            return NOTATION;
        }

        return NOTATION.toLowerCase();
    }
}
