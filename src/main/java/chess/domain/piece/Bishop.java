package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.ArrayList;
import java.util.List;

public final class Bishop extends Piece {
    private static final int MAX_DISTANCE = 8;

    private Bishop(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static Bishop from(final Color color) {
        return new Bishop(PieceType.BISHOP, color);
    }

    @Override
    public List<Position> findPositions(final Position source, final Position target) {
        final int rowDirection = Integer.compare(target.getRow(), source.getRow());
        final int columnDirection = Integer.compare(target.getColumn(), source.getColumn());

        return createMovablePositions(source, rowDirection, columnDirection);
    }

    private List<Position> createMovablePositions(final Position source, final int rowDirection, final int columnDirection) {
        final List<Position> positions = new ArrayList<>();
        int sourceRow = source.getRow();
        int sourceColumn = source.getColumn();
        for (int distance = 0; distance < MAX_DISTANCE; distance++) {
            sourceRow += rowDirection;
            sourceColumn += columnDirection;
            positions.add(Position.of(sourceRow, sourceColumn));
        }

        return positions;
    }
}
