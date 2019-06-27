package chess.model.rule;

import chess.model.board.Board;
import chess.model.board.Square;
import chess.model.unit.Piece;

import java.util.ArrayList;
import java.util.List;

class RookRule extends PieceRule {
    RookRule() {
        super();
        pieceScore = 5;
    }

    @Override
    List<Square> getMovableSquares(Board board, final Square square, Piece piece) {
        final List<Square> candidate = new ArrayList<>();
        candidate.addAll(getNonBlockedNeighbors(board, square, Square::getLeftOneNeighbor));
        candidate.addAll(getNonBlockedNeighbors(board, square, Square::getRightOneNeighbor));
        candidate.addAll(getNonBlockedNeighbors(board, square, Square::getUpOneNeighbor));
        candidate.addAll(getNonBlockedNeighbors(board, square, Square::getDownOneNeighbor));
        return candidate;
    }
}
