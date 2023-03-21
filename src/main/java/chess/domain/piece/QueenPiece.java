package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

public class QueenPiece extends Piece {
    public QueenPiece(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position from, Position to, Piece piece) {
        int x = from.calculateFileDifference(to);
        int y = from.calculateRankDifference(to);
        return isValidRequest(x, y) && piece.color != color;
    }

    private boolean isValidRequest(int x, int y) {
        if (x == 0 || y == 0) {
            return isValidStraightRequest(x, y);
        }
        return Math.abs(x) == Math.abs(y);
    }

    private boolean isValidStraightRequest(int x, int y) {
        if (x == 0 && y != 0) {
            return true;
        }
        return x != 0 && y == 0;
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
