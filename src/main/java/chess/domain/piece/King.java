package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

import java.util.List;

public class King extends Piece {
    King(final Color color) {
        super(color);
    }

    @Override
    public List<Position> findPositions(final Position source, final Position target) {
        final int rowDistance = Integer.compare(target.getRow(), source.getRow());
        final int columnDistance = Integer.compare(target.getColumn(), source.getColumn());
        return List.of(new Position(source.getRow() + rowDistance, source.getColumn() + columnDistance));
    }
}
