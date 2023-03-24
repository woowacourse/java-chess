package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;
import chess.domain.direction.BasicDirection;
import chess.domain.direction.Direction;

import java.util.Collections;
import java.util.List;

public final class King extends Piece {
    private King(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static King from(final Color color) {
        return new King(PieceType.KING, color);
    }

    @Override
    protected List<Position> createMovablePositions(final Position source, final Position target) {
        final Direction direction = BasicDirection.from(source, target);

        if (source.isRangeOk(direction)) {
            return List.of(source.move(direction));
        }

        return Collections.emptyList();
    }
}
