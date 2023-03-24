package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.List;

public abstract class Piece {
    protected final PieceType pieceType;
    protected final Color color;

    protected Piece(final PieceType pieceType, final Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public List<Position> findPositions(final Position source, final Position target) {
        final List<Position> movablePositions = createMovablePositions(source, target);
        validateUnmovablePosition(movablePositions, target);

        return movablePositions;
    }

    public void validateUnmovablePosition(final List<Position> positions, final Position target) {
        if (!positions.contains(target)) {
            throw new IllegalArgumentException("움직일 수 없는 위치입니다.");
        }
    }

    public boolean isSameColor(final Color color) {
        return this.color == color;
    }

    public boolean isNotSameColor(final Color color) {
        return this.color != color;
    }

    public boolean isSamePieceType(final PieceType type) {
        return this.pieceType == type;
    }

    public boolean isNotSamePieceType(final PieceType type) {
        return this.pieceType != type;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    protected abstract List<Position> createMovablePositions(final Position source, final Position target);
}
