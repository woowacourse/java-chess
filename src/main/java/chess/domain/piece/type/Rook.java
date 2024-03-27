package chess.domain.piece.type;

import chess.domain.PieceRelation;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import chess.domain.position.ChessDirection;
import chess.domain.position.Movement;

public final class Rook extends Piece {

    public Rook(final PieceColor color) {
        super(color, PieceType.ROOK);
    }

    @Override
    public boolean isMovable(final Movement movement, final PieceRelation pieceRelation, final boolean isOpened) {
        return isMovableDirection(movement.findDirection()) && isOpened;
    }

    private boolean isMovableDirection(final ChessDirection chessDirection) {
        return chessDirection.isCross();
    }
}
