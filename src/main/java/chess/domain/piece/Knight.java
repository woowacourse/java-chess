package chess.domain.piece;

import chess.domain.Position;

import java.util.Map;

public class Knight extends Piece {
    public Knight() {
    }

    @Override
    public boolean isMovable(Position current, Position destination, Map<Position, Piece> chessBoard) {
        return checkPositionRule(current, destination);
    }

    @Override
    public boolean checkPositionRule(Position current, Position destination) {
        int xDiff = Math.abs(current.getX() - destination.getX());
        int yDiff = Math.abs(current.getY() - destination.getY());
        return ((xDiff == 1 && yDiff == 2) || (xDiff == 2 && yDiff == 1));
    }
}
