package chess.domain.piece;

import static chess.domain.piece.Direction.E;
import static chess.domain.piece.Direction.N;
import static chess.domain.piece.Direction.NE;
import static chess.domain.piece.Direction.NW;
import static chess.domain.piece.Direction.S;
import static chess.domain.piece.Direction.SE;
import static chess.domain.piece.Direction.SW;
import static chess.domain.piece.Direction.W;

import chess.domain.board.Position;
import java.util.List;

public class Queen extends Piece {

    private static final List<Direction> POSSIBLE_DIRECTIONS = List.of(N, S, W, E, NE, SE, SW, NW);

    public Queen(final Color color) {
        super(PieceType.QUEEN, color);
    }

    @Override
    public Direction findValidDirection(final Position current, final Position target) {
        Direction direction = calculateDirection(current, target);
        validateDirection(direction, POSSIBLE_DIRECTIONS);
        return direction;
    }

}
