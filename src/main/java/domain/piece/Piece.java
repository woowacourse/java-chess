package domain.piece;

import domain.board.Board;
import domain.board.Position;

public abstract class Piece {
    protected final PieceColor color;

    public Piece(final PieceColor color) {
        this.color = color;
    }

    public abstract void move(final Position source, final Position destination, final Board board);

    public abstract PieceType pieceType();

    public boolean isTeam(final PieceColor teamColor) {
        return this.color == teamColor;
    }

    public PieceColor pieceColor() {
        return color;
    }
}
