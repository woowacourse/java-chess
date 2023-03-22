package chess.domain.piece;

import chess.domain.position.Move;

public abstract class Piece {

    protected final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public abstract boolean isValidMove(Move move, Piece targetPiece);

    public boolean isSameColor(Piece target) {
        if (target == null) {
            return false;
        }
        return color == target.color;
    }

    public boolean isRightTurn(Color turn) {
        return this.color == turn;
    }

    public abstract PieceType getType();

    public Color getColor() {
        return color;
    }
}
