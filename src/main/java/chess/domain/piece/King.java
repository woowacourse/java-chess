package chess.domain.piece;

import chess.domain.Position;

import java.util.List;
import java.util.Map;

public final class King extends Piece {
    private static final int KING_HASHCODE_AS_UNICODE = 9812;

    public King(final boolean isFirstMove) {
        super(isFirstMove);
    }

    public King() {
    }

    @Override
    public boolean isMovable(final Position current, final Position destination,
                             final Map<Position, Piece> chessBoard) {
        return checkPositionRule(current, destination) || checkKingSideCastlingRule(current, destination, chessBoard)
                || checkQueenSideCastlingRule(current, destination, chessBoard);
    }

    @Override
    public boolean checkPositionRule(final Position current, final Position destination) {
        return current.checkAdjacentEightWay(destination);
    }

    private boolean checkKingSideCastlingRule(final Position current, final Position destination,
                                              final Map<Position, Piece> chessBoard) {
        if (isFirstMove() && current.moveXandY(2, 0).equals(destination)) {
            final Position kingSideRookPosition = destination.moveXandY(1, 0);
            return checkCastlingPossibleRook(kingSideRookPosition, current, chessBoard);
        }
        return false;
    }

    private boolean checkQueenSideCastlingRule(final Position current, final Position destination,
                                               final Map<Position, Piece> chessBoard) {
        if (isFirstMove() && current.moveXandY(-2, 0).equals(destination)) {
            final Position queenSideRookPosition = destination.moveXandY(-2, 0);
            return checkCastlingPossibleRook(queenSideRookPosition, current, chessBoard);
        }
        return false;
    }

    private boolean checkCastlingPossibleRook(final Position rookPosition, final Position current,
                                              final Map<Position, Piece> chessBoard) {
        if (chessBoard.containsKey(rookPosition)) {
            final Piece piece = chessBoard.get(rookPosition);
            final List<Position> kingToRookPath = current.generateStraightPath(rookPosition);
            return piece.isRook() && piece.isFirstMove() && checkEmptyPath(kingToRookPath, chessBoard);
        }
        return false;
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public boolean isRook() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public int hashCode() {
        return KING_HASHCODE_AS_UNICODE;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        return getClass() == obj.getClass();
    }
}
