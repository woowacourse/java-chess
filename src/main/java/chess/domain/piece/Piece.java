package chess.domain.piece;

import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.pawn.WhitePawn;
import chess.domain.square.Direction;
import chess.domain.square.Square;

abstract public class Piece {

    private PieceType pieceType;
    protected Color color;

    protected Piece(final Color color, final PieceType pieceType) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public boolean isEnemy(final Piece piece) {
        return piece.color != color;
    }

    // TODO: override 할까? (Pawn만 true 반환)
    public boolean isPawn() {
        return getClass().equals(WhitePawn.class) || getClass().equals(BlackPawn.class);
    }

    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }

    public boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    public abstract Direction findDirection(final Square current, final Square destination);

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                '}';
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Color getColor() {
        return color;
    }
}
