package chess.domain.piece;

import chess.domain.Position;

public class RookPiece extends Piece {
    public RookPiece(Color color) {
        super(color, PieceType.ROOK);
    }

    @Override
    public boolean canMove(Position from, Position to, Piece piece) {
        int x = from.calculateFileDifference(to);
        int y = from.calculateRankDifference(to);
        return isValidVariance(x, y) && piece.color != color;
    }

    private boolean isValidVariance(int x, int y) {
        if (x != 0 && y == 0) {
            return true;
        }
        return x == 0 && y != 0;
    }

    @Override
    public boolean canJump() {
        return false;
    }

    @Override
    public Piece nextPiece() {
        return this;
    }
}
