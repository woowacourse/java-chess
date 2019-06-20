package chess.model.rule;

import chess.model.board.Row;
import chess.model.board.Square;
import chess.model.unit.Piece;
import chess.model.unit.Side;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends MoveRule {
    Pawn(final Piece piece) {
        super(piece);
    }

    @Override
    List<Square> getMovableSquares(final Square square) {
        final List<Square> candidate = new ArrayList<>();
        candidate.add(forward(square));
        if (isFirstPosition(square)) {
            candidate.add(forward(forward(square)));
        }
        return candidate;
    }

    private Square forward(final Square square) {
        if (piece.getSide() == Side.WHITE) {
            return Square.getUpOneNeighbor(square);
        }
        return Square.getDownOneNeighbor(square);
    }

    private boolean isFirstPosition(final Square square) {
        if ((square.getRow() == Row._2) && (piece.getSide() == Side.WHITE)) {
            return true;
        }
        return (square.getRow() == Row._7) && (piece.getSide() == Side.BLACK);
    }
}
