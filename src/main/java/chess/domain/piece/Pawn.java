package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.BlackPawnMovingStrategy;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.piece.strategy.WhitePawnMovingStrategy;
import chess.domain.position.Position;
import java.util.List;

public class Pawn extends Piece {

    private static final String NOTATION = "P";

    private final MovingStrategy movingStrategy;

    public Pawn(Color color) {
        super(color);

        if (color.isBlack()) {
            this.movingStrategy = new BlackPawnMovingStrategy();
            return;
        }

        this.movingStrategy = new WhitePawnMovingStrategy();
    }

    @Override
    public String getNotation() {
        if (isBlack()) {
            return NOTATION;
        }

        return NOTATION.toLowerCase();
    }

    public void validateMove(List<List<Piece>> board, Position sourcePosition, Position targetPosition) {
        movingStrategy.validateMove(board, sourcePosition, targetPosition);
    }
}
