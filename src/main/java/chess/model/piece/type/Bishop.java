package chess.model.piece.type;

import chess.model.Color;
import chess.model.piece.Direction;
import chess.model.piece.PieceType;
import java.util.List;

public class Bishop extends Piece {

    private static final List<Direction> directions = Direction.diagonal();

    public Bishop(final Color color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    boolean isRightDirection(final Direction direction) {
        return directions.stream()
                .anyMatch(it -> it.match(direction.rank(), direction.file()));
    }
}
