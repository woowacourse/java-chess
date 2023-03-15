package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    private static final int MAX_DISTANCE = 8;

    Bishop(final Color color) {
        super(color);
    }

    @Override
    public List<Position> findPositions(final Position source, final Position target) {
        final int rowDistance = Integer.compare(target.getRow(), source.getRow());
        final int columnDistance = Integer.compare(target.getColumn(), source.getColumn());
        final List<Position> positions = new ArrayList<>();

        int sourceRow = source.getRow();
        int sourceColumn = source.getColumn();
        for (int i = 0; i < MAX_DISTANCE; i++) {
            sourceRow += rowDistance;
            sourceColumn += columnDistance;
            positions.add(new Position(sourceRow, sourceColumn));
        }

        return positions;
    }
}
