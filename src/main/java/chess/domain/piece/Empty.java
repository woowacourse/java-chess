package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.Position;

import java.util.List;

public final class Empty extends Piece {
    private Empty(final PieceType pieceType, final Color color) {
        super(pieceType, color);
    }

    public static Empty create() {
        return new Empty(PieceType.EMPTY, Color.EMPTY);
    }

    @Override
    protected List<Position> createMovablePositions(final Position source, final Position target) {
        throw new UnsupportedOperationException("Empty 기물은 사용하지 않는 createMovablePositions 메서드 입니다.");
    }
}
