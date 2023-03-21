package chess.model.piece.type;

import chess.model.Color;
import chess.model.piece.PieceType;
import chess.model.position.Direction;
import java.util.Set;

public class Rook extends Piece {

    private static final Set<Direction> directions = Direction.orthogonal();

    public Rook(final Color color) {
        super(color, PieceType.ROOK);
    }

    @Override
    boolean isRightDirection(final Direction direction) {
        return directions.stream()
                .anyMatch(it -> it.match(direction.rank(), direction.file()));
    }
}
