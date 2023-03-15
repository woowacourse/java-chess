package chess.domain;

// TODO: Piece에 속하는 클래스는 ChessPiece 패키지에 저장하기
public abstract class Piece {
    final Side side;

    Piece(final Side side) {
        this.side = side;
    }

    public boolean isOppositeSide(final Piece piece) {
        return this.side != piece.side &&
                !piece.isEmpty();
    }

    public boolean isEmpty() {
        return this.side == Side.NO_SIDE;
    }

    public String getSide() {
        return side.toString();
    }

    abstract boolean isMovable(Square from, Square to, Piece piece);
}
