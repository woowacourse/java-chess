package chess.domain.piece;

import static chess.domain.piece.Direction.NE;
import static chess.domain.piece.Direction.NW;
import static chess.domain.piece.Direction.SE;
import static chess.domain.piece.Direction.SW;

import chess.domain.board.Position;
import java.util.List;

public class Bishop extends Piece {

    private static final List<Direction> POSSIBLE_DIRECTIONS = List.of(NE, SE, SW, NW);

    public Bishop(final Color color) {
        super(PieceType.BISHOP, color);
    }

    @Override
    public Direction findValidDirection(Position current, Position target) {
        Direction direction = calculateDirection(current, target);
        validateDirection(direction, POSSIBLE_DIRECTIONS);
        return direction;
    }

}
