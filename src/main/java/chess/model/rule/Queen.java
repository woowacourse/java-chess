package chess.model.rule;

import chess.model.board.Square;
import chess.model.unit.Piece;

import java.util.ArrayList;
import java.util.List;

public class Queen extends MoveRule {
    Queen(final Piece piece) {
        super(piece);
    }

    @Override
    List<Square> getMovableSquares(final Square square) {
        final List<Square> candidate = new ArrayList<>();
        candidate.addAll(Square.getLeftNeighbors(square));
        candidate.addAll(Square.getRightNeighbors(square));
        candidate.addAll(Square.getUpNeighbors(square));
        candidate.addAll(Square.getDownNeighbors(square));
        candidate.addAll(Square.getUpperLeftNeighbors(square));
        candidate.addAll(Square.getUpperRightNeighbors(square));
        candidate.addAll(Square.getLowerLeftNeighbors(square));
        candidate.addAll(Square.getLowerRightNeighbors(square));
        return candidate;
    }
}
