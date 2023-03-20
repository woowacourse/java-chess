package domain.piece;

import domain.chessboard.SquareStatus;
import domain.chessboard.Type;
import domain.coordinate.MovePosition;
import domain.coordinate.Position;

public abstract class Piece implements SquareStatus {

    protected final Color color;
    protected final PieceType pieceType;

    public Piece(final Color color, final PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }

    @Override
    public final Type getType() {
        return pieceType;
    }

    @Override
    public final Color getColor() {
        return color;
    }

    protected final void validateMovable(MovePosition movePosition) {
        if (isMovable(movePosition)) {
            return;
        }
        throw new IllegalStateException("잘못된 도착 지점입니다.");
    }

    protected abstract boolean isMovable(MovePosition movePosition);

}
