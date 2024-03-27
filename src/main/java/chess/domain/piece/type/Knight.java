package chess.domain.piece.type;

import chess.domain.PieceRelation;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceType;
import chess.domain.position.ChessDirection;
import chess.domain.position.Movement;

public final class Knight extends Piece {
    public Knight(final PieceColor color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    public boolean isMovable(final Movement movement, final PieceRelation pieceRelation, final boolean isOpened) {
        return isLShapeMovement(movement);
    }

    public boolean isLShapeMovement(final Movement movement) {
        ChessDirection chessDirection = movement.findDirection();
        return chessDirection.isLShaped();
    }
}
