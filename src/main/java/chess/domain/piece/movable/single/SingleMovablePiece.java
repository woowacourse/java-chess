package chess.domain.piece.movable.single;

import chess.domain.piece.AbstractPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.constant.PieceDirections;
import chess.domain.piece.constant.PieceType;

public abstract class SingleMovablePiece extends AbstractPiece {

    private static final int MAXIMUM_SIZE_OF_MOVABLE_ROUTE = 1;

    protected SingleMovablePiece(final PieceType pieceType, final PieceDirections pieceDirections) {
        super(pieceType, pieceDirections);
    }

    @Override
    protected final boolean isRouteSizeEnoughToMove(int routeSize) {
        return routeSize <= MAXIMUM_SIZE_OF_MOVABLE_ROUTE;
    }

    @Override
    protected final boolean isRouteSizeEnoughToAttack(int routeSize) {
        return routeSize <= MAXIMUM_SIZE_OF_MOVABLE_ROUTE;
    }

    @Override
    public final Piece move() {
        return this;
    }
}
