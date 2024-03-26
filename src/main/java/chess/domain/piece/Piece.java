package chess.domain.piece;

import chess.domain.board.Route;

public abstract class Piece {
    private final Color color;
    private final PieceType pieceType;

    protected Piece(Color color, PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }

    public abstract boolean canMove(Route route);

    public boolean isBlack() {
        return this.color == Color.BLACK;
    }

    public boolean isAllyPiece(Piece other) {
        return this.color == other.color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
