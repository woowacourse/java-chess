package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.LinearMovingStrategy;
import chess.domain.piece.strategy.MovingStrategy;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class Queen extends Piece {

    private static final List<Direction> DIRECTIONS = List.of(Direction.values());
    private static final String NOTATION = "Q";

    private final MovingStrategy movingStrategy;

    public Queen(Color color) {
        super(color);
        this.movingStrategy = new LinearMovingStrategy(DIRECTIONS);
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
