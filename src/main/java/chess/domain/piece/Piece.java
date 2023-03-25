package chess.domain.piece;

import chess.domain.position.Move;

public class Piece {

    protected final Color color;
    protected final PieceType type;

    public Piece(Color color, PieceType type) {
        this.color = color;
        this.type = type;
    }

    public boolean canMove(Move move, Piece targetPiece) {
        return type.getMoveStrategy().canMove(move);
    }

    public boolean isSameColor(Piece target) {
        if (target == null) {
            return false;
        }
        return color == target.color;
    }

    public boolean isRightTurn(Color turn) {
        return this.color == turn;
    }

    public PieceType getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }
}
