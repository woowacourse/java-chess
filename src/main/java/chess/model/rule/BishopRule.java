package chess.model.rule;

import chess.model.board.Board;
import chess.model.board.Square;
import chess.model.unit.Piece;

import java.util.ArrayList;
import java.util.List;

class BishopRule extends PieceRule {
    BishopRule() {
        super();
        pieceScore = 3;
    }

    @Override
    List<Square> getMovableSquares(Board board, final Square square, Piece piece) {
        final List<Square> candidate = new ArrayList<>();
        candidate.addAll(getNonBlockedNeighbors(board, square, Square::getUpperLeftOneNeighbor));
        candidate.addAll(getNonBlockedNeighbors(board, square, Square::getUpperRightOneNeighbor));
        candidate.addAll(getNonBlockedNeighbors(board, square, Square::getLowerLeftOneNeighbor));
        candidate.addAll(getNonBlockedNeighbors(board, square, Square::getLowerRightOneNeighbor));
        return candidate;
    }
}
