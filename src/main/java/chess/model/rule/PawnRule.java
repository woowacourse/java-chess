package chess.model.rule;

import chess.model.Side;
import chess.model.board.Board;
import chess.model.board.Position;
import chess.model.board.Row;
import chess.model.board.Square;
import chess.model.unit.Piece;
import chess.model.unit.UnitClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static chess.model.rule.Rule.getPiece;

class PawnRule extends PieceRule {
    private static final double HALF_SCORE = 0.5;
    private static final double FULL_SCORE = 1;

    PawnRule() {
        super();
    }

    @Override
    double getPieceScore(final Board board, final Position position) {
        final Piece piece = position.getPiece();
        final List<Square> squareList = getNonBlockedNeighbors(board, position.getSquare(), Square::getUpOneNeighbor);
        squareList.addAll(getNonBlockedNeighbors(board, position.getSquare(), Square::getDownOneNeighbor));
        final boolean isAnotherMyPawnInVertical = squareList.stream()
                .anyMatch(neighbor -> isMyPawn(board, neighbor, piece));
        return isAnotherMyPawnInVertical ? HALF_SCORE : FULL_SCORE;
    }

    private static boolean isMyPawn(final Board board, final Square square, final Piece piece) {
        final Optional<Piece> another = getPiece(board, square);
        if (piece == null || another.isEmpty()) return false;
        return another.get().getUnitClass() == UnitClass.PAWN
                && another.get().getSide() == piece.getSide();
    }

    @Override
    List<Square> getMovableSquares(Board board, final Square square, final Piece piece) {
        final List<Square> candidate = new ArrayList<>();
        final Optional<Square> forwarded = forward(square, piece);
        final Optional<Piece> maybeNull = getPiece(board, forwarded);
        forwarded.ifPresent(candidate::add);
        if (maybeNull.isEmpty() && isFirstPosition(square, piece)) {
            candidate.add(forward(forwarded.get(), piece).get());
        }
        return candidate;
    }

    private Optional<Square> forward(final Square square, final Piece piece) {
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
