package chess.domain.chesspiece;

import chess.domain.Side;
import chess.domain.Square;

public abstract class Piece {
    protected final Side side;

    protected final PieceInfo pieceInfo;

    Piece(final Side side, final PieceInfo pieceInfo) {
        this.side = side;
        this.pieceInfo = pieceInfo;
    }

    public abstract boolean isMovable(Square from, Square to, Piece piece);

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
        return side.name();
    }

    public String getName() {
        return pieceInfo.name();
    }

    public double addPieceScore(final double score) {
        return score + pieceInfo.addScore(score);
    }
}
