package chess.domain.piece;

import static chess.domain.piece.Direction.NEE;
import static chess.domain.piece.Direction.NNE;
import static chess.domain.piece.Direction.NNW;
import static chess.domain.piece.Direction.NWW;
import static chess.domain.piece.Direction.SEE;
import static chess.domain.piece.Direction.SSE;
import static chess.domain.piece.Direction.SSW;
import static chess.domain.piece.Direction.SWW;

import chess.domain.board.Position;
import java.util.List;

public class Knight extends Piece {

    private static final List<Direction> POSSIBLE_DIRECTIONS = List.of(NNE, NEE, SEE, SSE, SSW, SWW, NWW, NNW);

    public Knight(final Color color) {
        super(PieceType.KNIGHT, color);
    }

    @Override
    public Direction findValidDirection(final Position current, final Position target) {
        Direction direction = calculateDirection(current, target);
        validateDirection(direction, POSSIBLE_DIRECTIONS);
        return direction;
    }

}
