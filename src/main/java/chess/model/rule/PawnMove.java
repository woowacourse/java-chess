package chess.model.rule;

import chess.model.board.Board;
import chess.model.board.Row;
import chess.model.board.Square;
import chess.model.unit.Piece;
import chess.model.unit.Side;

import java.util.ArrayList;
import java.util.List;

import static chess.model.rule.Rule.getPiece;

class PawnMove extends MoveRule {
    PawnMove() {
        super();
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
