package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.LinearMovingStrategy;
import chess.domain.position.Direction;
import java.util.List;

public class Queen extends Piece {

    private static final List<Direction> DIRECTIONS = List.of(Direction.values());
    private static final String NOTATION = "Q";

    public Queen(Color color) {
        super(color, new LinearMovingStrategy(DIRECTIONS));
    }

    @Override
    public String getNotation() {
        if (isBlack()) {
            return NOTATION;
        }

        return NOTATION.toLowerCase();
    }
}
