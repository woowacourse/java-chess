package chess.domain.chesspiece;

import chess.domain.Side;
import chess.domain.Square;

public abstract class Piece {
    public abstract boolean isMovable(Square from, Square to, Piece piece);

    protected final Side side;

    protected final PieceInfo pieceInfo;

    Piece(final Side side, final PieceInfo pieceInfo) {
        this.side = side;
        this.pieceInfo = pieceInfo;
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

    public boolean isWhite() {
        return side == Side.WHITE;
    }

    public boolean isBlack() {
        return side == Side.BLACK;
    }

    public String getSide() {
        return side.toString();
    }

    public boolean isPawn() {
        return this instanceof Pawn;
    }
}
