package chess.domain.piece;

import chess.domain.Position;

import java.util.List;
import java.util.Map;

public class Queen extends Piece {
    public Queen() {
    }

    @Override
    public boolean isMovable(final Position current, final Position destination, final Map<Position, Piece> chessBoard) {
        if (!checkPositionRule(current, destination)) {
            return false;
        }
        if (current.checkDiagonalRule(destination)) {
            final List<Position> diagonalPath = current.generateDiagonalPath(destination);
            return checkEmptyPath(diagonalPath, chessBoard);
        }
        if (current.checkStraightRule(destination)) {
            System.out.println("여기로 와야해");
            final List<Position> straightPath = current.generateStraightPath(destination);
            return checkEmptyPath(straightPath, chessBoard);
        }
        return false;
    }

    @Override
    public boolean checkPositionRule(Position current, Position destination) {
        return current.checkDiagonalRule(destination) || current.checkStraightRule(destination);
    }
}