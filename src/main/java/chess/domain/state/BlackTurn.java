package chess.domain.state;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Direction;
import chess.domain.position.Square;
import chess.domain.position.SquareDifferent;

import java.util.Map;

public class BlackTurn implements Turn {

    public Turn checkMovable(Map<Square, Piece> board, Square source, Square destination, Piece sourcePiece, Piece destinationPiece) {
        if (sourcePiece.isWhite()) {
            throw new IllegalArgumentException(WhiteTurn.NOT_YOUR_TURN_ERROR);
        }

        if (!sourcePiece.canMove(source, destination)) {
            throw new IllegalArgumentException(WhiteTurn.CANNOT_MOVE_ERROR);
        }

        if (sourcePiece.isSameColor(destinationPiece)) {
            throw new IllegalArgumentException(WhiteTurn.SAME_COLOR_ERROR);
        }

        if (sourcePiece.matches(PieceType.PAWN)) {
            checkPawnCanCatch(source, destination, destinationPiece);
        }

        checkPathBlocked(board, source, destination, sourcePiece);

        return new WhiteTurn();
    }

    private void checkPawnCanCatch(Square source, Square destination, Piece destinationPiece) {
        SquareDifferent squareDifferent = source.calculateDiff(destination);
        Direction direction = Direction.findDirectionByDiff(squareDifferent);

        if (!direction.isDiagonal() && destinationPiece.isNotEmpty()) {
            throw new IllegalArgumentException(WhiteTurn.PAWN_CANNOT_CATCH_STRAIGHT_ERROR);
        }
    }

    private void checkPathBlocked(Map<Square, Piece> board, Square source, Square destination, Piece sourcePiece) {
        SquareDifferent diff = source.calculateDiff(destination);

        if (needFindPathCondition(source, sourcePiece)) {
            findPath(board, source, destination, diff);
        }
    }

    private boolean needFindPathCondition(Square source, Piece sourcePiece) {
        return !sourcePiece.matches(PieceType.KNIGHT)
                && !(sourcePiece.matches(PieceType.PAWN) && source.isPawnStartSquare())
                && !sourcePiece.matches(PieceType.KING);
    }

    private void findPath(Map<Square, Piece> board, Square source, Square destination, SquareDifferent diff) {
        Square candidate = source;
        Direction direction = Direction.findDirectionByDiff(diff);

        while (!candidate.equals(destination)) {
            checkBlocked(board, source, candidate);
            candidate = direction.nextSquare(candidate);
        }
    }

    private void checkBlocked(Map<Square, Piece> board, Square source, Square candidate) {
        if (!source.equals(candidate) && board.get(candidate).isNotEmpty()) {
            throw new IllegalArgumentException(WhiteTurn.PATH_BLOCKED_ERROR);
        }
    }
}
