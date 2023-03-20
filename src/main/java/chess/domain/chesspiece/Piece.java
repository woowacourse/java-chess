package chess.domain.chesspiece;

import chess.domain.Side;
import chess.domain.Square;

public abstract class Piece {
    final Side side;

    Piece(final Side side) {
        this.side = side;
    }

    public boolean isNotSameSide(final Piece piece) {
        return this.side != piece.side;
    }

    public boolean isOppositeSide(final Piece piece) {
        return this.side != piece.side && !piece.isEmpty();
    }

    public boolean isEmpty() {
        return this.side == Side.NO_SIDE;
    }

    public String getSide() {
        return side.toString();
    }

    public abstract boolean isMovable(Square from, Square to, Piece piece);

    public boolean isWhite() {
        return side == Side.WHITE;
    }

    public boolean isBlack() {
        return side == Side.BLACK;
    }

    public boolean isPawn() {
        return this instanceof Pawn;
    }
}
