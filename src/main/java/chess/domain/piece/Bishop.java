package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;
import chess.domain.direction.BasicDirection;
import chess.domain.direction.Direction;

import java.util.ArrayList;
import java.util.List;

public final class Bishop extends Piece {
    private Bishop(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static Bishop from(final Color color) {
        return new Bishop(PieceType.BISHOP, color);
    }

    @Override
    protected List<Position> createMovablePositions(final Position source, final Position target) {
        final List<Position> positions = new ArrayList<>();
        final Direction direction = BasicDirection.from(source, target);

        Position current = source;
        while (isMovable(current, target, direction)) {
            current = addPositionAfterMove(positions, current, direction);
        }
        addLastPosition(positions, current, direction);

        return positions;
    }

    private boolean isMovable(final Position current, final Position target, final Direction direction) {
        return current.isRangeOk(direction) && !target.equals(current.move(direction));
    }

    private Position addPositionAfterMove(final List<Position> positions, Position current, final Direction direction) {
        current = current.move(direction);
        positions.add(current);
        return current;
    }

    private void addLastPosition(final List<Position> positions, final Position current, final Direction direction) {
        positions.add(current.move(direction));
    }
}
