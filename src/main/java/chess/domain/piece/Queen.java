package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.LinearMovingStrategy;
import chess.domain.position.Direction;
import java.util.List;

public class Queen extends Piece {

    private static final List<Direction> DIRECTIONS = List.of(Direction.values());

    public Queen(Color color) {
        super(PieceType.QUEEN, color, new LinearMovingStrategy(DIRECTIONS));
    }
}
