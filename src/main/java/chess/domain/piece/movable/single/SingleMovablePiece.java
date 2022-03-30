package chess.domain.piece.movable.single;

import chess.domain.piece.AbstractPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.constant.PieceDirections;
import chess.domain.piece.constant.PieceName;
import chess.domain.piece.constant.PieceScore;

public abstract class SingleMovablePiece extends AbstractPiece {

    private static final int MAXIMUM_SIZE_OF_MOVABLE_ROUTE = 1;

    protected SingleMovablePiece(final PieceName pieceName,
                                 final PieceScore pieceScore,
                                 final PieceDirections pieceDirections) {
        super(pieceName, pieceScore, pieceDirections);
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
