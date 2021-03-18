package chess.domain.piece;

import chess.domain.Position;

import java.util.List;
import java.util.Map;

public class Bishop extends Piece {
    public Bishop() {
        super();
    }

    @Override
    public boolean isMovable(Position current, Position destination, Map<Position, Piece> chessBoard) {
        if (!checkPositionRule(current, destination)) {
            return false;
        }
        final List<Position> diagonalPath = current.generateDiagonalPath(destination);
        return checkEmptyPath(diagonalPath, chessBoard);
    }

    @Override
    public boolean checkPositionRule(Position current, Position destination) {
        return checkDiagonalRule(current, destination);
    }
}
