package chess.piece;

import chess.chessboard.Position;
import chess.chessboard.Side;

public abstract class Piece {

    private final Side side;
    private final PieceType pieceType;

    Piece(final Side side, PieceType pieceType) {
        this.side = side;
        this.pieceType = pieceType;
    }

    protected boolean isOppositeSide(final Piece piece) {
        return isNotSameSide(piece) && !piece.isEmpty();
    }

    protected boolean isNotSameSide(final Piece piece) {
        return this.side != piece.side;
    }

    protected boolean isEmpty() {
        return this.side == Side.EMPTY;
    }

    abstract public boolean isMovable(Position source, Position to, Piece piece);

    public boolean hasSideOf(final Side side) {
        return this.side == side;
    }

    public Side getSide() {
        return side;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public double getScore() {
        return pieceType.getScore();
    }
}
