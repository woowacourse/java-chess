package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.KnightMovingStrategy;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.position.Position;
import java.util.List;

public class Knight extends Piece {

    private static final String NOTATION = "N";

    private final MovingStrategy movingStrategy;

    public Knight(Color color) {
        super(color);
        this.movingStrategy = new KnightMovingStrategy();
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
