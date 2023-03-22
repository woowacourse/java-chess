package chess.piece;

import chess.chessboard.Side;
import chess.chessboard.Square;

// TODO: Piece에 속하는 클래스는 ChessPiece 패키지에 저장하기
public abstract class Piece {

    private final Side side;

    Piece(final Side side) {
        this.side = side;
    }

    protected boolean isNotSameSide(final Piece piece) {
        return this.side != piece.side;
    }

    protected boolean isOppositeSide(final Piece piece) {
        return this.side != piece.side && !piece.isEmpty();
    }

    protected boolean isEmpty() {
        return this.side == Side.NO_SIDE;
    }

    abstract public boolean isMovable(Square source, Square to, Piece piece);

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
