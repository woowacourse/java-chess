package chess.domain.piece.type;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.PieceRelation;
import chess.domain.piece.PieceType;
import chess.domain.position.ChessDirection;
import chess.domain.position.Movement;

public final class Queen extends Piece {

    public Queen(final PieceColor color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    public boolean isMovable(final Movement movement, final PieceRelation pieceRelation, final boolean isOpened) {
        return isMovableDirection(movement.findDirection()) && isOpened;
    }

    private boolean isMovableDirection(final ChessDirection chessDirection) {
        return chessDirection.isDiagonal() || chessDirection.isCross();
    }
}
