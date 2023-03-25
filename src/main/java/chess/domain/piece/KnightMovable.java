package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;
import chess.domain.direction.Direction;
import chess.domain.direction.KnightDirection;

import java.util.Collections;
import java.util.List;

public abstract class KnightMovable extends Piece {
    protected KnightMovable(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    @Override
    protected final List<Position> createMovablePositions(final Position source, final Position target) {
        final Direction direction = KnightDirection.from(source, target);

        if (source.isRangeOk(direction)) {
            return List.of(source.move(direction));
        }

        return Collections.emptyList();
    }
}
