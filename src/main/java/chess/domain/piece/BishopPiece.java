package chess.domain.piece;

import chess.domain.Position;

public class BishopPiece extends Piece {
    public BishopPiece(Color color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public boolean canMove(Position from, Position to, Piece piece) {
        int x = from.calculateFileDifference(to);
        int y = from.calculateRankDifference(to);
        return Math.abs(x) == Math.abs(y) && piece.color != color;
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
