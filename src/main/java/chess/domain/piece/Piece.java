package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.dto.PieceSymbol;

import java.util.Objects;

public abstract class Piece {

    private final Color color;
    private final PieceSymbol pieceSymbol;

    protected Piece(final Color color, final PieceSymbol pieceSymbol) {
        this.color = color;
        this.pieceSymbol = pieceSymbol;
    }

    public abstract void checkMovingRange(final Board board, final Position from, final Position to);

    public abstract boolean isPawn();

    public abstract boolean isKnight();

    public abstract boolean isKing();

    public boolean isSameColor(final Color other) {
        return color == other;
    }

    public boolean isSameColorWithPawn(final Color color) {
        return isSameColor(color) && isPawn();
    }

    public boolean isSameColorWithoutPawn(final Color color) {
        return isSameColor(color) && !isPawn();
    }

    public boolean isSamePieceSymbol(final PieceSymbol pieceSymbol) {
        return this.pieceSymbol == pieceSymbol;
    }

    public Color getColor() {
        return color;
    }

    public PieceSymbol getPieceSymbol() {
        return pieceSymbol;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Piece)) return false;

        Piece piece = (Piece) other;

        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
