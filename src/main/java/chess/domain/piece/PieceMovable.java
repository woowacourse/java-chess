package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;
import chess.domain.direction.BasicDirection;
import chess.domain.direction.Direction;

import java.util.ArrayList;
import java.util.List;

public abstract class PieceMovable extends Piece {
    protected PieceMovable(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    @Override
    protected final List<Position> createMovablePositions(final Position source, final Position target) {
        final List<Position> positions = new ArrayList<>();
        final Direction direction = BasicDirection.from(source, target);

        Position current = source;
        while (current.isRangeOk(direction) && isDistanceOk(source, current.move(direction), target)) {
            current = addPositionAfterMove(positions, current, direction);
        }

        return positions;
    }

    private boolean isDistanceOk(final Position source, final Position current, final Position target) {
        return source.diff(target) >= source.diff(current);
    }

    private Position addPositionAfterMove(final List<Position> positions, Position current, final Direction direction) {
        current = current.move(direction);
        positions.add(current);
        return current;
    }
}
