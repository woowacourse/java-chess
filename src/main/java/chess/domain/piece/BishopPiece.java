package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

public class BishopPiece extends Piece {
    public BishopPiece(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position from, Position to, Piece piece) {
        int x = from.getFileDifference(to);
        int y = from.getRankDifference(to);
        return Math.abs(x) == Math.abs(y) && piece.color != color;
    }

    @Override
    public boolean canJump() {
        return false;
    }
}
