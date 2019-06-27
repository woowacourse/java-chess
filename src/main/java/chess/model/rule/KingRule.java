package chess.model.rule;

import chess.model.board.Board;
import chess.model.board.Square;
import chess.model.unit.Piece;

import java.util.ArrayList;
import java.util.List;

class KingRule extends PieceRule {
    KingRule() {
        super();
    }

    @Override
    List<Square> getMovableSquares(Board board, final Square square, Piece piece) {
        final List<Square> candidate = new ArrayList<>();
        Square.getLeftOneNeighbor(square).ifPresent(candidate::add);
        Square.getRightOneNeighbor(square).ifPresent(candidate::add);
        Square.getUpOneNeighbor(square).ifPresent(candidate::add);
        Square.getDownOneNeighbor(square).ifPresent(candidate::add);
        Square.getUpperLeftOneNeighbor(square).ifPresent(candidate::add);
        Square.getUpperRightOneNeighbor(square).ifPresent(candidate::add);
        Square.getLowerLeftOneNeighbor(square).ifPresent(candidate::add);
        Square.getLowerRightOneNeighbor(square).ifPresent(candidate::add);
        return candidate;
    }
}
