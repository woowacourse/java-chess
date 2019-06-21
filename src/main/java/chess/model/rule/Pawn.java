package chess.model.rule;

import chess.model.board.Board;
import chess.model.board.Row;
import chess.model.board.Square;
import chess.model.unit.Piece;
import chess.model.unit.Side;
import chess.model.unit.UnitClass;

import java.util.ArrayList;
import java.util.List;

import static chess.model.rule.Rule.getPiece;

class Pawn extends PieceRule {
    private static final double HALF_SCORE = 0.5;
    private static final double FULL_SCORE = 1;

    Pawn() {
        super();
    }

    @Override
    double getPieceScore(final Board board, final Square square) {
        final Piece piece = getPiece(board, square);
        final List<Square> squareList = getNonBlockedNeighbors(board, square, Square::getUpOneNeighbor);
        squareList.addAll(getNonBlockedNeighbors(board, square, Square::getDownOneNeighbor));
        final boolean isAnotherMyPawnInVertical = squareList.stream()
                .anyMatch(neighbor -> isMyPawn(board, neighbor, piece));
        return isAnotherMyPawnInVertical ? HALF_SCORE : FULL_SCORE;
    }

    private static boolean isMyPawn(final Board board, final Square square, final Piece piece) {
        final Piece another = getPiece(board, square);
        if (piece == null || another == null) return false;
        return another.getUnitClass() == UnitClass.PAWN
                && another.getSide() == piece.getSide();
    }

    @Override
    List<Square> getMovableSquares(Board board, final Square square, final Piece piece) {
        final List<Square> candidate = new ArrayList<>();
        final Square forwarded = forward(square, piece);
        final Piece maybeNull = getPiece(board, forwarded);
        candidate.add(forwarded);
        if (maybeNull == null && isFirstPosition(square, piece)) {
            candidate.add(forward(forwarded, piece));
        }
        return candidate;
    }

    private Square forward(final Square square, final Piece piece) {
        if (piece.getSide() == Side.WHITE) {
            return Square.getUpOneNeighbor(square);
        }
        return Square.getDownOneNeighbor(square);
    }

    private boolean isFirstPosition(final Square square, final Piece piece) {
        if ((square.getRow() == Row._2) && (piece.getSide() == Side.WHITE)) {
            return true;
        }
        return (square.getRow() == Row._7) && (piece.getSide() == Side.BLACK);
    }
}
