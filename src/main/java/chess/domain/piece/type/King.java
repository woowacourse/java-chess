package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;

public class King extends Piece {

    public King(final Color color, final PiecePosition piecePosition) {
        super(color, piecePosition);
    }

    @Override
    protected void validateMovable(final Path path) {
        if (!path.isUnitDistance()) {
            throw new IllegalArgumentException("킹은 한칸만 이동할 수 있습니다.");
        }
    }
}
