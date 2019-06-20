package chess.model.rule;

import chess.model.board.Board;
import chess.model.board.Square;
import chess.model.unit.Piece;

import java.util.ArrayList;
import java.util.List;

class King extends MoveRule {
    King(final Piece piece) {
        super(piece);
    }

    @Override
    List<Square> getMovableSquares(Board board, final Square square) {
        final List<Square> candidate = new ArrayList<>();
        candidate.add(Square.getLeftOneNeighbor(square));
        candidate.add(Square.getRightOneNeighbor(square));
        candidate.add(Square.getUpOneNeighbor(square));
        candidate.add(Square.getDownOneNeighbor(square));
        candidate.add(Square.getUpperLeftOneNeighbor(square));
        candidate.add(Square.getUpperRightOneNeighbor(square));
        candidate.add(Square.getLowerLeftOneNeighbor(square));
        candidate.add(Square.getLowerRightOneNeighbor(square));
        return candidate;
    }
}
