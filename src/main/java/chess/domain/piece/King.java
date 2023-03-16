package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.List;

public final class King extends Piece {
    private King(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static King from(final Color color) {
        return new King(PieceType.KING, color);
    }

    @Override
    public List<Position> findPositions(final Position source, final Position target) {
        final int rowDirection = Integer.compare(target.getRow(), source.getRow());
        final int columnDirection = Integer.compare(target.getColumn(), source.getColumn());

        return List.of(Position.of(source.getRow() + rowDirection, source.getColumn() + columnDirection));
    }
}
