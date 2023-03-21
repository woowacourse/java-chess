package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

public class KnightPiece extends Piece {
    public KnightPiece(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position from, Position to, Piece piece) {
        int x = from.getFileDifference(to);
        int y = from.getRankDifference(to);
        return validMoveRequest(x, y) && piece.color != color;
    }

    private boolean validMoveRequest(int x, int y) {
        if (Math.abs(x) == 2 && Math.abs(y) == 1) {
            return true;
        }
        return Math.abs(x) == 1 && Math.abs(y) == 2;
    }

    @Override
    public boolean canJump() {
        return true;
    }
}
