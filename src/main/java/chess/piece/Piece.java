package chess.piece;

import chess.chessboard.Side;
import chess.chessboard.Square;

// TODO: Piece에 속하는 클래스는 ChessPiece 패키지에 저장하기
public abstract class Piece {

    private final Side side;

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

    abstract public boolean isMovable(Square from, Square to, Piece piece);

    protected boolean isWhite() {
        return side == Side.WHITE;
    }

    protected boolean isBlack() {
        return side == Side.BLACK;
    }

    public Side getSide() {
        return side;
    }
}
