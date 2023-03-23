package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

public class KingPiece extends Piece {
    public KingPiece(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position from, Position to, Piece piece) {
        int x = from.calculateFileDifference(to);
        int y = from.calculateRankDifference(to);
        return validMoveRequest(x, y) && piece.color != color;
    }

    private boolean validMoveRequest(int x, int y) {
        return Math.abs(x) <= 1 && Math.abs(y) <= 1;
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
