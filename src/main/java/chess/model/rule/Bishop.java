package chess.model.rule;

import chess.model.board.Board;
import chess.model.board.Square;
import chess.model.unit.Piece;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends MoveRule {
    Bishop(final Piece piece) {
        super(piece);
    }

    @Override
    List<Square> getMovableSquares(Board board, final Square square) {
        final List<Square> candidate = new ArrayList<>();
        candidate.addAll(getNonBlockedNeighbors(board, square, Square::getUpperLeftOneNeighbor));
        candidate.addAll(getNonBlockedNeighbors(board, square, Square::getUpperRightOneNeighbor));
        candidate.addAll(getNonBlockedNeighbors(board, square, Square::getLowerLeftOneNeighbor));
        candidate.addAll(getNonBlockedNeighbors(board, square, Square::getLowerRightOneNeighbor));
        return candidate;
    }
}
