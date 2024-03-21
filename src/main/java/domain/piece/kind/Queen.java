package domain.piece.kind;

import domain.piece.Piece;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.Point;

public class Queen extends Piece {
    public Queen(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.QUEEN;
    }

    //TODO : 현재 방향을 계산해야만 검증 로직 가능 좀 더 생각해봐야함
    public boolean canMove(final Point point) {
        this.point.calculate(point);
        return true;
    }
}
