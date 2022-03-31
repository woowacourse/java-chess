package chess.domain.piece.movable.multiple;

import chess.domain.piece.AbstractPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.constant.PieceDirections;
import chess.domain.piece.constant.PieceType;

public abstract class MultipleMovablePiece extends AbstractPiece {

    protected MultipleMovablePiece(final PieceType pieceType, final PieceDirections pieceDirections) {
        super(pieceType, pieceDirections);
    }

    @Override
    protected final boolean isRouteSizeEnoughToMove(int routeSize) {
        return true;
    }

    @Override
    protected final boolean isRouteSizeEnoughToAttack(int routeSize) {
        return true;
    }

    @Override
    public final Piece move() {
        return this;
    }
}
