package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    private static final int MAX_DISTANCE = 8;

    private Queen(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static Queen from(final Color color) {
        return new Queen(PieceType.QUEEN, color);
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
        for (int i = 0; i < MAX_DISTANCE; i++) {
            sourceRow += rowDirection;
            sourceColumn += columnDirection;
            positions.add(Position.of(sourceRow, sourceColumn));
        }

        return positions;
    }
}
