package chess.domain.piece;

import static chess.domain.position.Direction.BOTTOM;
import static chess.domain.position.Direction.LEFT;
import static chess.domain.position.Direction.RIGHT;
import static chess.domain.position.Direction.TOP;

import chess.domain.Color;
import chess.domain.piece.strategy.LinearMovingStrategy;
import chess.domain.position.Direction;
import java.util.List;

public class Rook extends Piece {

    private static final List<Direction> DIRECTIONS = List.of(RIGHT, LEFT, TOP, BOTTOM);

    public Rook(Color color) {
        super(PieceType.ROOK, color, new LinearMovingStrategy(DIRECTIONS));
    }
}