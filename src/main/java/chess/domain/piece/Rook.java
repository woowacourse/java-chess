package chess.domain.piece;

import chess.domain.Position;

import java.util.List;
import java.util.Map;

public class Rook extends Piece {
    public Rook() {
    }

    @Override
    public boolean isMovable(Position current, Position destination, Map<Position, Piece> chessBoard) {
        if (!checkPositionRule(current, destination)) {
            return false;
        }
        final List<Position> straightPath = current.generateStraightPath(destination);
        return checkEmptyPath(straightPath, chessBoard);
    }

    @Override
    public boolean checkPositionRule(Position current, Position destination) {
        return current.checkStraightRule(destination);
    }
}
