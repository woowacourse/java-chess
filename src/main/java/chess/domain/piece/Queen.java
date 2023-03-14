package chess.domain.piece;

import chess.domain.piece.position.PiecePosition;

public class Queen extends Piece {

    protected Queen(final PiecePosition position, final Color color) {
        super(position, color);
    }
}
