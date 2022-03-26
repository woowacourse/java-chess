package chess.domain.piece;

import static chess.domain.position.Direction.BOTTOM_LEFT;
import static chess.domain.position.Direction.BOTTOM_RIGHT;
import static chess.domain.position.Direction.TOP_LEFT;
import static chess.domain.position.Direction.TOP_RIGHT;

import chess.domain.Color;
import chess.domain.piece.strategy.LinearMovingStrategy;
import chess.domain.position.Direction;
import java.util.List;

public class Bishop extends Piece {

    private static final List<Direction> DIRECTIONS = List.of(TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT);

    public Bishop(Color color) {
        super(PieceType.BISHOP, color, new LinearMovingStrategy(DIRECTIONS));
    }
}
